package com.anma.count;

public class WordCounter {
    public static int countWords(String text) {
        return text.split(" ").length;
    }
}
