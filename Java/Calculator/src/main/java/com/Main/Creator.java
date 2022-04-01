package com.Main;

import Factory.Calculator;
import Exception.MyException;

import java.io.*;
import java.util.ArrayList;

public class Creator {
    private BufferedReader reader = null;
    private Parser parser = null;
    private Context context = null;

    public Creator() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        parser = new Parser(reader);
        context = new Context();
    }

    public Creator(String file_name) {
        try {
            reader = new BufferedReader(new InputStreamReader
                    (new FileInputStream(file_name) ) );
            parser = new Parser(reader);
            context = new Context();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void work () throws MyException {
        parser.readStream();
        ArrayList<RecordWord> all_operations = parser.getAllOperations();

        Calculator calculator = new Calculator(all_operations, context);
        calculator.work();
    }
}
