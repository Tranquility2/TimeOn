package com.vnr.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Verose on 4/16/2014.
 * This class is our model and contains the data we will save in the database and show in
 * the user interface.
 */
public class CurrTime {
    private long id;
    private Date mDateTime;
    private String mBtnName;
    private String mTimerValue;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBtnName() {
        return mBtnName;
    }

    public void setBtnName(String mBtnName) {
        this.mBtnName = mBtnName;
    }

    public String getTimerValue() {
        return mTimerValue;
    }

    public void setTimerValue(String mTimerValue) {
        this.mTimerValue = mTimerValue;
    }

    public String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                /*yyyy-MM-dd*/ "HH:mm:ss", Locale.getDefault());

        return dateFormat.format(mDateTime);
    }

    public void setDateTime(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        try {
            mDateTime = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return getBtnName() + " " + getDateTime() + " " + getTimerValue();
    }
}