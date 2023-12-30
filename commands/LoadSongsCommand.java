package com.crio.jukebox.commands;

import com.crio.jukebox.entities.Album;
import com.crio.jukebox.entities.Artist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.services.IAlbumService;
import com.crio.jukebox.services.IArtistService;
import com.crio.jukebox.services.ISongService;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LoadSongsCommand implements ICommand{

  private ISongService songService;
  private IArtistService artistService;
  private IAlbumService albumService;

  public LoadSongsCommand(ISongService songService, IArtistService artistService,
      IAlbumService albumService){

    this.songService = songService;
    this.artistService = artistService;
    this.albumService = albumService;
  }


  @Override
  public void execute(List<String> tokens){
    String filename = tokens.get(1);

    try(BufferedReader br = new BufferedReader(new FileReader(filename))){
      String line;
      while((line = br.readLine()) != null){
        String[] records = line.split(",");
        String songName = records[0];
        String genre = records[1];
        String albumName = records[2];
        String owner = records[3];
        String[] artists = records[4].split("#");
        List<Artist> artistList = new ArrayList<>();
        for(String art: artists) {
          artistList.add(artistService.create(art));
        }
        Song song = songService.create(songName, genre, artistList);
        Album album = albumService.create(albumName, artistList.get(0));
        albumService.addSong(albumName, song);
      }

      System.out.println("Songs Loaded successfully");
    } catch (IOException e) {
      e.printStackTrace();
    }


  }
}
