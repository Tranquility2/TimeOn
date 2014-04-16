package com.vnr.timeon;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class DisplayMessageTestActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message_test);

        // Get the message from the intent
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_NAME_MESSAGE);

        // Create the text view
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);

        // Create the Chronometer
        Chronometer chronometer = new Chronometer(this);
        chronometer.setId(R.id.new_chronometer_1);
        chronometer.setTextSize(40);
        chronometer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                touchChronometer(v);
            }
        });

        // Create the Button
        Button button = new Button(this);
        button.setText("Start");
        button.setTextSize(40);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                startTimerTest(v);
            }
        });

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.activity_display_message_test, null);
        // Find the LinearLayout element
        LinearLayout ll = (LinearLayout) v.findViewById(R.id.linearLayoutMain);
        // Add elements the LinearLayout
        ll.addView(textView);
        ll.addView(chronometer);
        ll.addView(button);
        // Display the view
        setContentView(v);
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

    /** Called when the user clicks the timer button **/
    public void startTimerTest(View view) {
        Chronometer chronometer = (Chronometer) findViewById(R.id.new_chronometer_1);
        chronometer.start();
    }

    /** Called when the user clicks the chronometer **/
    public void touchChronometer(View view) {
        Chronometer chronometer;
        chronometer = (Chronometer) findViewById(R.id.new_chronometer_1);
        chronometer.stop();
    }

}
