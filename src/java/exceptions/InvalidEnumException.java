package exceptions;
/**
 * thrown when input is not included in enum
 */
public class InvalidEnumException extends InvalidDataException{
    public InvalidEnumException(){
        super("Wrong enum");
    }
}
