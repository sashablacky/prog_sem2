package file;

import static io.OutputManager.*;
import exceptions.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;

import java.io.IOException;
import java.util.Collection;

import data.HumanBeing;

import java.io.BufferedInputStream;
import java.beans.ExceptionListener;
import java.beans.XMLEncoder;

public class FileManager implements FileInterface{
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
            if(!file.canRead()) throw new FileWrongPermissionsException("cannot read file");
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
    public boolean write(Collection<HumanBeing> collection){
        boolean res = true;
        try{
            if (path == null) throw new NoPathException();

            File file = new File(path);

            if(!file.exists()) {
                print("file " + path +" does not exist, trying to create it");
                create(file);
            };
            if(!file.canWrite()) throw new FileWrongPermissionsException("Cannot write file");
            FileOutputStream fos = new FileOutputStream(file);
            XMLEncoder encoder = new XMLEncoder(fos);
  encoder.setExceptionListener(new ExceptionListener() {
          public void exceptionThrown(Exception e) {
            System.out.println("Exception! :"+e.toString());
          }
  });
  encoder.writeObject(collection);
  encoder.close();
  fos.close();
        } catch(FileException e){
            printErr(e.getMessage());
            res = false;
        } catch (IOException e) {

            res = false;
            printErr("cannot access file");
        }
        return res;
    }
}
