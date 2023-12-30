package com.crio.jukebox.entities;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Playlist extends BaseEntity{
  private String name;
  private List<Song> songList;
  private int currentSongIndex;

  public Playlist(Playlist playlist){
    this(playlist.id, playlist.name, playlist.songList);
  }

  public Playlist(String name, List<Song> songList){
    this.name = name;
    this.songList = songList;
  }

  public Playlist(String id, String name, List<Song> songList){
    this(name, songList);
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public List<Song> getSongList() {
    return songList.stream().collect(Collectors.toList());
  }

  public void addSong(List<Song> songs){
    for(Song song: songs){
      if(!songList.contains(song)){
        songList.add(song);
      }
    }
  }

  public void removeSongs(List<Song> songs){
    for(Song song: songs){
      songList.remove(song);
    }
  }

  public void setCurrentSongIndex(int currentSongIndex){
    this.currentSongIndex = currentSongIndex;
  }

  public int getCurrentSongIndex(){
    return currentSongIndex;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Playlist)) {
      return false;
    }
    Playlist playlist = (Playlist) o;
    return id.equals(playlist.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }

  @Override
  public String toString() {
    return "Playlist{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", songList=" + songList +
        '}';
  }
}
