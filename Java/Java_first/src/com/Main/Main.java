package com.Main;

import com.Parser.Parser;
import com.Sort.Sort;
import com.WriteFile.WriteFile;

public class Main {

    public static void main(String[] args) {
        Parser parser = new Parser(args[0]);
        parser.readFile();

        Sort sort_map = new Sort(parser);
        sort_map.sortMap();

        WriteFile write_list = new WriteFile(sort_map);
        write_list.WriteToFile();
    }
}
