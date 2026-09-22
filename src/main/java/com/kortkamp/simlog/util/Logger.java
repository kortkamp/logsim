package com.kortkamp.simlog.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.kortkamp.simlog.clock.SimulationClock;

public final class Logger {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
    SimulationClock clock = SimulationClock.getInstance();

    // Private constructor prevents instantiation
    private Logger() {
    }

    // Singleton holder
    private static class Holder {
        private static final Logger INSTANCE = new Logger();
    }

    public static Logger getInstance() {
        return Holder.INSTANCE;
    }

    public void debug(String message) {
        log("DBUG", message);
    }

    public void info(String message) {
        log("INFO", message);
    }

    public void warn(String message) {
        log("WARN", message);
    }

    public void error(String message) {
        log("ERROR", message);
    }

    private void log(String level, String message) {
        System.out.printf(
            "%s [%s] %s%n",
            clock.dateTime().format(FORMATTER),
            level,
            message
        );
    }
}
