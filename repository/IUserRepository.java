package com.crio.jukebox.repository;

import com.crio.jukebox.entities.User;
import java.util.Optional;

public interface IUserRepository extends CrudRepository<User, String> {
  public Optional<User> findByName(String name);
}
