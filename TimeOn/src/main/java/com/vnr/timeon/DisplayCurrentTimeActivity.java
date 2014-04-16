package com.vnr.timeon;

import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import android.app.ListActivity;
import android.view.View;
import android.widget.ArrayAdapter;

import com.vnr.sql.CurrTimeDataSource;
import com.vnr.models.CurrTime;

public class DisplayCurrentTimeActivity extends ListActivity {

    private CurrTimeDataSource datasource;

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
        switch (view.getId()) {
            case R.id.add:
                SimpleDateFormat dateFormat = new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                Date current_time = new Date();

                time = datasource.createCurrentTime(dateFormat.format(current_time));
                // save the new current time to the database
                adapter.add(time);
                break;
            case R.id.delete:
                if (getListAdapter().getCount() > 0) {
                    time = (CurrTime) getListAdapter().getItem(0);
                    datasource.deleteCurrTime(time);
                    adapter.remove(time);
                }
                break;
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
