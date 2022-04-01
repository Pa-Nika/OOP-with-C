package Operations;

import com.Main.Context;
import com.Main.RecordWord;
import Exception.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PUSH implements Block {
    private static final Logger log = Logger.getLogger(PUSH.class.getName());

    @Override
    public void work(Context context, RecordWord operation) throws MyException {
        if (operation.getSecondArg() != null) {
            log.log(Level.SEVERE, "Exception: not enough arguments on the stack");
            throw new ArgCountException();
        }

        context.setStackNum(operation.getFirstArg());
        log.info("Push " + operation.getFirstArg());
    }
}
