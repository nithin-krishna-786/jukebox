package com.crio.jukebox.commands;

import com.crio.jukebox.dtos.SongDto;
import com.crio.jukebox.services.IJukeBoxService;
import java.util.List;

public class PlaySongCommand implements ICommand{

  private IJukeBoxService jukeBoxService;

  public PlaySongCommand(IJukeBoxService jukeBoxService){
    this.jukeBoxService = jukeBoxService;
  }

  @Override
  public void execute(List<String> tokens) {
    String uid = tokens.get(1);
    String tobePlayed = tokens.get(2);
    SongDto songDto = null;
    switch (tobePlayed){
      case "NEXT":{
        songDto = jukeBoxService.playNextSongInActivePlaylist(uid);
        break;
      }
      case "BACK":{
        songDto = jukeBoxService.playPrevSongInActivePlaylist(uid);
        break;
      }
      default:{
        songDto = jukeBoxService.playSelectedSong(uid, tobePlayed);
        if(songDto.getSong() == null){
          System.out.println("Given song id is not a part of the active playlist");
          return;
        }
      }

    }

    System.out.println("Current Song Playing");
    System.out.println("Song - "+songDto.getSong());
    System.out.println("Album - "+songDto.getAlbum());
    System.out.println("Artists - "+songDto.getArtists());
  }
}
