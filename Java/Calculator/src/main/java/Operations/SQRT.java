package Operations;

import com.Main.Context;
import com.Main.RecordWord;
import Exception.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Math.sqrt;

public class SQRT implements Block {
    private static final Logger log = Logger.getLogger(SQRT.class.getName());

    @Override
    public void work(Context context, RecordWord operation) throws MyException{
        if (context.getStack().isEmpty()) {
            log.log(Level.SEVERE, "Exception: not enough arguments on the stack");
            throw new NotArgOnStack();
        }

        Double a = context.getStack().pop();
        if (a < 0) {
            log.log(Level.SEVERE, "Exception: trying to take the SQRT of a negative number");
            throw new SqrtOfNegativeNum();
        }

        log.info("Sqrt of " + a);
        a = sqrt(a);
        context.setStackNum(a.toString());
    }
}
