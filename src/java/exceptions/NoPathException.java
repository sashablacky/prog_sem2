package exceptions;

public class NoPathException extends FileException{
    public NoPathException(){
        super("path is empty");
    }
}
