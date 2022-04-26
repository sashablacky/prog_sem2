package file;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import data.HumanBeing;
import exceptions.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.LinkedList;

import static io.OutputManager.print;
import static io.OutputManager.printErr;

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

    public LinkedList read(){
        String res = "";
        try{
            if (path == null) throw new NoPathException();
            BufferedInputStream reader = null;
            File file = new File(path);
            if (!file.exists()) throw new FileDoesNotExistException();
            if(!file.canRead()) throw new FileWrongPermissionsException("cannot read file");
            StringBuilder sb = new StringBuilder();
            XmlMapper xmlMapper = new XmlMapper();
            String xmlString = read_xml_String(file);
            LinkedList value = xmlMapper.readValue(read_xml_string(bis), LinkedList.class);
            bis.close();
            return value;
        }
        catch(FileException e){
            printErr(e.getMessage());
        }  catch(IOException e){
            printErr("File could not be accessed");
        }
    return value;
    }
    private String read_xml_String(File file){
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        byte[] bytes = bis.readAllBytes();
        String res = new String(bytes, StandardCharsets.UTF_8);
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
            XmlMapper mapper = new XmlMapper();
            mapper.writeValue(fos, collection);
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
