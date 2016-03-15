package com.anotherapp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RemoteViews;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";

    private LinearLayout mRemoteViewContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRemoteViewContent = (LinearLayout) findViewById(R.id.remoteview_content);

        IntentFilter intentFilter = new IntentFilter(Constants.REMOTE_ACTION);
        registerReceiver(mBroadcastReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver);
    }

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
       @Override
       public void onReceive(Context context, Intent intent) {
           RemoteViews remoteViews = intent.getParcelableExtra(Constants.REMOTE_VIEW);
           
           Log.d(TAG, "remote view id is " + remoteViews.getLayoutId());
           int layoutId = getResources().getIdentifier("simulated_notificaton", "layout", getPackageName());
           ViewGroup view = (ViewGroup) getLayoutInflater().inflate(layoutId, mRemoteViewContent, false);
           if(remoteViews != null) {
               remoteViews.reapply(MainActivity.this, view);
               mRemoteViewContent.addView(view);
           }
           Log.d(TAG, "receive a broadcast");
       }
   };
}
