package Operations;

import com.Main.Context;
import com.Main.RecordWord;
import Exception.NotArgOnStack;
import Exception.ArgCountException;
import Exception.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PRINT implements Block {
    private static final Logger log = Logger.getLogger(PRINT.class.getName());

    @Override
    public void work(Context context, RecordWord operation) throws MyException {
        if (context.getStack().isEmpty()) {
            log.log(Level.SEVERE, "Exception: not enough arguments on the stack");
            throw new NotArgOnStack();
        }
        if (operation.getFirstArg() != null || operation.getSecondArg() != null) {
            log.log(Level.SEVERE, "Exception: incorrect number of arguments for the operation");
            throw new ArgCountException();
        }

        Double a = context.getStack().pop();
        System.out.println(a);
        context.setStackNum(a.toString());
        log.info("Print " + a);
    }
}
