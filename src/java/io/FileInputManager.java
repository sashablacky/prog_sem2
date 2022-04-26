package io;

import file.FileManager;

import java.util.Scanner;
/**
 * Operates input
 */
public class FileInputManager extends InputManagerImpl{
    public FileInputManager(String path){
        super(new Scanner(new FileManager().read(path)));
        getScanner().useDelimiter("\n");
    }
}
