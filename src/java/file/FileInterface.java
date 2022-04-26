package file;

import data.HumanBeing;

import java.util.Collection;
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
    public String read();

    /**
     * write data
     * @param collection
     */
    public boolean write(Collection<HumanBeing> collection);
}
