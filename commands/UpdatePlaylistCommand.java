package com.crio.jukebox.commands;

import com.crio.jukebox.dtos.PlayListUpdateDto;
import com.crio.jukebox.services.IPlaylistService;
import java.util.List;

public class UpdatePlaylistCommand implements ICommand{

  private IPlaylistService playlistService;

  public UpdatePlaylistCommand(IPlaylistService playlistService){
    this.playlistService = playlistService;
  }

  @Override
  public void execute(List<String> tokens) {
    String command = tokens.get(1);
    String uid = tokens.get(2);
    String pid = tokens.get(3);
    List<String> songIds = tokens.subList(4, tokens.size());
    PlayListUpdateDto updateDto = null;
    switch (command){
      case "ADD-SONG":{
        updateDto = playlistService.addSongsAndUpdatePlaylist(uid, pid, songIds);
        System.out.println("Playlist ID - "+updateDto.getId());
        System.out.println("Playlist Name - "+updateDto.getName());
        System.out.println("Song IDs - "+updateDto.getSongIds());
        break;
      }
      case "DELETE-SONG":{
        updateDto = playlistService.deleteSongsAndUpdatePlaylist(uid, pid, songIds);
        System.out.println("Playlist ID - "+updateDto.getId());
        System.out.println("Playlist Name - "+updateDto.getName());
        System.out.println("Song IDs - "+updateDto.getSongIds());
        break;
      }
    }

  }
}
