package Factory;

import Operations.Block;
import com.Main.Context;
import com.Main.RecordWord;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import Exception.UndefOperation;
import Exception.MyException;

public class Calculator {
    private Properties properties = null;
    private InputStream file = null;
    private ArrayList<RecordWord> all_operations = null;
    private Context context = null;
    private HashMap<String, String> table_of_operations = null;
    private static final Logger log = Logger.getLogger(Calculator.class.getName());

    public Calculator(ArrayList<RecordWord> all_operations, Context context) {
        file = Calculator.class.getClassLoader().getResourceAsStream("factory.property");
        properties = new Properties();
        this.all_operations = all_operations;
        this.context = context;
        table_of_operations = new HashMap<>();
    }

    private String FillProperty (Properties properties, String key) throws MyException {
        String value = properties.getProperty(key);
        if (value == null) {
            log.log(Level.SEVERE, "Exception: undefined operation " + key);
            throw new UndefOperation();
        }

        table_of_operations.put(key, value);
        return value;
    }

    public void work() throws MyException {
        try {
            properties.load(file);
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }


        Block operation;

        for (RecordWord rw : all_operations) {
            String key = rw.getNameOfOperation();
            String name;

            if (table_of_operations.containsKey(key)) {
                name = table_of_operations.get(key);
            }
            else {
                name = FillProperty(properties, key);
            }

            try {
                operation = (Block) Class.forName(name).newInstance();
                operation.work(context, rw);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
