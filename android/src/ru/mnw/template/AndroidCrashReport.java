package ru.mnw.template;

import com.crashlytics.android.Crashlytics;
import ru.mnw.template.mnw.CrashReport;
import ru.mnw.template.utils.Log;

public class AndroidCrashReport implements CrashReport {

    @Override
    public void report(Exception e) {
        Log.error(e);
        try {
            Crashlytics.logException(e);
        } catch (Exception ignore) {}
    }

    @Override
    public void report(String error) {
        Log.error(error);
        try {
            Crashlytics.log(error);
        } catch (Exception ignore) {}
    }
}
