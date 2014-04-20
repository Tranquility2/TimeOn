package com.vnr.timeon;

import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import android.app.ListActivity;
import android.os.SystemClock;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;

import com.vnr.sql.CurrTimeDataSource;
import com.vnr.models.CurrTime;

public class DisplayCurrentTimeActivity extends ListActivity {

    private CurrTimeDataSource datasource;
    private static long timeWhenStopped = 0;

    public static long getTimeWhenStopped() {
        return timeWhenStopped;
    }

    public static void setTimeWhenStopped(long timeWhenStopped) {
        DisplayCurrentTimeActivity.timeWhenStopped = timeWhenStopped;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_current_time);

        datasource = new CurrTimeDataSource(this);
        datasource.open();

        List<CurrTime> values = datasource.getAllTimes();

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        ArrayAdapter<CurrTime> adapter = new ArrayAdapter<CurrTime>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }

    // Will be called via the onClick attribute
    // of the buttons in main.xml
    public void onClick(View view) {
        @SuppressWarnings("unchecked")

        ArrayAdapter<CurrTime> adapter = (ArrayAdapter<CurrTime>) getListAdapter();
        CurrTime time = null;
        String btn_name = ((Button)view).getText().toString();
        String chrono_val = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date current_time = new Date();
        Chronometer chrono_timer = (Chronometer) findViewById(R.id.chronometer_timer);

        chrono_val = chrono_timer.getText().toString();
        time = datasource.createCurrentTime(dateFormat.format(current_time), btn_name, chrono_val);
        // save the new current time to the database
        adapter.add(time);

        switch (view.getId()) {
            case R.id.Start:
                // Start the timer
                chrono_timer.setBase(SystemClock.elapsedRealtime() + getTimeWhenStopped());
                chrono_timer.start();
                break;

            case R.id.Pause:
                // Pause the timer
                setTimeWhenStopped(chrono_timer.getBase() - SystemClock.elapsedRealtime());
                chrono_timer.stop();
                break;

            case R.id.Stop:
                // Stop and Reset the timer
                chrono_timer.setBase(SystemClock.elapsedRealtime());
                chrono_timer.stop();
                setTimeWhenStopped(0);
                break;

            /*case R.id.delete:
                if (getListAdapter().getCount() > 0) {
                    time = (CurrTime) getListAdapter().getItem(0);
                    datasource.deleteCurrTime(time);
                    adapter.remove(time);
                }
                break;*/
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }
}
