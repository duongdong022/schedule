package com.dddong.servicesample.model;

import java.util.List;

/**
 * Created by dddong on 2015/08/13.
 */
public class Schedule {

    private String id;
    private String label;
    private boolean status;
    private Type type;
    private List<DayOfWeek> dayOfWeeks;

    private int startHour;
    private int startMinute;

    private int endHour;
    private int endMinute;

    private String ringToneName;
    private String ringTonePath;

}
