package com.crio.jukebox.services;

import com.crio.jukebox.entities.Album;
import com.crio.jukebox.entities.Artist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.repository.AlbumRepositoryImpl;
import com.crio.jukebox.repository.IAlbumRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AlbumServiceImpl implements IAlbumService{

  private IAlbumRepository albumRepository;

  public AlbumServiceImpl(IAlbumRepository albumRepository){
    this.albumRepository = albumRepository;
  }

  @Override
  public Album create(String name, Artist owner) {
    if(albumRepository.existById(name)){
      Album album = albumRepository.findById(name).get();
      return albumRepository.save(album);
    }
    Album album = new Album(name, owner);
    Album newAlbum = albumRepository.save(album);
    return newAlbum;
  }

  @Override
  public void addSong(String albumName, Song song) {
    Optional<Album> album = albumRepository.findById(albumName);
    if(album.isPresent()){
      albumRepository.addSong(albumName, song);
    }
  }

  @Override
  public List<Album> getAllAlbums() {
    return albumRepository.findAll().stream().collect(Collectors.toList());
  }
}
