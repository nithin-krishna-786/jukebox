package com.crio.jukebox.dtos;

import com.crio.jukebox.entities.Song;
import java.util.List;

public class SongDto {

  private String song;
  private String album;
  private String artists;

  public SongDto(){}

  public SongDto(String song, String album, String artists){
    this.song = song;
    this.album = album;
    this.artists = artists;
  }

  public String getSong() {
    return song;
  }

  public String getAlbum() {
    return album;
  }

  public String getArtists() {
    return artists;
  }
}
