package file;

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
     * @param data
     */
    public boolean write(String data);
}
