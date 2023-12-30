package com.crio.jukebox.services;

import com.crio.jukebox.dtos.SongDto;
import com.crio.jukebox.entities.Playlist;

public interface IJukeBoxService {
  public SongDto selectAndPlayPlaylist(String uid, String pid);
  public SongDto playNextSongInActivePlaylist(String uid);
  public SongDto playPrevSongInActivePlaylist(String uid);
  public SongDto playSelectedSong(String uid, String songId);
}
