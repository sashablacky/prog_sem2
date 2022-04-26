package file;

import data.HumanBeing;

import java.util.Collection;
import java.util.LinkedList;

public interface FileInterface {
    /**
     * set path to file
     * @param path
     */
    public void setPath(String path);

    /**
     * read data
     * @return
     */
    public LinkedList load();
    public String read(String ScriptPath);
    /**
     * write data
     * @param collection
     */
    public boolean write(Collection<HumanBeing> collection);
}
