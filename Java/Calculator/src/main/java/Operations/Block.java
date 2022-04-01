package Operations;

import com.Main.Context;
import com.Main.RecordWord;

import Exception.MyException;

public interface Block {
    void work (Context context, RecordWord operation) throws MyException;
}
