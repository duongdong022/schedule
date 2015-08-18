package com.dddong.servicesample;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dddong.servicesample.fragment.DaysOptionsFragment;
import com.dddong.servicesample.fragment.ScheduleUpdateFragment;

import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView btnStartService;
    private TextView btnStopService, btnSample, btnList, btnUpdate, btnDaySelect;
    private Intent fooServiceIntent;
    private Intent alarmIntent;
    private PendingIntent pendingIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStartService = (TextView) findViewById(R.id.btnStartService);
        btnStopService = (TextView) findViewById(R.id.btnStopService);
        btnSample = (TextView) findViewById(R.id.btnSample);
        btnList = (TextView) findViewById(R.id.btnList);
        btnUpdate = (TextView) findViewById(R.id.btnUpdate);
        btnDaySelect = (TextView) findViewById(R.id.btnDaySelect);

        Intent alarmIntent = new Intent(MainActivity.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, alarmIntent, 0);

        btnStartService.setOnClickListener(this);
        btnStopService.setOnClickListener(this);
        btnSample.setOnClickListener(this);
        btnList.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDaySelect.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnStartService:
                start();
                break;
            case R.id.btnStopService:
                cancel();
                break;
            case R.id.btnSample:
                startAt10();
                break;
            case R.id.btnList:
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
                break;
            case R.id.btnUpdate:
                ScheduleUpdateFragment scheduleUpdateFragment = ScheduleUpdateFragment.newInstance();
                scheduleUpdateFragment.show(getFragmentManager(), "dialog");
                break;
            case R.id.btnDaySelect:
                DaysOptionsFragment daysOptionsFragment = new DaysOptionsFragment();
                daysOptionsFragment.show(getFragmentManager(), "dialog");
                break;
        }
    }

    public void start() {

        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 8000;

        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
        Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();
    }

    public void cancel() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
        Toast.makeText(this, "Alarm Canceled", Toast.LENGTH_SHORT).show();
    }

    public void startAt10() {

        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int intervalOneHour = 1000 * 60 * 60;

        /* Set the alarm to start at 10:30 AM */
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
//        calendar.set(Calendar.HOUR_OF_DAY, 10);
//        calendar.set(Calendar.MINUTE, 30);
        calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) + 4);

        /* Repeating on every 20 minutes interval */
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                intervalOneHour / 60 /20, pendingIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
