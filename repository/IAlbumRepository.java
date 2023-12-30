package com.crio.jukebox.repository;

import com.crio.jukebox.entities.Album;
import com.crio.jukebox.entities.Song;
import java.util.Optional;

public interface IAlbumRepository extends CrudRepository<Album, String> {

  public void addSong(String albumName, Song song);
  public Optional<Album> findAlbumBySongId(String id);
}
