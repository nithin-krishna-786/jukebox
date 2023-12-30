package com.crio.jukebox.configs;

import com.crio.jukebox.commands.CommandInvoker;
import com.crio.jukebox.commands.CreatePlaylistCommand;
import com.crio.jukebox.commands.CreateUserCommand;
import com.crio.jukebox.commands.DeletePlaylistCommand;
import com.crio.jukebox.commands.ICommand;
import com.crio.jukebox.commands.LoadSongsCommand;
import com.crio.jukebox.commands.PlayPlaylistCommand;
import com.crio.jukebox.commands.PlaySongCommand;
import com.crio.jukebox.commands.UpdatePlaylistCommand;
import com.crio.jukebox.entities.Album;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.repository.AlbumRepositoryImpl;
import com.crio.jukebox.repository.ArtistRepositoryImpl;
import com.crio.jukebox.repository.IAlbumRepository;
import com.crio.jukebox.repository.IArtistRepository;
import com.crio.jukebox.repository.IPlaylistRepository;
import com.crio.jukebox.repository.ISongRepository;
import com.crio.jukebox.repository.IUserRepository;
import com.crio.jukebox.repository.PlaylistRepositoryImpl;
import com.crio.jukebox.repository.SongRepositoryImpl;
import com.crio.jukebox.repository.UserRepositoryImpl;
import com.crio.jukebox.services.AlbumServiceImpl;
import com.crio.jukebox.services.ArtistServiceImpl;
import com.crio.jukebox.services.IAlbumService;
import com.crio.jukebox.services.IArtistService;
import com.crio.jukebox.services.IJukeBoxService;
import com.crio.jukebox.services.IPlaylistService;
import com.crio.jukebox.services.ISongService;
import com.crio.jukebox.services.IUserService;
import com.crio.jukebox.services.JukeBoxServiceImpl;
import com.crio.jukebox.services.PlaylistServiceImpl;
import com.crio.jukebox.services.SongServiceImpl;
import com.crio.jukebox.services.UserServiceImpl;
import java.util.List;

public class AppConfig {

  private final IUserRepository userRepository = new UserRepositoryImpl();
  private final IArtistRepository artistRepository = new ArtistRepositoryImpl();
  private final ISongRepository songRepository = new SongRepositoryImpl();
  private final IAlbumRepository albumRepository = new AlbumRepositoryImpl();
  private final IPlaylistRepository playlistRepository = new PlaylistRepositoryImpl();

  private final IUserService userService = new UserServiceImpl(userRepository);
  private final IArtistService artistService = new ArtistServiceImpl(artistRepository);
  private final ISongService songService = new SongServiceImpl(songRepository);
  private final IAlbumService albumService = new AlbumServiceImpl(albumRepository);
  private final IPlaylistService playlistService = new PlaylistServiceImpl(
      userRepository,
      playlistRepository,
      songRepository
  );
  private final IJukeBoxService jukeBoxService = new JukeBoxServiceImpl(
      albumRepository,
      playlistRepository,
      userRepository
  );

  private final CreateUserCommand createUserCommand = new CreateUserCommand(userService);
  private final LoadSongsCommand loadSongsCommand = new LoadSongsCommand(
      songService,
      artistService,
      albumService
  );
  private final CreatePlaylistCommand createPlaylistCommand = new CreatePlaylistCommand(
      playlistService
  );

  private final DeletePlaylistCommand deletePlaylistCommand = new DeletePlaylistCommand(
      playlistService
  );

  private final PlayPlaylistCommand playPlaylistCommand = new PlayPlaylistCommand(
      jukeBoxService
  );

  private final PlaySongCommand playSongCommand = new PlaySongCommand(jukeBoxService);

  private final UpdatePlaylistCommand updatePlaylistCommand = new UpdatePlaylistCommand(
      playlistService
  );

  private final CommandInvoker commandInvoker = new CommandInvoker();

  public CommandInvoker getCommandInvoker(){
    commandInvoker.registerCommand("LOAD-DATA", loadSongsCommand);
    commandInvoker.registerCommand("CREATE-USER", createUserCommand);
    commandInvoker.registerCommand("CREATE-PLAYLIST", createPlaylistCommand);
    commandInvoker.registerCommand("DELETE-PLAYLIST", deletePlaylistCommand);
    commandInvoker.registerCommand("PLAY-PLAYLIST", playPlaylistCommand);
    commandInvoker.registerCommand("PLAY-SONG", playSongCommand);
    commandInvoker.registerCommand("MODIFY-PLAYLIST", updatePlaylistCommand);
    return commandInvoker;
  }

  public List<Song> getAllSongs(){
    return songRepository.findAll();
  }

  public List<Album> getAllAlbums(){
    return albumRepository.findAll();
  }

  public List<User> getAllUsers(){
    return userRepository.findAll();
  }

  public List<Playlist> getAllPlaylists(){
    return playlistRepository.findAll();
  }

}
