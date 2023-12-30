package com.crio.jukebox.services;

import com.crio.jukebox.entities.Artist;
import com.crio.jukebox.entities.Song;
import java.util.List;

public interface ISongService {
  public Song create(String name, String genre, List<Artist> artistList);
  public List<Song> getAllSongs();
}
