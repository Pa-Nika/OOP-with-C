import Operations.PUSH;
import com.Main.Context;
import com.Main.Parser;
import com.Main.RecordWord;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class PUSHTest {
    @Test
    public void TestNormalPush() throws Exception {
        Context context = new Context();
        ArrayList<RecordWord> all_operations = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Parser parser = new Parser(reader);

        parser.parsLine("PUSH 3", all_operations);
        Double answer = 3.0;
        PUSH push = new PUSH();
        for(RecordWord rw : all_operations) {
            push.work(context, rw);
        }

        assertEquals(answer, context.getStack().pop());
    }

    @Test
    public void TestBadArgs1() throws Exception {
        Context context = new Context();
        ArrayList<RecordWord> all_operations = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Parser parser = new Parser(reader);

        parser.parsLine("PUSH 3 a", all_operations);
        PUSH push = new PUSH();
        Exception exception = assertThrows(Exception.class, () -> {
            for(RecordWord rw : all_operations) {
                push.work(context, rw);
            }
        });
        String answer = "Exception: incorrect number of arguments for the operation";

        assertEquals(answer, exception.getMessage());
    }

    @Test
    public void TestBadArgs2() throws Exception {
        Context context = new Context();
        ArrayList<RecordWord> all_operations = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Parser parser = new Parser(reader);

        parser.parsLine("PUSH", all_operations);
        PUSH push = new PUSH();
        Exception exception = assertThrows(Exception.class, () -> {
            for(RecordWord rw : all_operations) {
                push.work(context, rw);
            }
        });
        String answer = "Exception: incorrect number of arguments for the operation";

        assertEquals(answer, exception.getMessage());
    }
}