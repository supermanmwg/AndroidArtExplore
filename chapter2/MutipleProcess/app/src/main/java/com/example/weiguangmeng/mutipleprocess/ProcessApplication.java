package com.example.weiguangmeng.mutipleprocess;

import android.app.Application;
import android.os.Process;
import android.util.Log;

/**
 * Created by weiguangmeng on 16/2/29.
 */
public class ProcessApplication extends Application {

    private final String TAG = getClass().getSimpleName();
    @Override
    public void onCreate() {
        super.onCreate();
        
        Log.d(TAG, "Process Id is " + Process.myPid());
    }
}
