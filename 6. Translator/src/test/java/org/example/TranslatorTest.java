package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TranslatorTest {
    private Translator translator;

    @BeforeEach
    void setUp() {
        translator = new Translator();
        translator.addWord("hello", "привіт");
        translator.addWord("world", "світ");
        translator.addWord("cat", "кіт");
    }

    @Test
    void testTranslateSingleKnownWord() {
        assertEquals("привіт", translator.translate("hello"));
    }

    @Test
    void testTranslateSingleUnknownWord() {
        assertEquals("[dog]", translator.translate("dog"));
    }

    @Test
    void testTranslatePhraseWithKnownWords() {
        assertEquals("привіт світ", translator.translate("hello world"));
    }

    @Test
    void testTranslatePhraseWithMixedWords() {
        assertEquals("привіт [dog] світ",
                     translator.translate("hello dog world"));
    }

    @Test
    void testAddWordWorksCaseInsensitive() {
        translator.addWord("Tree", "дерево");
        assertEquals("дерево", translator.translate("tree"));
        assertEquals("дерево", translator.translate("TREE"));
    }

    @Test
    void testTranslateIsCaseInsensitive() {
        assertEquals("привіт", translator.translate("HELLO"));
        assertEquals("світ", translator.translate("World"));
    }

    @Test
    void testTranslateEmptyPhrase() {
        assertEquals("", translator.translate(""));
    }
}
