package com.crio.jukebox.services;

import com.crio.jukebox.entities.Album;
import com.crio.jukebox.entities.Artist;
import com.crio.jukebox.entities.Song;
import java.util.List;

public interface IAlbumService {
  public Album create(String name, Artist owner);
  public void addSong(String albumName, Song song);
  public List<Album> getAllAlbums();
}
