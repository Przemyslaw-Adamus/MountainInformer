package com.mountaininformer;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import java.util.Calendar;

public class DatabaseHelper extends SQLiteOpenHelper {

    static final String PERSONS_CREATE = "create table Persons( Id integer primary key autoincrement, Name text, Surname text, Number text, SMSstart integer, SMSend integer, SMShelp integer);";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(PERSONS_CREATE);
        }catch(Exception er){
            Log.e("Error","exceptioin");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = String.valueOf(new StringBuilder().append("DROP TABLE IF EXISTS ").append("Persons"));
        db.execSQL(query);
        Log.w("TaskDBAdapter", "Upgrading from version " +oldVersion + " to " + newVersion + ", which will destroy all old data");
        onCreate(db);
    }
}
