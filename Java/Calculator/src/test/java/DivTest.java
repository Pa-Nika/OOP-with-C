import Factory.Calculator;
import com.Main.Context;
import com.Main.Parser;
import com.Main.RecordWord;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class DivTest {
    @Test
    public void TestNormalDiv() throws Exception {
        Context context = new Context();
        ArrayList<RecordWord> all_operations = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Parser parser = new Parser(reader);

        parser.parsLine("PUSH 3", all_operations);
        parser.parsLine("PUSH 9", all_operations);
        parser.parsLine("/", all_operations);

        Calculator calculator = new Calculator(all_operations, context);
        calculator.work();
        Double answer = 3.0;

        assertEquals(answer, context.getStack().pop());
    }

    @Test
    public void TestDivByZero() throws Exception {
        Context context = new Context();
        ArrayList<RecordWord> all_operations = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Parser parser = new Parser(reader);

        parser.parsLine("PUSH 0", all_operations);
        parser.parsLine("PUSH 9", all_operations);
        parser.parsLine("/", all_operations);

        Calculator calculator = new Calculator(all_operations, context);
        Exception exception = assertThrows(Exception.class, calculator::work);
        String answer = "Exception: division by zero";

        assertEquals(answer, exception.getMessage());
    }

    @Test
    public void TestNotArgOnStack() throws Exception {
        Context context = new Context();
        ArrayList<RecordWord> all_operations = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Parser parser = new Parser(reader);

        parser.parsLine("PUSH 9", all_operations);
        parser.parsLine("/", all_operations);

        Calculator calculator = new Calculator(all_operations, context);
        Exception exception = assertThrows(Exception.class, calculator::work);
        String answer = "Exception: not enough arguments on the stack";

        assertEquals(answer, exception.getMessage());
    }


}