import Factory.Calculator;
import Operations.DEFINE;
import com.Main.Context;
import com.Main.Parser;
import com.Main.RecordWord;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class DEFINETest {
    @Test
    public void TestNormalDefine() throws Exception {
        Context context = new Context();
        ArrayList<RecordWord> all_operations = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Parser parser = new Parser(reader);

        parser.parsLine("DEFINE a 3", all_operations);

        DEFINE define = new DEFINE();
        for(RecordWord rw : all_operations) {
            define.work(context, rw);
        }
        Double answer = 3.0;

        assertEquals(answer, context.getDefine().get("a"));
    }

    @Test
    public void TestBadArgs() throws Exception {
        Context context = new Context();
        ArrayList<RecordWord> all_operations = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Parser parser = new Parser(reader);

        parser.parsLine("DEFINE a", all_operations);
        DEFINE define = new DEFINE();
        Exception exception = assertThrows(Exception.class, () -> {
            for(RecordWord rw : all_operations) {
                define.work(context, rw);
            }
        });
        String answer = "Exception: incorrect number of arguments for the operation";
        assertEquals(answer, exception.getMessage());
    }

    @Test
    public void TestNotDefinedVar() throws Exception {
        Context context = new Context();
        ArrayList<RecordWord> all_operations = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Parser parser = new Parser(reader);

        parser.parsLine("DEFINE a 3", all_operations);
        parser.parsLine("PUSH b", all_operations);

        Calculator calculator = new Calculator(all_operations, context);

        Exception exception = assertThrows(Exception.class, calculator::work);
        String answer = "Exception: variable is not defined";
        assertEquals(answer, exception.getMessage());
    }

    @Test
    public void TestNotNumArg() throws Exception {
        Context context = new Context();
        ArrayList<RecordWord> all_operations = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Parser parser = new Parser(reader);

        parser.parsLine("DEFINE a b", all_operations);
        DEFINE define = new DEFINE();
        Exception exception = assertThrows(Exception.class, () -> {
            for(RecordWord rw : all_operations) {
                define.work(context, rw);
            }
        });

        String answer = "Exception: incorrect record of DEFINE operation";
        assertEquals(answer, exception.getMessage());
    }
}