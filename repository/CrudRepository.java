package com.crio.jukebox.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID> {
  T save(T entity);
  List<T> findAll();
  Optional<T> findById(ID id);
  boolean existById(ID id);
  void delete(T entity);
  void deleteById(ID id);
  long count();
}
