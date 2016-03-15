package com.remoteview;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RemoteViews;

public class MainActivity extends Activity implements View.OnClickListener{

    private LinearLayout mRemoteViewsContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.go_to_demo2).setOnClickListener(this);
        mRemoteViewsContent = (LinearLayout) findViewById(R.id.remoteview_content);

        IntentFilter intentFilter = new IntentFilter(Constants.REMOTE_ACTION);
        registerReceiver(mRemoteViewReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mRemoteViewReceiver);
    }

    private BroadcastReceiver mRemoteViewReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            RemoteViews remoteViews = intent.getParcelableExtra(Constants.REMOTE_VIEW);
            if(remoteViews != null) {
                updateView(remoteViews);
            }

        }
    };

    private void updateView(RemoteViews remoteViews) {
        View view = remoteViews.apply(this, mRemoteViewsContent);
        mRemoteViewsContent.addView(view);
    }


    @Override
    public void onClick(View v) {
        Intent intent  = new Intent(this, Demo2Activity.class);
        startActivity(intent);
    }
}
