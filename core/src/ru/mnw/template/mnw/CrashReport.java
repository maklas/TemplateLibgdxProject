package ru.mnw.template.mnw;

public interface CrashReport {

    void report(Exception e);

    void report(String error);

}
