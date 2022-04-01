package com.Parser;

import java.io.*;
import java.lang.StringBuilder;
import java.util.*;

public class Parser {
    private final String file_name;
    private final StringBuilder builder = new StringBuilder();

    private final HashMap <String, Integer> my_map = new HashMap<>();
    private int count_words = 0;


    public Parser(String name) {
        file_name = name;
    }

    public HashMap <String, Integer> getMap () {
        return my_map;
    }

    public int getCountWords () {
        return count_words;
    }

    public void readFile () {
        Reader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream(file_name));
            int symbol = reader.read();

            while (symbol != -1) {
                if (Character.isLetterOrDigit(symbol)) {
                    builder.append((char)symbol);
                }
                else {
                    if (builder.length() != 0 && my_map.containsKey(builder.toString())) {
                        String key = builder.toString();

                        Integer count_map = (Integer)my_map.get(key);
                        count_map++;
                        my_map.put(key, count_map);
                        count_words++;
                    }
                    else if (builder.length() != 0) {
                        my_map.put(builder.toString(), 1);
                        count_words++;
                    }

                    builder.delete(0, builder.length());
                }

                symbol = reader.read();
            }

            reader.close();
        }
        catch (IOException e) {
            System.err.println("Error while reading file:" + e.getLocalizedMessage());
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                }
                catch (IOException e) {
                    System.err.println("Error while closing file:" + e.getLocalizedMessage());
                }
            }
        }
    }
}
