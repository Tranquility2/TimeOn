package com.vnr.timeon;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Chronometer;



public class DisplayTimerTestActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_timer_test);

        // Create the Chronometer
        Chronometer chronometer = new Chronometer(this);
        chronometer.setId(R.id.new_chronometer_1);
        chronometer.setTextSize(40);
        chronometer.setFormat("H:MM:SS");
        chronometer.start();
        chronometer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                Chronometer chronometer;
                chronometer = (Chronometer) findViewById(R.id.new_chronometer_1);
                chronometer.stop();
                }
        });

        // Set the text view as the activity layout
        setContentView(chronometer);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.display_timer_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
