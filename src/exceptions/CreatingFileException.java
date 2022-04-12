package exceptions;
/**
 * thrown when unable to create file
 */
public class CreatingFileException extends FileException{
    public CreatingFileException(){
        super("cannot create file");
    }
}
