package exceptions;
/**
 * thrown when script call loops
 */
public class RecursiveScriptExecuteException extends CommandException{
    public RecursiveScriptExecuteException(){
        super("Recursive script execution attempt");
    }
}
