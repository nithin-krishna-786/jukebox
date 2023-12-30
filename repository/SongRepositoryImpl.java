package com.crio.jukebox.repository;

import com.crio.jukebox.entities.Song;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class SongRepositoryImpl implements ISongRepository {

  private Map<String, Song> songMap = new HashMap<>();

  private long autoIncrement = 0;

  public SongRepositoryImpl(){}

  public SongRepositoryImpl(Map<String, Song> songMap){
    this.songMap = songMap;
    this.autoIncrement = songMap.size();
  }

  @Override
  public Song save(Song entity) {
    if(entity.getId() == null){
      autoIncrement++;
      Song song = new Song(
          String.valueOf(autoIncrement),
          entity.getName(),
          entity.getGenre(),
          entity.getArtistList()
      );
      songMap.put(song.getId(), song);
      return song;
    }
    songMap.put(entity.getId(), entity);
    return entity;
  }

  @Override
  public List<Song> findAll() {
    return songMap.values().stream().collect(Collectors.toList());
  }

  @Override
  public Optional<Song> findById(String s) {

      return Optional.ofNullable(songMap.get(s));
  }

  @Override
  public boolean existById(String s) {
    return songMap.containsKey(s);
  }

  @Override
  public void delete(Song entity) {
    if(songMap.containsKey(entity.getId())){
      songMap.remove(entity.getId());
    }
  }

  @Override
  public void deleteById(String s) {
    if(songMap.containsKey(s)){
      songMap.remove(s);
    }
  }

  @Override
  public long count() {
    return songMap.size();
  }
}
