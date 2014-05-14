package com.vnr.timeon;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {
    public final static String EXTRA_NAME_MESSAGE = "com.example.TimeOn.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ActionBar
        ActionBar actionbar = getActionBar();
        actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // create new tabs and set up the titles of the tabs
        ActionBar.Tab mHomeTab = actionbar.newTab().setText(
                getString(R.string.ui_tabname_Home));
        ActionBar.Tab mProgressTab = actionbar.newTab().setText(
                getString(R.string.ui_tabname_Progress));
        ActionBar.Tab mStatisticsTab = actionbar.newTab().setText(
                getString(R.string.ui_tabname_Statistics));

        // create the fragments
        Fragment mHomeFragment = new HomeFragment();
        Fragment mProgressFragment = new ProgressFragment();
        Fragment mStatisticsFragment = new StatisticsFragment();

        // bind the fragments to the tabs - set up tabListeners for each tab
        mHomeTab.setTabListener(new MyTabsListener(mHomeFragment, getApplicationContext()));
        mProgressTab.setTabListener(new MyTabsListener(mProgressFragment, getApplicationContext()));
        mStatisticsTab.setTabListener(new MyTabsListener(mStatisticsFragment, getApplicationContext()));

        // add the tabs to the action bar
        actionbar.addTab(mHomeTab);
        actionbar.addTab(mProgressTab);
        actionbar.addTab(mStatisticsTab);
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
                Intent intent = new Intent(this, DisplayAboutActivity.class);
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

}
