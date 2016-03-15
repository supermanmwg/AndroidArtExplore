package com.remoteview;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.TextView;

/**
 * Created by weiguangmeng on 16/3/14.
 */
public class Demo2Activity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo2);
        findViewById(R.id.send_remote).setOnClickListener(this);
        TextView demoNameTv = (TextView) findViewById(R.id.demo2_name);
        demoNameTv.setText(demoNameTv.getText().toString() + Process.myPid() + "");
    }

    @Override
    public void onClick(View v) {
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.simulated_notificaton);
        remoteViews.setTextViewText(R.id.msg, getResources().getString(R.string.go_to_demo2));
        remoteViews.setImageViewResource(R.id.icon, R.drawable.circle);
        PendingIntent pendingIntent1 = PendingIntent.getActivity(this, 0, new Intent(this, Demo1Activity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntent2 = PendingIntent.getActivity(this, 0, new Intent(this, Demo2Activity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.open_demo2, pendingIntent2);
        remoteViews.setOnClickPendingIntent(R.id.item_holder, pendingIntent1);



        Intent intent = new Intent(Constants.REMOTE_ACTION);
        intent.putExtra(Constants.REMOTE_VIEW, remoteViews);
        sendBroadcast(intent);
    }
}
