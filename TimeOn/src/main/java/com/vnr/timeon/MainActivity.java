package com.vnr.timeon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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
        Intent intent = new Intent(this, DisplayMessageOKActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_name_message);
        String message = editText.getText().toString();

        // add key-value pairs
        intent.putExtra(EXTRA_NAME_MESSAGE, message);
        startActivity(intent);
    }
}
