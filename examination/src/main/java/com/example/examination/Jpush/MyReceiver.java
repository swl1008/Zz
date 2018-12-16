package com.example.examination.Jpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.examination.view.MainActivity;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("cn.jpush.android.intent.NOTIFICATION_OPENED")){
            Intent in= new Intent(context, MainActivity.class);
            in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(in);
        }
    }
}
