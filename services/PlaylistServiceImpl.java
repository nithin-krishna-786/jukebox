package com.crio.jukebox.services;

import com.crio.jukebox.dtos.PlayListUpdateDto;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.PlaylistNotExistException;
import com.crio.jukebox.exceptions.SongNotAvailableException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.repository.IPlaylistRepository;
import com.crio.jukebox.repository.ISongRepository;
import com.crio.jukebox.repository.IUserRepository;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class PlaylistServiceImpl implements IPlaylistService{

  private IUserRepository userRepository;
  private IPlaylistRepository playlistRepository;
  private ISongRepository songRepository;

  public PlaylistServiceImpl(IUserRepository userRepository,
      IPlaylistRepository playlistRepository, ISongRepository songRepository){
    this.userRepository = userRepository;
    this.playlistRepository = playlistRepository;
    this.songRepository = songRepository;
  }

  @Override
  public Playlist create(String uid, String name, List<String> songIds) {
    User user = userRepository.findById(uid).orElseThrow(UserNotFoundException::new);

    List<Song> songsToBeAdded = collectSongsFromIds(songIds);

    Playlist playlist = new Playlist(name, songsToBeAdded);
    Playlist newPlaylist = playlistRepository.save(playlist);

    user.addPlaylist(newPlaylist);
    userRepository.save(user);
    return newPlaylist;
  }

  @Override
  public void delete(String uid, String pid) {
    User user = userRepository.findById(uid).orElseThrow(UserNotFoundException::new);
    Playlist playlist = playlistRepository
        .findById(pid)
        .orElseThrow(PlaylistNotExistException::new);

    playlistRepository.delete(playlist);
    user.deletePlaylist(playlist);
    userRepository.save(user);
  }

  @Override
  public PlayListUpdateDto addSongsAndUpdatePlaylist(String uid, String pid, List<String> songIds) {
    User user = userRepository.findById(uid).orElseThrow(UserNotFoundException::new);
    Playlist playlist = playlistRepository
        .findById(pid)
        .orElseThrow(PlaylistNotExistException::new);

    List<Song> songsTobeAdded = collectSongsFromIds(songIds);
    playlist.addSong(songsTobeAdded);


    return update(playlist);
  }

  @Override
  public PlayListUpdateDto deleteSongsAndUpdatePlaylist(String uid, String pid,
      List<String> songIds) {

    User user = userRepository.findById(uid).orElseThrow(UserNotFoundException::new);
    Playlist playlist = playlistRepository.findById(pid).orElseThrow(PlaylistNotExistException::new);

    List<Song> songsTobeDeleted = collectSongsFromIds(songIds);
    playlist.removeSongs(songsTobeDeleted);

    return update(playlist);
  }

  private List<Song> collectSongsFromIds(List<String> songIds){
    List<Song> temp = new LinkedList<>();
    for(String id: songIds){
      if(!songRepository.existById(id)){
        throw new SongNotAvailableException("Song with "+id+" not available");
      }
      Song song = songRepository.findById(id).get();
      temp.add(song);
    }
    return temp;
  }

  private PlayListUpdateDto update(Playlist playlist) {
    Playlist updatedPlaylist = playlistRepository.save(playlist);
    List<String> latestSongsIds = updatedPlaylist.getSongList()
        .stream().map(Song::getId).collect(Collectors.toList());
    String songs = String.join(" ",latestSongsIds);

    PlayListUpdateDto playListUpdateDto = new PlayListUpdateDto(
        updatedPlaylist.getId(),
        updatedPlaylist.getName(),
        songs
    );

    return playListUpdateDto;
  }
}
