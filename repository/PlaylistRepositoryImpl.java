package com.crio.jukebox.repository;

import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class PlaylistRepositoryImpl implements IPlaylistRepository{

  private Map<String, Playlist> playlistMap = new HashMap<>();
  private long autoIncrement = 0;

  public PlaylistRepositoryImpl(){}
  public PlaylistRepositoryImpl(Map<String, Playlist> playlistMap){
    this.playlistMap = playlistMap;
    autoIncrement = playlistMap.size();
  }


  @Override
  public Playlist save(Playlist entity) {
    if(entity.getId() == null){
      autoIncrement++;
      Playlist playlist = new Playlist(
          String.valueOf(autoIncrement),
          entity.getName(),
          entity.getSongList()
      );

      playlistMap.put(playlist.getId(), playlist);
      return playlist;
    }

    Playlist existingPlaylist = playlistMap.get(entity.getId());
    List<Song> newSongs = entity.getSongList();
    existingPlaylist.addSong(newSongs);
    playlistMap.put(entity.getId(),existingPlaylist);
    return existingPlaylist;
  }

  @Override
  public List<Playlist> findAll() {
    return playlistMap.values().stream().collect(Collectors.toList());
  }

  @Override
  public Optional<Playlist> findById(String s) {
    return Optional.ofNullable(playlistMap.get(s));
  }

  @Override
  public boolean existById(String s) {
    return playlistMap.containsKey(s);
  }

  @Override
  public void delete(Playlist entity) {
    if(playlistMap.containsKey(entity.getId())){
      playlistMap.remove(entity.getId());
    }
  }

  @Override
  public void deleteById(String s) {
    if(playlistMap.containsKey(s)){
      playlistMap.remove(s);
    }
  }

  @Override
  public long count() {
    return playlistMap.size();
  }

}
