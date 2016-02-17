package com.tsubik.cordova.start_on_boot;

import android.content.Intent;
import android.content.BroadcastReceiver;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.widget.Toast;
import android.util.Log;
import android.content.Context;
import android.content.ComponentName;

import java.util.List;


public class StartOnBootReceiver extends BroadcastReceiver {
    public static final String TAG = "StartOnBootPlugin";

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast toast = Toast.makeText(context, "Starting stuff", Toast.LENGTH_SHORT);
        toast.show();

        try {
            Intent i = new Intent();
            i.setComponent(new ComponentName("com.adobe.phonegap.push", "com.adobe.phonegap.push.RegistrationIntentService"));
            context.startService(i);
        } catch(Exceptoin ex) {
            Log.w(TAG, "Cannot start service on boot. Exception" + ex.toString());
        }
    }

    private String getMainActivityName (Context context) {
        try {
            PackageManager pm = context.getPackageManager();

            ActivityInfo[] activities = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES).activities;
            if (activities.length > 0) {
                return activities[0].name;
            }
        } catch (PackageManager.NameNotFoundException e) {}
        //return default name
        return context.getPackageName() + ".MainActivity";
    }
}
