package com.crio.jukebox.exceptions;

public class UnSupportedOperationException extends Exception {

  String message;
  public UnSupportedOperationException(String message){
    this.message = message;
  }
}
