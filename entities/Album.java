
package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Album extends BaseEntity {

  private String name;
  private Artist owner;
  private List<Song> songList;

  public Album(Album album){
    this(album.id, album.name, album.owner);
  }
  public Album(String name, Artist owner){
    this.name = name;
    this.owner = owner;
    this.songList = new ArrayList<>();
  }

  public Album(String id, String name, Artist owner){
    this(name, owner);
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public Artist getOwner() {
    return owner;
  }

  public List<Song> getSongList() {
    return songList.stream().collect(Collectors.toList());
  }

  public void addSong(Song song) throws UnsupportedOperationException{
    if(!songList.contains(song)){
      songList.add(song);
    } else throw new UnsupportedOperationException("Can not add song, already exists in album");
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Album)) {
      return false;
    }
    Album album = (Album) o;
    return name.equals(album.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  @Override
  public String toString() {
    return "Album{" +
        "name='" + name + '\'' +
        ", owner=" + owner +
        ", songList=" + songList +
        ", id='" + id + '\'' +
        '}';
  }
}
