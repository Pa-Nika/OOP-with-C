import Factory.Calculator;
import com.Main.Context;
import com.Main.Parser;
import com.Main.RecordWord;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class UndefOperationTest {
    @Test
    public void TestUndefOperation() throws Exception {
        Context context = new Context();
        ArrayList<RecordWord> all_operations = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Parser parser = new Parser(reader);

        parser.parsLine("Hello world!", all_operations);

        Calculator calculator = new Calculator(all_operations, context);

        Exception exception = assertThrows(Exception.class, calculator::work);
        String answer = "Exception: undefined operation";
        assertEquals(answer, exception.getMessage());
    }
}
