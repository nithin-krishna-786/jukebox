package com.crio.jukebox.entities;

import java.util.Objects;

public class Artist extends BaseEntity{
  private String name;

  public Artist(Artist artist){
    this(artist.id, artist.name);
  }

  public Artist(String name){
    this.name = name;
  }

  public Artist(String id, String name){
    this.id = id;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Artist)) {
      return false;
    }
    Artist artist = (Artist) o;
    return id.equals(artist.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "Artist{" +
        "name='" + name + '\'' +
        ", id='" + id + '\'' +
        '}';
  }
}
