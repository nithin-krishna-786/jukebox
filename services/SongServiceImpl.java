package com.crio.jukebox.services;

import com.crio.jukebox.entities.Artist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.repository.ISongRepository;
import java.util.List;
import java.util.stream.Collectors;

public class SongServiceImpl implements ISongService {

  private ISongRepository songRepository;

  public SongServiceImpl(ISongRepository songRepository){
    this.songRepository = songRepository;
  }

  @Override
  public Song create(String name, String genre, List<Artist> artistList) {
    Song song = new Song(name, genre, artistList);
    Song s = songRepository.save(song);
    return s;
  }

  @Override
  public List<Song> getAllSongs() {
    return songRepository.findAll().stream().collect(Collectors.toList());
  }
}
