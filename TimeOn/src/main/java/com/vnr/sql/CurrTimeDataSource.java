package com.vnr.sql;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.vnr.models.CurrTime;
import com.vnr.sql.MySQLiteHelper;

/**
 * Created by Verose on 4/16/2014.
 */
public class CurrTimeDataSource {
    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_CURR_TIME };

    public CurrTimeDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public CurrTime createCurrentTime(String curr_time) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_CURR_TIME, curr_time);
        long insertId = database.insert(MySQLiteHelper.TABLE_CURR_TIME, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_CURR_TIME,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        CurrTime newCurrTime = cursorToCurrTime(cursor);
        cursor.close();

        return newCurrTime;
    }

    public void deleteCurrTime(CurrTime curr_time) {
        long id = curr_time.getId();
        System.out.println("Time deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_CURR_TIME, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<CurrTime> getAllTimes() {
        List<CurrTime> times = new ArrayList<CurrTime>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_CURR_TIME,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            CurrTime comment = cursorToCurrTime(cursor);
            times.add(comment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return times;
    }

    private CurrTime cursorToCurrTime(Cursor cursor) {
        CurrTime curr_time = new CurrTime();
        curr_time.setId(cursor.getLong(0));
        curr_time.setDateTime(cursor.getString(1));

        return curr_time;
    }
}