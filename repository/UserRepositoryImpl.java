package com.crio.jukebox.repository;

import com.crio.jukebox.entities.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserRepositoryImpl implements IUserRepository{

  private Map<String, User> userMap;
  private Integer autoIncrement = 0;

  public UserRepositoryImpl(){
    userMap = new HashMap<>();
  }

  public UserRepositoryImpl(Map<String, User> userMap){
    this.userMap = userMap;
    this.autoIncrement = userMap.size();
  }
  @Override
  public User save(User entity) {
    if(entity.getId() == null){
      autoIncrement++;
      User user = new User(String.valueOf(autoIncrement), entity.getName());
      userMap.put(user.getId(), user);
      return user;
    }
    userMap.put(entity.getId(), entity);
    return entity;
  }

  @Override
  public List<User> findAll() {
    return userMap.values().stream().collect(Collectors.toList());
  }

  @Override
  public Optional<User> findById(String s) {
    return Optional.ofNullable(userMap.get(s));
  }

  @Override
  public boolean existById(String s) {
    return userMap.containsKey(s);
  }

  @Override
  public void delete(User entity) {
    if(userMap.containsKey(entity.getId())){
      userMap.remove(entity.getId());
    }
  }

  @Override
  public void deleteById(String s) {
    if(userMap.containsKey(s)){
      userMap.remove(s);
    }
  }

  @Override
  public long count() {
    return userMap.size();
  }

  @Override
  public Optional<User> findByName(String name) {
    return userMap.values().stream().filter(user -> user.getName().equals(name)).findAny();
  }
}
