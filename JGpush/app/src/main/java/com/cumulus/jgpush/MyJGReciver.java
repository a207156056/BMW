package com.cumulus.jgpush;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;

import com.cumulus.jgpush.utils.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.logging.Logger;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by wang on 2019/3/11.
 */

public class MyJGReciver extends BroadcastReceiver {
    private NotificationManager nm;
    private String TAG = "MyJGReciver";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (null == nm) {
            nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }

        Bundle bundle = intent.getExtras();
        LogUtil.d(TAG, "onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            LogUtil.d(TAG, "JPush 用户注册成功");

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            LogUtil.d(TAG, "接受到推送下来的自定义消息");

            // Push Talk messages are push down by custom message format
            sendNotification(context,bundle.getString(JPushInterface.EXTRA_MESSAGE));
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            LogUtil.d(TAG, "接受到推送下来的通知");


        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            LogUtil.d(TAG, "用户点击打开了通知");


        } else {
            LogUtil.d(TAG, "Unhandled intent - " + intent.getAction());
        }
    }

    private void sendNotification(Context context, String msg) {
        //data是1,跳转Activity展示美女
        //如果是2,点击跳转到主页
        String data = "";
        String message = "";
        String url = "";
        try {
            JSONObject extrasJson = new JSONObject(msg);
            data = extrasJson.optString("data");
            message = extrasJson.optString("msg");
            url = extrasJson.optString("url");
        } catch (Exception e) {
            Log.w(TAG, "Unexpected: extras is not a valid json", e);
            return;
        }

        //发送自己的通知
        String channelId = "me";
        CharSequence channelName = "msg";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            nm.createNotificationChannel(channel);
        }

        Intent intent;
        //data是1,跳转Activity展示美女
        //如果是2,点击跳转到主页
        if ("1".equals(data)) {
            intent = new Intent(context, MeiNvActivity.class);
        } else{
            intent = new Intent(context, MainActivity.class);
        }
        intent.putExtra("url",url);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //延时意图
        PendingIntent pendingIntent = PendingIntent.getActivity(context,100,
                intent,PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.mipmap.ic_launcher)//小图标,必要
                .setContentText(message)//消息内容,必要
                .setContentTitle(message)//通知标题,必要
                .setContentIntent(pendingIntent)//延时意图
                .setAutoCancel(true)//点击消失,和延时意图配合使用
                .build();
        nm.notify(1,notification);
    }

    private String printBundle(Bundle bundle) {

        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
                    Log.i(TAG, "This message has no Extra data");
                    continue;
                }
                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it = json.keys();

                    while (it.hasNext()) {
                        String myKey = it.next().toString();
                        sb.append("\nkey:" + key + ", value: [" +
                                myKey + " - " + json.optString(myKey) + "]");
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "Get message extra JSON error!");
                }
            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
            }
        }
        return sb.toString();
    }

}
