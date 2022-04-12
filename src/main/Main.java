package main;
import data.*;
import file.FileManager;
import io.*;

import java.io.PrintStream;
import collection.CollectionManager;
import collection.HumanBeingCollectionManager;
import java.util.Map;

import static io.OutputManager.*;

import commands.*;
public class Main {
    
    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        FileManager fileManager = new FileManager();
        CollectionManager<HumanBeing> collectionManager = new HumanBeingCollectionManager();
        Map<String, String> env = System.getenv();
        String path = env.get("COLLECTION_PATH");
        if (args.length!=0){
            fileManager.setPath(args[0]);
            collectionManager.deserializeCollection(path);
        } else{
            print("no file passed by argument. you can load file using load command");
        }
        
        InputManager consoleManager = new ConsoleInputManager();
        CommandManager commandManager = new CommandManager(collectionManager,consoleManager,fileManager);
        commandManager.consoleMode();    
    }
}
