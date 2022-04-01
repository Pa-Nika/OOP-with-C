package com.Sort;

import com.Parser.Parser;
import com.RecordWord;
import java.util.*;
import java.lang.*;

public class Sort {
    private final HashMap<String, Integer> my_map;
    private final ArrayList<RecordWord> sorted_list = new ArrayList<>();
    private final int count_words;

    public Sort(Parser parser) {
        my_map = parser.getMap();
        count_words = parser.getCountWords();
    }

    public ArrayList<RecordWord> getSortedList () {
        return sorted_list;
    }

    public int getCountWords () {
        return count_words;
    }

    public void sortMap() {
        for (String str:my_map.keySet()) {
            RecordWord word = new RecordWord(str, (Integer)my_map.get(str));
            sorted_list.add(word);
        }

        sorted_list.sort(new Comparator<RecordWord>() {
            public int compare(RecordWord o1, RecordWord o2) {
                return o2.getCountWord().compareTo(o1.getCountWord());
            }
        });
    }
}
