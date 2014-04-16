package com.vnr.sql; /**
 * Created by Verose on 4/16/2014.
 * This class is responsible for creating the database.
 * The onUpgrade() method will simply delete all existing data and re-create the table.
 * It also defines several constants for the table name and the table columns.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_CURR_TIME = "time";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CURR_TIME = "curr_time";

    private static final String DATABASE_NAME = "time.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_CURR_TIME + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_CURR_TIME
            + " text not null);";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data"
        );
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CURR_TIME);
        onCreate(db);
    }

}