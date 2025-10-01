package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class TagSorter {
    private final String url;
    private final Map<String, Integer> tagCount;

    public TagSorter(String url) throws IOException {
        validateUrl(url);
        this.url = url;
        this.tagCount = new HashMap<>();
        parse();
    }

    private void validateUrl(String url) throws MalformedURLException {
        new URL(url);
    }

    private void parse() throws IOException {
        Document doc = Jsoup.connect(url).get();
        for (Element element : doc.getAllElements()) {
            String tagName = element.tagName();
            tagCount.put(tagName, tagCount.getOrDefault(tagName, 0) + 1);
        }
    }

    public List<Map.Entry<String, Integer>> sortByTagName() {
        return tagCount.entrySet().stream()
                       .sorted(Map.Entry.comparingByKey())
                       .toList();
    }

    public List<Map.Entry<String, Integer>> sortByFrequency() {
        return tagCount.entrySet().stream()
                       .sorted(Map.Entry.comparingByValue())
                       .toList();
    }
}
