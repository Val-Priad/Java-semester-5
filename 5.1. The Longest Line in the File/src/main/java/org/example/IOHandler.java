package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class IOHandler {

    private final String filePath;

    public IOHandler(String filePath) {
        this.filePath = filePath;
    }

    public LineResult getLineWithMaxWords() throws
            IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        String line;
        String maxLine = "";
        int maxWords = 0;

        while ((line = reader.readLine()) != null) {
            String[] words = line.trim().split("\\s+");
            int wordCount = (line.trim().isEmpty()) ? 0 : words.length;

            if (wordCount > maxWords) {
                maxWords = wordCount;
                maxLine = line;
            }
        }
        reader.close();

        return new LineResult(maxLine, maxWords);
    }
}
