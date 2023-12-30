package com.crio.jukebox.services;

import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.repository.IUserRepository;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements IUserService{

  private final IUserRepository userRepository;

  public UserServiceImpl(IUserRepository userRepository){
    this.userRepository = userRepository;
  }

  @Override
  public User create(String name) {
    User user = new User(name);
    User u = userRepository.save(user);
    return u;
  }

  @Override
  public User addPlaylist(String uid, Playlist playlist) {
    Optional<User> user = userRepository.findById(uid);
    User updatedUser = null;
    if(user.isPresent()){
      User u  = user.get();
      u.addPlaylist(playlist);
      updatedUser = userRepository.save(u);

    }
    return updatedUser;
  }

  @Override
  public List<Playlist> getAllPlaylist(String uid) {
    Optional<User> user = userRepository.findById(uid);
    return user.map(User::getPlaylists).orElse(null);

  }
}
