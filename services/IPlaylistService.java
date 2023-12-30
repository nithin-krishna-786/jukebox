package com.crio.jukebox.services;

import com.crio.jukebox.dtos.PlayListUpdateDto;
import com.crio.jukebox.entities.Playlist;
import java.util.List;

public interface IPlaylistService {

  public Playlist create(String uid, String name, List<String> songIds);
  public void delete(String uid, String pid);
  public PlayListUpdateDto addSongsAndUpdatePlaylist(String uid, String pid, List<String> songIds);
  public PlayListUpdateDto deleteSongsAndUpdatePlaylist(String uid, String pid, List<String> songIds);

}
