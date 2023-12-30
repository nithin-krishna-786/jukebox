package com.crio.jukebox;

import com.crio.codingame.appConfig.ApplicationConfig;
import com.crio.codingame.exceptions.NoSuchCommandException;
import com.crio.jukebox.commands.CommandInvoker;
import com.crio.jukebox.configs.AppConfig;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


public class App {
    // To run the application  ./gradlew run --args="INPUT_FILE=jukebox-input.txt"
	public static void main(String[] args) {
		List<String> commandLineArgs = new LinkedList<>(Arrays.asList(args));
        String expectedSequence = "INPUT_FILE";
        String actualSequence = commandLineArgs.stream()
                .map(a -> a.split("=")[0])
                .collect(Collectors.joining("$")).trim();

        if(expectedSequence.equals(actualSequence)){
            run(commandLineArgs);
        }


	}

    // public static void run(List<String> commandLineArgs) {
    // // Complete the final logic to run the complete program.
    //   String filename = commandLineArgs.get(0).split("=")[1];
    //   File file = Paths.get(filename).toFile();
    //   AppConfig appConfig = new AppConfig();
    //   CommandInvoker commandInvoker = appConfig.getCommandInvoker();

    //   try(BufferedReader br = new BufferedReader(new FileReader(file))){

    //     String line;
    //     while((line = br.readLine()) != null){

    //       List<String> tokens = Arrays.stream(line.split(" ")).collect(Collectors.toList());
    //       commandInvoker.executeCommand(tokens.get(0), tokens);
    //     }

    //   } catch (Exception e) {
    //     e.printStackTrace();
    //   }

    // }
    public static void run(List<String> commandLineArgs) {
      // Complete the final logic to run the complete program.
      AppConfig applicationConfig = new AppConfig();
      CommandInvoker commandInvoker = applicationConfig.getCommandInvoker();
      BufferedReader reader;
      String inputFile = commandLineArgs.get(0).split("=")[1];
      commandLineArgs.remove(0);
      try {
          reader = new BufferedReader(new FileReader(inputFile));
          String line = reader.readLine();
          while (line != null) {
              List<String> tokens = Arrays.asList(line.split(" "));

              commandInvoker.executeCommand(tokens.get(0),tokens);
              // read next line
              line = reader.readLine();
          }
          reader.close();
      } catch (IOException | NoSuchCommandException e) {
          e.printStackTrace();
      }
  }
}
