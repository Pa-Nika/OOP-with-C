package Operations;

import com.Main.Context;
import com.Main.RecordWord;
import Exception.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class POP implements Block {
    private static final Logger log = Logger.getLogger(POP.class.getName());

    @Override
    public void work(Context context, RecordWord operation) throws MyException {
        if (context.getStack().isEmpty()) {
            log.log(Level.SEVERE, "Exception: not enough arguments on the stack");
            throw new NotArgOnStack();
        }

        Double a = context.getStack().pop();
        log.info("Pop " + a);
    }
}
