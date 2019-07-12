package ru.mnw.template.utils;


import com.badlogic.gdx.utils.Disposable;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * A low overhead, lightweight logging system.
 * @author Nathan Sweet <misc@n4te.com>
 */
public class Log {
    public static final int LEVEL_ERROR = 3;
    public static final int LEVEL_DEBUG = 2;
    public static final int LEVEL_TRACE = 1;

    private static final int level = LEVEL_TRACE;

    public static boolean ERROR = level <= LEVEL_ERROR;
    public static boolean DEBUG = level <= LEVEL_DEBUG;
    public static boolean TRACE = level <= LEVEL_TRACE;

    public static Logger logger = new Logger();

    private String type;

    private Log(String type) {
        this.type = type;
    }

    public static Log make(String type){
        return new Log(type);
    }

    public static Log make(Class clazz){
        return new Log(clazz.getSimpleName());
    }

    public static void setLogger(Logger logger) {
        Log.logger = logger;
    }

    public static Logger getLogger() {
        return logger;
    }


    public static void error(String message, Throwable ex) {
        if (ERROR) logger.log(LEVEL_ERROR, null, message, ex);
    }

    public static void error(Throwable ex) {
        if (ERROR) logger.log(LEVEL_ERROR, null, ex.getMessage() != null ? ex.getMessage() : "", ex);
    }

    public static void error(String category, Object message, Throwable ex) {
        if (ERROR) logger.log(LEVEL_ERROR, category, String.valueOf(message), ex);
    }

    public static void error(String message) {
        if (ERROR) logger.log(LEVEL_ERROR, null, message, null);
    }

    public static void error(String category, Object message) {
        if (ERROR) logger.log(LEVEL_ERROR, category, String.valueOf(message), null);
    }

    public static void debug(String message, Throwable ex) {
        if (DEBUG) logger.log(LEVEL_DEBUG, null, message, ex);
    }

    public static void debug(String category, Object message, Throwable ex) {
        if (DEBUG) logger.log(LEVEL_DEBUG, category, String.valueOf(message), ex);
    }

    public static void debug(String message) {
        if (DEBUG) logger.log(LEVEL_DEBUG, null, message, null);
    }

    public static void debug(String category, Object message) {
        if (DEBUG) logger.log(LEVEL_DEBUG, category, String.valueOf(message), null);
    }

    public static void debug(Class clazz, String message) {
        if (DEBUG) logger.log(LEVEL_DEBUG, clazz.getSimpleName() + ".class", message, null);
    }

    public static void trace(String message, Throwable ex) {
        if (TRACE) logger.log(LEVEL_TRACE, null, message, ex);
    }

    public static void trace(String category, Object message, Throwable ex) {
        if (TRACE) logger.log(LEVEL_TRACE, category, String.valueOf(message), ex);
    }

    public static void trace(String message) {
        if (TRACE) logger.log(LEVEL_TRACE, null, message, null);
    }

    public static void trace(String category, String message) {
        if (TRACE) logger.log(LEVEL_TRACE, category, message, null);
    }

    public static void trace(String category, Object message) {
        if (TRACE) logger.log(LEVEL_TRACE, category, String.valueOf(message), null);
    }

    public void t(Object msg, Throwable t){
        trace(type, msg, t);
    }

    public void t(Object msg){
        trace(type, msg);
    }

    public void d(Object msg, Throwable t){
        debug(type, msg, t);
    }

    public void d(Object msg){
        debug(type, msg);
    }

    public void e(Object msg, Throwable t){
        error(type, msg, t);
    }

    public void e(Object msg){
        error(type, msg);
    }

    public void e(Throwable ex){
        error(type, ex);
    }



    /**
     * Performs the actual logging. Default implementation logs to System.out. Extended and use {@link Log#logger} set to handle
     * logging differently.
     */
    public static class Logger implements Disposable {
        public void log(int level, String category, String message, Throwable ex) {
            StringBuilder builder = new StringBuilder(256);

            long time = System.currentTimeMillis();
            long minutes = time / (60_000) % 60;
            long seconds = time / (1000) % 60;
            long millis = time % 1000;
            if (minutes <= 9) builder.append('0');
            builder.append(minutes);
            builder.append(':');
            if (seconds <= 9) builder.append('0');
            builder.append(seconds);
            builder.append(':');
            if (millis <= 9) builder.append('0');
            if (millis <= 99) builder.append('0');
            builder.append(millis);

            switch (level) {
                case LEVEL_ERROR:
                    builder.append(" ERROR: ");
                    break;
                case LEVEL_DEBUG:
                    builder.append(" DEBUG: ");
                    break;
                case LEVEL_TRACE:
                    builder.append(" TRACE: ");
                    break;
            }

            if (category != null) {
                builder.append('[');
                builder.append(category);
                builder.append("] ");
            }

            builder.append(message);

            if (ex != null) {
                StringWriter writer = new StringWriter(256);
                ex.printStackTrace(new PrintWriter(writer));
                builder.append('\n');
                builder.append(writer.toString().trim());
            }

            print(builder.toString());
        }

        protected void print(String message) {
            System.out.println(message);
        }

        @Override
        public void dispose() {

        }
    }
}