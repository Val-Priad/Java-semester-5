package org.example;

import java.util.HashMap;
import java.util.Map;

class Translator {
    private final Map<String, String> dictionary = new HashMap<>();

    public void addWord(String english, String ukrainian) {
        dictionary.put(english.toLowerCase(), ukrainian.toLowerCase());
    }

    public String translate(String phrase) {
        if (phrase == null || phrase.trim()
                                    .isEmpty()) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        String[] words = phrase.toLowerCase()
                               .split("\\s+");
        for (String word : words) {
            if (dictionary.containsKey(word)) {
                result.append(dictionary.get(word))
                      .append(" ");
            } else {
                result.append("[")
                      .append(word)
                      .append("] ");
            }
        }
        return result.toString()
                     .trim();
    }
}

