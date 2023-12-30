package com.crio.jukebox.commands;

import com.crio.jukebox.services.IPlaylistService;
import java.util.List;

public class DeletePlaylistCommand implements ICommand{

  private IPlaylistService playlistService;

  public DeletePlaylistCommand(IPlaylistService playlistService){
    this.playlistService = playlistService;
  }

  @Override
  public void execute(List<String> tokens) {
    String uid = tokens.get(1);
    String pid = tokens.get(2);
    playlistService.delete(uid, pid);
    System.out.println("Delete Successful");
  }
}
