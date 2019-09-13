package com.mountaininformer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

public class PersonsDBAdapter {
    static final String DATABASE_NAME = "MountainInformerDB.db";
    String ok="OK";
    static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase db;
    private final Context context;
    private static DatabaseHelper dbHelper;

    public PersonsDBAdapter(Context _context) {
        context = _context;
        dbHelper = new DatabaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public PersonsDBAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public  SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    public boolean insertEntry(String name, String surname, String number, int SMSstart, int SMSend, int SMShelp) {
        try {
            ContentValues newValues = new ContentValues();
            newValues.put("Name", name);
            newValues.put("Surname", surname);
            newValues.put("Number", number);
            newValues.put("SMSstart", SMSstart);
            newValues.put("SMSend", SMSend);
            newValues.put("SMShelp", SMShelp);


            db = dbHelper.getWritableDatabase();
            long result=db.insert("Persons", null, newValues);
            System.out.print(result);
        }catch(Exception ex) {
            System.out.println("Exceptions " +ex);
            Log.e("Note", "One row entered");
            return false;
        }
        return true;
    }

    public int deleteEntry(String id) {
        String where="Id=?";
        int numberOFEntriesDeleted= (int) db.delete("Persons", where, new String[]{id});
        Toast.makeText(context, R.string.deleted_person_toast+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }

    public Cursor getLastEntry() {
        db=dbHelper.getReadableDatabase();
        Cursor cursor=db.query("Persons", null, null, null, null, null, null);
        if(cursor.getCount()<1)
            return null;
        cursor.moveToLast();
        return cursor;
    }

    public Cursor getEntry() {
        db=dbHelper.getReadableDatabase();
        Cursor cursor=db.query("Persons", null, null, null, null, null, null);
        if(cursor.getCount()<1) // Persons Not Exist
            return null;
        return cursor;
    }

    public void  updateEntry(String name, String surname, String number, int SMSstart, int SMSend, int SMShelp) {
        //  create object of ContentValues
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("Name", name);
        updatedValues.put("Surname", surname);
        updatedValues.put("Number", number);
        updatedValues.put("SMSstart", SMSstart);
        updatedValues.put("SMSend", SMSend);
        updatedValues.put("SMShelp", SMShelp);

        String where="Id = ?";
        db.update("Persons",updatedValues, where, new String[]{getLastEntry().getString(1)});
    }
}
