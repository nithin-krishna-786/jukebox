package com.crio.jukebox.services;

import com.crio.jukebox.entities.Artist;
import com.crio.jukebox.repository.ArtistRepositoryImpl;
import com.crio.jukebox.repository.IArtistRepository;

public class ArtistServiceImpl implements IArtistService{

  private IArtistRepository artistRepository;

  public ArtistServiceImpl(IArtistRepository artistRepository){
    this.artistRepository = artistRepository;
  }

  @Override
  public Artist create(String name) {
    Artist artist = new Artist(name);
    Artist a = artistRepository.save(artist);
    return a;
  }
}
