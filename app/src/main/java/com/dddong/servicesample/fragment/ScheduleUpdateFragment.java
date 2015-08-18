package com.dddong.servicesample.fragment;


import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.TimePicker;

import com.dddong.servicesample.R;

import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class ScheduleUpdateFragment extends DialogFragment implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {

    private TextView btnProfileMode;
    private static ScheduleUpdateFragment instance;
    private View viewSelectTimeFrom, viewSelectTimeTo, viewRepeat;
    private TextView txtTimeFrom, txtTimeTo;
    private GregorianCalendar timeFrom, timeTo;

    public static ScheduleUpdateFragment newInstance() {

        if (instance == null) {
            instance = new ScheduleUpdateFragment();
            instance.setStyle(android.support.v4.app.DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light);
            instance.timeFrom = (GregorianCalendar) GregorianCalendar.getInstance();
            instance.timeTo = (GregorianCalendar) GregorianCalendar.getInstance();
        }
        return instance;
    }

    public ScheduleUpdateFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_schedule_update, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnProfileMode = (TextView) view.findViewById(R.id.btnProfileMode);
        btnProfileMode.setOnClickListener(this);

        viewSelectTimeFrom = view.findViewById(R.id.selectTimeFrom);
        viewSelectTimeTo = view.findViewById(R.id.selectTimeTo);
        viewSelectTimeFrom.setOnClickListener(this);
        viewSelectTimeTo.setOnClickListener(this);

        viewRepeat = view.findViewById(R.id.selectRepeat);
        viewRepeat.setOnClickListener(this);

        txtTimeFrom = (TextView) view.findViewById(R.id.txtFrom);
        txtTimeTo = (TextView) view.findViewById(R.id.txtTo);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnProfileMode:
                PopupMenu popup = new PopupMenu(getActivity(), v);
                popup.setOnMenuItemClickListener(instance);
                popup.inflate(R.menu.menu_profile);
                popup.show();
                break;
            case R.id.selectTimeFrom:
                showTimeDialog(txtTimeFrom, timeFrom);
                break;

            case R.id.selectTimeTo:
                showTimeDialog(txtTimeTo, timeTo);
                break;

            case R.id.selectRepeat:
                DaysOptionsFragment daysOptionsFragment = new DaysOptionsFragment();
                daysOptionsFragment.show(getFragmentManager(), "dialog");
                break;
        }
    }

    private void showTimeDialog(final TextView txtDisplay, final GregorianCalendar time) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                txtDisplay.setText(StringUtils.leftPad(String.valueOf(hourOfDay), 2, "0") + ":" + StringUtils.leftPad(String.valueOf(minute), 2, "0"));
                time.set(Calendar.HOUR_OF_DAY, hourOfDay);
                time.set(Calendar.MINUTE, minute);

            }
        }, time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), true);

        timePickerDialog.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        btnProfileMode.setText(item.getTitle());
        return false;
    }

}
