package com.crio.jukebox.commands;

import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.repository.IUserRepository;
import com.crio.jukebox.services.IPlaylistService;
import com.crio.jukebox.services.IUserService;
import java.util.ArrayList;
import java.util.List;

public class CreatePlaylistCommand implements ICommand{

  private IPlaylistService playlistService;

  public CreatePlaylistCommand(IPlaylistService playlistService){
    this.playlistService = playlistService;
  }

  @Override
  public void execute(List<String> tokens) {
    String userId = tokens.get(1);
    String playlistName = tokens.get(2);
    List<String> songIds = new ArrayList<>();
    for(int i = 3; i < tokens.size(); i++){
      songIds.add(tokens.get(i));
    }
    Playlist playlist = playlistService.create(userId, playlistName, songIds);
    System.out.println("Playlist ID - "+playlist.getId());
  }
}
