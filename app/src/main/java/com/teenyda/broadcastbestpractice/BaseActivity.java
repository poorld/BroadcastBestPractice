package com.teenyda.broadcastbestpractice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

/**
 * BaseActivity类是作为所有活动的父类
 * 并且监听广播
 *
 * 为什么不在onCreate()和onDestroy()方法里来注册和取消广播？
 * 因为我们始终只需要保证只有处于栈顶的活动才能接收到这条强制下线广播，非栈顶的
 * 活动不应该也没必要....
 */
public class BaseActivity extends AppCompatActivity {

    // 强制下线广播ACTION
    public static final String ACTION_FORCE_OFFLINE = "com.teenyda.broadcastbestpractice.FORCE_OFFLINE";

    private static final String TAG = "BaseActivity";
    
    private ForceOfflineReceiver receiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.teenyda.broadcastbestpractice.FORCE_OFFLINE");
        intentFilter.addDataScheme("file");
        receiver = new ForceOfflineReceiver();
        registerReceiver(receiver, intentFilter);
        Log.d(TAG, getClass().getSimpleName() + "register receiver");
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (receiver != null) {
            unregisterReceiver(receiver);
            receiver = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    /**
     * 强制下线广播监听
     */
    /*static class ForceOfflineReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, Intent intent) {

            Log.d(TAG, "onReceive: ");
            
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("warning");
            builder.setMessage("你已经被踢下线,请重新登录!");
            builder.setCancelable(false);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // 摧毁所有活动
                    ActivityCollector.finishAll();
                    Intent intent = new Intent(context, LoginActivity.class);
                    // 重新启动LoginActivity
                    context.startActivity(intent);
                }
            });
            builder.show();
        }
    }*/
}
