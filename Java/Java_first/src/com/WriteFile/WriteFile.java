package com.WriteFile;

import com.RecordWord;
import com.Sort.Sort;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class WriteFile {
    private  final ArrayList<RecordWord> sorted_list;
    private final int count_words;

    public WriteFile (Sort sorter) {
        sorted_list = sorter.getSortedList();
        count_words = sorter.getCountWords();
    }

    public double percOfWords(long count_words, int count_this_word) {
        return (double) (count_this_word * 100) / count_words;
    }

    public void WriteToFile() {
        PrintWriter writer = null;
        try {
            File fout  = new File("output.csv");
            writer = new PrintWriter(fout);

            double perc;

            for(RecordWord rw : sorted_list){
                perc = percOfWords(count_words, rw.getCountWord());
                String result = String.format("%.2f", perc);
                writer.println(rw.getWord() + ";" + rw.getCountWord() + ";" + result + "%");
            }

            writer.close();
        }
        catch (IOException e){
            System.err.println("Error while writing file:" + e.getLocalizedMessage());
        }
        finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
