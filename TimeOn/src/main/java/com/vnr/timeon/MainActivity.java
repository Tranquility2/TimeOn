package com.vnr.timeon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends Activity {
    public final static String EXTRA_NAME_MESSAGE = "com.example.TimeOn.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user clicks the ok button **/
    public void okMessage(View view) {
        // send this to DisplayMessageActivity
        Intent intent = new Intent(this, DisplayMessageTestActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_name_message);
        String message = editText.getText().toString();

        // add key-value pairs
        intent.putExtra(EXTRA_NAME_MESSAGE, message);
        startActivity(intent);
    }

    /** Called when the user clicks the update_time button **/
    public void updateTimeText(View view) {
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => "+c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());

        TextView txtView = (TextView) findViewById(R.id.Time_now);
        txtView.setText(formattedDate);
    }
}
