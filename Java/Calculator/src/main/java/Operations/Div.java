package Operations;

import com.Main.Context;
import com.Main.RecordWord;
import Exception.NotArgOnStack;
import Exception.DivByZero;
import Exception.MyException;

import java.util.EmptyStackException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Div implements Block {
    private static final Logger log = Logger.getLogger(Div.class.getName());

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

        if (b == 0) {
            log.log(Level.SEVERE, "Exception: division by zero");
            throw new DivByZero();
        }

        double answer = a / b;

        context.setStackNum(Double.toString(answer));
        log.info(a + " / " + b);
    }
}
