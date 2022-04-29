package main;

import collection.CollectionManager;
import collection.HumanBeingCollectionManager;
import commands.CommandManager;
import data.HumanBeing;
import file.FileManager;
import io.ConsoleInputManager;
import io.InputManager;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.Map;

import static io.OutputManager.print;
public class Main {

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        FileManager fileManager = new FileManager();
        CollectionManager<HumanBeing> collectionManager = new HumanBeingCollectionManager();
        Map<String, String> env = System.getenv();
        String path = env.get("COLLECTION_PATH");
        if (args.length!=0){

            fileManager.setPath(args[0]);
            LinkedList<HumanBeing> data = fileManager.load();
            if (data.size()!=0) {
                collectionManager.load(data);
            }
            else{
                print("nothing to load, creating new collection");
            }
        } else{
            print("no file passed by env var");
        }
        
        InputManager consoleManager = new ConsoleInputManager();
        CommandManager commandManager = new CommandManager(collectionManager,consoleManager,fileManager);
        commandManager.consoleMode();    
    }
}
