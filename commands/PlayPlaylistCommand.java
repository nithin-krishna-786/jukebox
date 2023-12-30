package com.crio.jukebox.commands;

import com.crio.jukebox.dtos.SongDto;
import com.crio.jukebox.services.IJukeBoxService;
import java.util.List;

public class PlayPlaylistCommand implements ICommand{

  private IJukeBoxService jukeBoxService;
  public PlayPlaylistCommand(IJukeBoxService jukeBoxService){
    this.jukeBoxService = jukeBoxService;
  }

  @Override
  public void execute(List<String> tokens) {
    String uid = tokens.get(1);
    String pid = tokens.get(2);
    SongDto songDto = jukeBoxService.selectAndPlayPlaylist(uid, pid);
    System.out.println("Current Song Playing");
    System.out.println("Song - "+songDto.getSong());
    System.out.println("Album - "+songDto.getAlbum());
    System.out.println("Artists - "+songDto.getArtists());
  }
}
