package Operations;

import com.Main.Context;
import com.Main.RecordWord;
import Exception.*;


import java.util.logging.Level;
import java.util.logging.Logger;

public class DEFINE implements Block {
    private static final Logger log = Logger.getLogger(DEFINE.class.getName());

    @Override
    public void work (Context context, RecordWord operation) throws MyException {
        if (operation.getFirstArg() == null || operation.getSecondArg() == null) {
            log.log(Level.SEVERE, "Exception: not enough arguments on the stack");
            throw new ArgCountException();
        }

        context.setDefineValue(operation.getFirstArg(), operation.getSecondArg());
        log.info("Define " + operation.getFirstArg() + " " + operation.getSecondArg());
    }
}
