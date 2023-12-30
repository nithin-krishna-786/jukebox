package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class User extends BaseEntity {
  private String name;
  private List<Playlist> playlists = new ArrayList<>();
  private Playlist active;

  public User(User user){
    this(user.id, user.name);
  }

  public User(String name){
    this.name = name;
  }

  public User(String id, String name) {
    this.id = id;
    this.name = name;
  }


  public String getName() {
    return name;
  }

  public List<Playlist> getPlaylists(){
    return playlists.stream().collect(Collectors.toList());
  }

  public void addPlaylist(Playlist playlist){
    playlists.add(playlist);
  }

  public void deletePlaylist(Playlist playlist){
    playlists.remove(playlist);
  }

  public void setActivePlaylist(Playlist playlist){
    this.active = playlist;
  }

  public Playlist getActivePlaylist(){
    return active;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof User)) {
      return false;
    }
    User user = (User) o;
    return getId().equals(user.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }

  @Override
  public String toString() {
    return "User{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", playlists=" + playlists +
        '}';
  }
}
