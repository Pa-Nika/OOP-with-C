package Exception;

public class NotDefineVar extends SyntaxException{
    public NotDefineVar() {
        super("Exception: variable is not defined");
    }
}
