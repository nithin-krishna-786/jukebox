package com.crio.jukebox.exceptions;

public class PlaylistNotExistException extends RuntimeException{

  public PlaylistNotExistException(){
    super();
  }

  public PlaylistNotExistException(String message){
    super(message);
  }

}
