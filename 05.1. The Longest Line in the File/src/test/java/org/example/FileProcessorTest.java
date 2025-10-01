package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileProcessorTest {

    @ParameterizedTest
    @MethodSource("fileProvider")
    void testLineWithMaxWords(String fileName, String expectedLine)
            throws IOException {
        Path file = Path.of(fileName);
        IOHandler processor = new IOHandler(file.toString());
        LineResult result = processor.getLineWithMaxWords();

        assertEquals(expectedLine, result.line());
    }

    static Stream<Arguments> fileProvider() {
        return Stream.of(
                Arguments.of("1_line.txt",
                             "Who am I? Who am I? Who am I? Who am I? Who am I? Who am I?  Who am I?"),
                Arguments.of("3_line.txt",
                             "How to handle it? How to handle it? How to handle it? How to handle it? How to handle it? How to handle it?"),
                Arguments.of("6_line.txt",
                             "Where am I? Where am I? Where am I? Where am I? Where am I? Where am I?"),
                Arguments.of("15_line.txt",
                             "I'm not weak! I'm not weak! I'm not weak! I'm not weak! I'm not weak! I'm not weak!")
        );
    }
}
