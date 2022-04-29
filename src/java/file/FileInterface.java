package file;

import data.HumanBeing;

import java.util.Collection;
import java.util.LinkedList;

public interface FileInterface {
    /**
     * set path to file
     * @param path
     */
    void setPath(String path);

    /**
     * read data
     * @return
     */
    LinkedList load();
    String read(String ScriptPath);
    /**
     * write data
     * @param collection
     */
    boolean write(Collection<HumanBeing> collection);
}
