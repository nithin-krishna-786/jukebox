package com.crio.jukebox.services;

import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.User;
import java.util.List;

public interface IUserService {
  public User create(String name);
  public User addPlaylist(String uid, Playlist playlist);
  public List<Playlist> getAllPlaylist(String uid);
}
