package com.Main;

import Exception.ArgCountException;
import Operations.Mul;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Parser {
    private static final Logger log = Logger.getLogger(Parser.class.getName());
    private final ArrayList<RecordWord> all_operations = new ArrayList<>();
    private final BufferedReader parser_reader;
    private static final int NAME = 1;
    private static final int FIRST_ARG = 2;
    private static final int SECOND_ARG = 3;

    public Parser (BufferedReader reader) {
        parser_reader = reader;
    }

    public ArrayList<RecordWord> getAllOperations () {
        return all_operations;
    }

    public void parsLine (String line, ArrayList<RecordWord> all_operations) throws ArgCountException {
        short count_of_space = 0;
        boolean flag_of_comment = false;
        String[] buffer;
        RecordWord this_operation = new RecordWord();

        buffer = line.split(" ");
        for (String s : buffer) {
            count_of_space ++;

            if (count_of_space == NAME) {
                if (Objects.equals(s.charAt(0), '#')) {
                    flag_of_comment = true;
                    break;
                }
                this_operation.setNameOfOperation(s);
            }
            else if (count_of_space == FIRST_ARG) {
                this_operation.setFirstArg(s);
            }
            else if (count_of_space == SECOND_ARG) {
                this_operation.setSecondArg(s);
            }
            else {
                log.log(Level.SEVERE, "Exception: incorrect number of arguments for the operation");
                throw new ArgCountException();
            }
        }
        if (!flag_of_comment) {
            all_operations.add(this_operation);
        }
    }

    public void readStream() throws ArgCountException {
        String line_now;

        try{
            while ((line_now = parser_reader.readLine()) != null) {
                parsLine(line_now, all_operations);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
