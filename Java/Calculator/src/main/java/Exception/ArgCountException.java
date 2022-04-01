package Exception;

public class ArgCountException extends SyntaxException{
    public ArgCountException() {
        super ("Exception: incorrect number of arguments for the operation");
    }
}
