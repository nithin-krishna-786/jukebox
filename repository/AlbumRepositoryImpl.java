package com.crio.jukebox.repository;

import com.crio.jukebox.entities.Album;
import com.crio.jukebox.entities.Song;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class AlbumRepositoryImpl implements IAlbumRepository{

  private Map<String, Album> albumMap = new HashMap<>();
  private Map<String, Album> songAlbumMap = new HashMap<>();
  private long autoIncrement = 0;

  public AlbumRepositoryImpl(){}
  public AlbumRepositoryImpl(Map<String, Album> albumMap){
    this.albumMap = albumMap;
    autoIncrement = albumMap.size();
  }

  @Override
  public Album save(Album entity) {
    if(entity.getId() == null){
      autoIncrement++;
      Album album = new Album(
          String.valueOf(autoIncrement),
          entity.getName(),
          entity.getOwner()
      );
      albumMap.put(album.getName(), album);
      return album;
    }
    albumMap.put(entity.getName(), entity);
    return entity;
  }

  @Override
  public void addSong(String albumName, Song song){
    if(albumMap.containsKey(albumName)){
      Album album = albumMap.get(albumName);
      album.addSong(song);
      albumMap.put(albumName, album);
      songAlbumMap.put(song.getId(), album);
    }
  }

  @Override
  public Optional<Album> findAlbumBySongId(String id) {
    return Optional.ofNullable(songAlbumMap.get(id));
  }

  @Override
  public List<Album> findAll() {
    return albumMap.values().stream().collect(Collectors.toList());
  }

  @Override
  public Optional<Album> findById(String s) {
    return Optional.ofNullable(albumMap.get(s));
  }

  @Override
  public boolean existById(String s) {
    return albumMap.containsKey(s);
  }

  @Override
  public void delete(Album entity) {
  }

  @Override
  public void deleteById(String s) {

  }

  @Override
  public long count() {
    return albumMap.size();
  }
}
