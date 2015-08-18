package com.dddong.servicesample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by dddong on 2015/08/13.
 */
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Calendar calendar = Calendar.getInstance();

        Toast.makeText(context, "I'm running at " + calendar.getTime(), Toast.LENGTH_SHORT).show();
    }
}
