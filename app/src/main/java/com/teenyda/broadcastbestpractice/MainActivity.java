package com.teenyda.broadcastbestpractice;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * 强制下线操作
 */
public class MainActivity extends AppCompatActivity {

    private Button forceOffline;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        forceOffline = (Button) findViewById(R.id.force_offline);
        forceOffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.teenyda.broadcastbestpractice.FORCE_OFFLINE");
                Log.d(TAG, "send broadcast");

                /*LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(MainActivity.this);
                localBroadcastManager.sendBroadcast(intent);*/
                sendBroadcast(intent);
            }
        });
    }
}
