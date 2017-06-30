package androidpermission.com.bw.test.day0630;

import android.app.Application;

import androidpermission.com.bw.test.day0630.crash.CrashHandler;

/**
 * Created by 2016 on 2017/6/30.
 */

public class MyApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }
}
