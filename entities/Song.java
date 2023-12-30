package com.crio.jukebox.entities;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Song extends BaseEntity{

  private String name;
  private String genre;
  private List<Artist> artistList;

  public Song(Song song){
    this(song.id, song.name, song.genre, song.artistList);
  }

  public Song(String name, String genre, List<Artist> artistList){
    this.name = name;
    this.genre = genre;
    this.artistList = artistList;
  }

  public Song(String id, String name, String genre, List<Artist> artistList){
    this(name, genre, artistList);
    this.id = id;
  }

  public String getId(){
    return id;
  }

  public String getName() {
    return name;
  }

  public String getGenre() {
    return genre;
  }

  public List<Artist> getArtistList() {
    return artistList.stream().collect(Collectors.toList());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Song)) {
      return false;
    }
    Song song = (Song) o;
    return getName().equals(song.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName());
  }

  @Override
  public String toString() {
    return "Song{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", genre='" + genre + '\'' +
        ", artistList=" + artistList +
        '}';
  }
}
