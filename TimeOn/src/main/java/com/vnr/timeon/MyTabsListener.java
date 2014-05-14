package com.vnr.timeon;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Roy on 5/14/2014.
 */
class MyTabsListener implements ActionBar.TabListener {
    public Fragment fragment;
    public Context context;

    public MyTabsListener(Fragment fragment, Context context) {
        this.fragment = fragment;
        this.context = context;

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
        //Toast.makeText(context, "Reselected!", Toast.LENGTH_SHORT).show();
        String tabName = tab.getText().toString();
        if (BuildConfig.DEBUG) {
            Log.e(Constants.TAG, tabName + "is Reselected!");
        }
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        //Toast.makeText(context, "Selected!", Toast.LENGTH_SHORT).show();
        String tabName = tab.getText().toString();
        if (BuildConfig.DEBUG) {
            Log.e(Constants.TAG, tabName + "isSelected!");
        }
        ft.replace(R.id.fragment_container, fragment);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        //Toast.makeText(context, "Unselected!", Toast.LENGTH_SHORT).show();
        String tabName = tab.getText().toString();
        if (BuildConfig.DEBUG) {
            Log.e(Constants.TAG, tabName + "isUnselected!");
        }
        ft.remove(fragment);
    }

}
