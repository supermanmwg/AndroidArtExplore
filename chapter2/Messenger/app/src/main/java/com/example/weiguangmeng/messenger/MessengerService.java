package com.example.weiguangmeng.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

/**
 * Created by weiguangmeng on 16/2/28.
 */
public class MessengerService extends Service {
    private static String TAG = "MessengerService";

    private static class MessengerHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            Log.d(TAG, "get msg from client, msg is " + msg.what);
            switch (msg.what) {
                case MyConstant.MSG_FROM_CLIENT:
                    Log.d(TAG, "receive msg from client:" + msg.getData().getString("msg"));
                    Messenger client = msg.replyTo;
                    Message replyMessage = Message.obtain(null, MyConstant.MSG_FROM_SERVER);
                    Bundle data = new Bundle();
                    data.putString("reply", "嗯,你的消息已收到,稍后会恢复你.");
                    replyMessage.setData(data);
                    try {
                        client.send(replyMessage);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }


        }
    }

    private final Messenger mMessenger = new Messenger(new MessengerHandler());

    @Override
    public IBinder onBind(Intent intent) {

        return mMessenger.getBinder();
    }
}
