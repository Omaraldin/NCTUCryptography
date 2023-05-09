package com.nctu.cryptography;

import android.widget.EditText;

public class FrequencyItem {
    private String word;
    public EditText replacement;
    private double count;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        System.out.println(count);
        this.count = count;
    }
}
