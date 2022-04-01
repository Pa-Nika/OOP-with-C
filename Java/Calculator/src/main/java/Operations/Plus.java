package Operations;

import com.Main.Context;
import com.Main.RecordWord;
import Exception.*;

import java.util.EmptyStackException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Plus implements Block {
    private static final Logger log = Logger.getLogger(Plus.class.getName());

    @Override
    public void work(Context context, RecordWord operation) throws MyException {
        Double a, b;
        try {
            a = context.getStack().pop();
            b = context.getStack().pop();
        }catch (EmptyStackException e) {
            log.log(Level.SEVERE, "Exception: not enough arguments on the stack");
            throw new NotArgOnStack();
        }

        double answer = a + b;

        context.setStackNum(Double.toString(answer));
        log.info( a + " + " + b);
    }
}
