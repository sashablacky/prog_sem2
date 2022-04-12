package commands;

import static io.OutputManager.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import collection.CollectionManager;
import data.*;
import exceptions.*;
import file.FileManager;
import file.ReaderWriter;
import io.*;
public class CommandManager implements Commandable{
    private Map<String,Command> map;
    private CollectionManager<HumanBeing> collectionManager;
    private InputManager inputManager;
    private FileManager fileManager;
    private boolean isRunning;
    private String currentScriptFileName;

    private static Stack<String> callStack = new Stack<>();

    public void clearStack(){
        callStack.clear();
    }
    public CommandManager(CollectionManager<HumanBeing> cManager, InputManager iManager, FileManager fManager){

        isRunning = false;
        this.inputManager = iManager;
        this.collectionManager = cManager;
        this.fileManager = fManager;

        currentScriptFileName = "";
        map = new HashMap<String,Command>();
        addCommand("info", (a)->print(collectionManager.getInfo()));
        addCommand("help", (a)->print(getHelp()));
        addCommand("show", (a)->{
            if (collectionManager.getCollection().isEmpty()) print("Collection is empty");
            else print(collectionManager.serializeCollection());
        });
        addCommand("add", (a)->{
            collectionManager.add(inputManager.readHumanBeing());
        });
        addCommand("update", (arg)->{
            int id = 0;
            if (arg == null || arg.equals("")){
                throw new MissedCommandArgumentException();
            }
            try{
                id = Integer.parseInt(arg);
            } catch (NumberFormatException e){
                throw new InvalidCommandArgumentException("Id must be an integer");
            }
            if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();
            if (!collectionManager.checkID(id)) throw new InvalidCommandArgumentException("Element with this id does not exist");
            
            collectionManager.updateByID(id, inputManager.readHumanBeing());
            
        });
        addCommand("remove_by_id", (arg)->{
            int id = 0;
            if (arg == null || arg.equals("")){
                throw new MissedCommandArgumentException();
            }
            try{
                id = Integer.parseInt(arg);
            } catch (NumberFormatException e){
                throw new InvalidCommandArgumentException("Id must be an integer");
            }
            if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();
            if (!collectionManager.checkID(id)) throw new InvalidCommandArgumentException("Element with this id does not exist");
            
            collectionManager.removeByID(id);
            
        });
        addCommand("clear", (a)->{
            collectionManager.clear();
        });

        addCommand("save", (arg)->{
            if (!(arg == null ||arg.equals(""))) fileManager.setPath(arg);
            if (collectionManager.getCollection().isEmpty()) print("Collection is empty, nothing to save");
            if(!fileManager.write(collectionManager.serializeCollection())) throw new CommandException("Cannot save collection");
            
        });
        addCommand("execute_script",(arg)->{
            if (arg == null || arg.equals("")){
                throw new MissedCommandArgumentException();
            } 
            
            if (callStack.contains(currentScriptFileName)) throw new RecursiveScriptExecuteException();

            callStack.push(currentScriptFileName);
            CommandManager process = new CommandManager(collectionManager, inputManager, fileManager);
            process.fileMode(arg);
            callStack.pop();
            print("successfully executed script " + arg);
            
        });
        addCommand("exit", (a)->isRunning=false);
        addCommand("remove_first", (a)->{
            if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();
            collectionManager.removeFirst();
        });
        addCommand("add_if_max", (a)->collectionManager.addIfMax(inputManager.readWorker()));
        addCommand("add_if_min", (a)->collectionManager.addIfMin(inputManager.readWorker()));
        addCommand("group_counting_by_end_date", (a)->{
            if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();
            collectionManager.groupByEndDate();
        });
        addCommand("filter_starts_with_name", (arg)->{
            if (arg == null || arg.equals("")){
                throw new MissedCommandArgumentException();
            } else{
                if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();
                collectionManager.printStartsWithName(arg);
            }
        });
        addCommand("print_unique_salary", (a)->{
            if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();
            collectionManager.printUniqueSalary();
        });

        addCommand("load", (arg)->{
            if (!(arg == null ||arg.equals(""))) fileManager.setPath(arg);
            String data = fileManager.read();
            if(data.equals("")) throw new CommandException("cannot load, data not found");
            boolean success = collectionManager.deserializeCollection(data);
            if(success) print("collection successfully loaded");
        });
    }

    public void addCommand(String key, Command c){
        map.put(key, c);
    }

    public void runCommand(String s, String arg){
        try{
            if (! hasCommand(s)) throw new NoSuchCommandException();
            map.get(s).run(arg);
        } 
        catch(CommandException|InvalidDataException e){
            printErr(e.getMessage());
        }
    }
    public void runCommand(String s){
        runCommand(s,null);
    }
    public boolean hasCommand(String s){
        return map.containsKey(s);
    }
    public void consoleMode(){
        inputManager = new ConsoleInputManager();
        isRunning = true;
        while(isRunning){
            System.out.print("enter command (help to get command list): ");
            CommandWrapper pair = inputManager.readCommand();
            runCommand(pair.getCommand(), pair.getArg());
        }
    }
    public void fileMode(String path){
        currentScriptFileName = path;
        isRunning = true;
        inputManager = new FileInputManager(path);
        isRunning = true;
        while(isRunning && inputManager.getScanner().hasNextLine()){
            CommandWrapper pair = inputManager.readCommand();
            runCommand(pair.getCommand(), pair.getArg());
        }
    }

    public static String getHelp(){ 
        return String.join("\n", "help : вывести справку по доступным командам",
        "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)",
        "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении",
        "add {element} : добавить новый элемент в коллекцию",
        "update id {element} : обновить значение элемента коллекции, id которого равен заданному",
        "remove_by_id id : удалить элемент из коллекции по его id",
        "clear : очистить коллекцию",
        "save : сохранить коллекцию в файл",
        "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.",
        "exit : завершить программу (без сохранения в файл)",
        "remove_first : удалить первый элемент из коллекции",
        "remove_last : удалить последний элемент из коллекции",
        "shuffle : перемешать элементы коллекции в случайном порядке",
        "sum_of_minutes_of_waiting : вывести сумму значений поля minutesOfWaiting для всех элементов коллекции",
        "min_by_minutes_of_waiting : вывести любой объект из коллекции, значение поля minutesOfWaiting которого является минимальным",
        "print_unique_impact_speed : вывести уникальные значения поля impactSpeed всех элементов в коллекции)");
    }
}
