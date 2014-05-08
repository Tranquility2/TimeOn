package com.vnr.timeon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    /** Called when the user click on the about menu **/
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                // send this to DisplayAboutActivity
                Intent intent = new Intent(this, DisplayAbout.class);
                // get the version
                String value = Integer.toString(android.os.Build.VERSION.SDK_INT);
                // add key-value pairs
                intent.putExtra(EXTRA_NAME_MESSAGE, value);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void insertCurrentTimeText(View view) {
        Intent intent = new Intent(this, DisplayCurrentTimeActivity.class);

        startActivity(intent);
    }
}
