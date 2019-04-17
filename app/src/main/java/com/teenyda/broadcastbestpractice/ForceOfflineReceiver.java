package com.teenyda.broadcastbestpractice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;

/**
 * @Auther: teenyda
 * @Date: 2019/4/17 10:39
 * @Description:
 */
public class ForceOfflineReceiver extends BroadcastReceiver {

    private static final String TAG = "ForceOfflineReceiver";
    
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
}
