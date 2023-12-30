package com.crio.jukebox.repository;

import com.crio.jukebox.entities.Artist;
import com.crio.jukebox.entities.Song;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ArtistRepositoryImpl implements IArtistRepository{

  private Map<String, Artist> artistMap = new HashMap<>();
  private long autoIncrement = 0;

  public ArtistRepositoryImpl(){

  }

  public ArtistRepositoryImpl(Map<String, Artist> artistMap){
    this.artistMap = artistMap;
    autoIncrement = artistMap.size();
  }


  @Override
  public Artist save(Artist entity) {
    if (entity.getId() == null) {
      autoIncrement++;
      Artist artist = new Artist(String.valueOf(autoIncrement), entity.getName());
      artistMap.put(artist.getId(), artist);
      return artist;
    }

    artistMap.put(entity.getId(), entity);
    return entity;
  }

  @Override
  public List<Artist> findAll() {
    return artistMap.values().stream().collect(Collectors.toList());
  }

  @Override
  public Optional<Artist> findById(String s) {
    return Optional.ofNullable(artistMap.get(s));
  }

  @Override
  public boolean existById(String s) {
    return artistMap.containsKey(s);
  }

  @Override
  public void delete(Artist entity) {
    if(artistMap.containsKey(entity.getId())){
      artistMap.remove(entity.getId());
    }
  }

  @Override
  public void deleteById(String s) {
    if(artistMap.containsKey(s)){
      artistMap.remove(s);
    }
  }

  @Override
  public long count() {
    return artistMap.size();
  }
}
