package Exception;

public class NotArgOnStack extends MathException{
    public NotArgOnStack() {
        super("Exception: not enough arguments on the stack");
    }
}
