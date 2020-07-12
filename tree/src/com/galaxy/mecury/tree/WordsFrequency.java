package com.galaxy.mecury.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class WordsFrequency {
    Map<String, Integer> result = new HashMap<>();
    public WordsFrequency(String[] book) {
        Arrays.asList(book).forEach(word -> {
            result.merge(word, 1, (old_value, new_value) -> old_value + new_value );
        });
    }

    public int get(String word) {
        return result.getOrDefault(word, 0);
    }

    public static void main(String[] args) {
        WordsFrequency wordsFrequency = new WordsFrequency(new String[] {"i", "have", "an", "apple", "he", "have", "a", "pen"});
        System.out.println(wordsFrequency.get("have"));
    }
}