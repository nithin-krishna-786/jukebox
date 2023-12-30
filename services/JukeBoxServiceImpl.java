package com.crio.jukebox.services;

import com.crio.jukebox.dtos.SongDto;
import com.crio.jukebox.entities.Album;
import com.crio.jukebox.entities.Artist;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.PlaylistNotExistException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.repository.AlbumRepositoryImpl;
import com.crio.jukebox.repository.IAlbumRepository;
import com.crio.jukebox.repository.IPlaylistRepository;
import com.crio.jukebox.repository.ISongRepository;
import com.crio.jukebox.repository.IUserRepository;
import java.util.List;
import java.util.stream.Collectors;

public class JukeBoxServiceImpl implements IJukeBoxService{

  private IAlbumRepository albumRepository;
  private IPlaylistRepository playlistRepository;
  private IUserRepository userRepository;

  public JukeBoxServiceImpl(IAlbumRepository albumRepository,
      IPlaylistRepository playlistRepository, IUserRepository userRepository){
    this.albumRepository = albumRepository;
    this.playlistRepository = playlistRepository;
    this.userRepository = userRepository;
  }

  @Override
  public SongDto selectAndPlayPlaylist(String uid, String pid) {
    User user = userRepository.findById(uid).orElseThrow(UserNotFoundException::new);
    Playlist playlist = playlistRepository.findById(pid).orElseThrow(PlaylistNotExistException::new);
    user.setActivePlaylist(playlist);
    playlist.setCurrentSongIndex(0);
    userRepository.save(user);
    playlistRepository.save(playlist);

    SongDto songDto = getSongToBePlayed(playlist, 0);
    return songDto;
  }

  @Override
  public SongDto playNextSongInActivePlaylist(String uid) {
    User user = userRepository.findById(uid).orElseThrow(UserNotFoundException::new);
    Playlist activePlaylist = user.getActivePlaylist();
    int nextSongIndex = activePlaylist.getCurrentSongIndex() + 1;
    if(nextSongIndex == activePlaylist.getSongList().size()){
      nextSongIndex = 0;
    }
    activePlaylist.setCurrentSongIndex(nextSongIndex);
    playlistRepository.save(activePlaylist);
    SongDto songDto = getSongToBePlayed(activePlaylist, nextSongIndex);
    return songDto;
  }

  private SongDto getSongToBePlayed(Playlist playlist, int songIndex){
    Song song = playlist.getSongList().get(songIndex);
    Album album = albumRepository.findAlbumBySongId(song.getId()).orElse(new Album(
        "Jukebox", null
    ));;
    List<String> artistList = song.getArtistList().stream().map(Artist::getName)
        .collect(Collectors.toList());
    String artist = String.join(",", artistList);
    return new SongDto(song.getName(), album.getName(), artist);
  }

  @Override
  public SongDto playPrevSongInActivePlaylist(String uid) {
    User user = userRepository.findById(uid).orElseThrow(UserNotFoundException::new);
    Playlist activePlaylist = user.getActivePlaylist();
    int prevSongIndex = activePlaylist.getCurrentSongIndex() - 1;
    if(prevSongIndex < 0) {
      prevSongIndex = activePlaylist.getSongList().size() - 1;
    }
    activePlaylist.setCurrentSongIndex(prevSongIndex);
    playlistRepository.save(activePlaylist);
    SongDto songDto = getSongToBePlayed(activePlaylist, prevSongIndex);
    return songDto;
  }

  @Override
  public SongDto playSelectedSong(String uid, String songId) {
    User user = userRepository.findById(uid).orElseThrow(UserNotFoundException::new);
    Playlist playlist = user.getActivePlaylist();
    Song song = playlist.getSongList().stream()
        .filter(s -> s.getId().equals(songId))
        .findAny().orElse(null);

    if(song == null) return new SongDto();

    int songIndex = playlist.getSongList().indexOf(song);
    playlist.setCurrentSongIndex(songIndex);
    playlistRepository.save(playlist);
    SongDto songDto = getSongToBePlayed(playlist, songIndex);
    return songDto;
  }
}
