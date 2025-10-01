package org.example;


import java.io.IOException;
import java.util.Locale;
import java.util.logging.*;

public class LoggingConfig {

    public static void setupLogging(Logger logger, boolean enableConsole) {
        try {
            LogManager.getLogManager().reset();

            if (enableConsole) {
                logger.addHandler(getConsoleHandler());
            }

            logger.addHandler(getFileHandler());
            logger.setLevel(Level.ALL);

        } catch (IOException e) {
            System.err.println("Logging setup error: " + e.getMessage());
        }
    }

    private static FileHandler getFileHandler() throws IOException {
        FileHandler fileHandler = new FileHandler("app.log", true);
        fileHandler.setLevel(Level.ALL);
        fileHandler.setFormatter(new SimpleFormatter() {
            @Override
            public synchronized String format(LogRecord record) {
                return String.format(Locale.ENGLISH,
                                     "%1$tF %1$tT [%2$-7s] %3$s %n",
                                     record.getMillis(),
                                     record.getLevel().getName(),
                                     record.getMessage()
                );
            }
        });
        return fileHandler;
    }

    private static ConsoleHandler getConsoleHandler() {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.INFO);
        consoleHandler.setFormatter(new SimpleFormatter() {
            @Override
            public synchronized String format(LogRecord record) {
                return String.format(Locale.ENGLISH,
                                     "%1$tF %1$tT [%2$-7s] %3$s %n",
                                     record.getMillis(),
                                     record.getLevel().getName(),
                                     record.getMessage()
                );
            }
        });
        return consoleHandler;
    }
}
