package com.crio.jukebox.commands;

import com.crio.jukebox.exceptions.CommandNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandInvoker {

  private Map<String, ICommand> commandMap = new HashMap<>();

  public void registerCommand(String commandName, ICommand command){
    commandMap.put(commandName, command);
  }

  public void executeCommand(String commandName, List<String> tokens){
    ICommand command = commandMap.get(commandName);
    if(command == null){
      throw new CommandNotFoundException();
    }

    command.execute(tokens);
  }

}
