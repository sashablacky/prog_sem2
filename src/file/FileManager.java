package file;

import static io.OutputManager.*;
import exceptions.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileWriter;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.processing.FilerException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;

public class FileManager implements FileInterface{
    private InputStream inputStream;
    private String path;

    public FileManager(String pth){
        path = pth;
    }

    public void setPath(String pth){
        path = pth;
    }

    public FileManager(){
        path = null;
    }

    public String read(){
        String res = "";
        try{
            if (path == null) throw new NoPathException();
            BufferedInputStream reader = null;
            File file = new File(path);
            if (!file.exists()) throw new FileDoesNotExistException();
            if(!file.Readable()) throw new FileWrongPermissionsException("cannot read file");
            reader = new BufferedInputStream(new FileInputStream(file));
            int curSymbol;
            while ((curSymbol = reader.read()) != -1) {
                res += ((char)curSymbol);
            }
            reader.close();     
        }
        catch(FileException e){
            printErr(e.getMessage());
        }  catch(IOException e){
            printErr("File could not be accessed");
        }
        return res;
    }

    private void create(File file) throws CreatingFileException{
        try{
            file.createNewFile(); 
        } catch(IOException e){
            throw new CreatingFileException();
        }
    }

}
