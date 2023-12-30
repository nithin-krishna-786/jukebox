package com.crio.jukebox.dtos;

public class PlayListUpdateDto {

  private String id;
  private String name;
  private String songIds;

  public PlayListUpdateDto(String id, String name, String songIds){
    this.id = id;
    this.name = name;
    this.songIds = songIds;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getSongIds() {
    return songIds;
  }
}
