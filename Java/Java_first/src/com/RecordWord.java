package com;

public class RecordWord {
    private final String word;
    private final Integer count_word;

    public RecordWord(String word_, Integer count_word_) {
        word = word_;
        count_word = count_word_;
    }

    public String getWord () { return word;}
    public Integer getCountWord () { return count_word;}
}
