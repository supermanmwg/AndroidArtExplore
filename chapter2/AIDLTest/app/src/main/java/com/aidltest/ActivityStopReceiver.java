package com.aidltest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by mwg on 16-2-29.
 */
public class ActivityStopReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String actionName = intent.getAction();
        // 自定义消息
        if(MainActivity.ACTIVITY_STOP.equals(actionName)){
            Intent mainActivity = new Intent(context, MainActivity.class);
            mainActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(mainActivity);

            // 推送服务停止
        }
    }
}
