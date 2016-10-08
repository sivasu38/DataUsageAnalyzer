package com.myntra.networkanalyzer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by c.sivasubramanian on 06/10/16.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DataUsageDetails.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "dataUsage";
    private static final String ID = "id";
    private static final String APPNAME = "name";
    private static final String DATE = "date";
    private static final String DATAUSED = "dataUsed";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                        ID + " INTEGER PRIMARY KEY, " +
                        APPNAME + " TEXT, " +
                        DATE + " TEXT, " +
                        DATAUSED + " DOUBLE)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertDataUsageDetail(String appName, String date, Double dataUsed) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(APPNAME, appName);
        contentValues.put(DATE, date);
        contentValues.put(DATAUSED, dataUsed);
        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean updateDateUsageDetail(String appName, String date, Double dataUsed) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(APPNAME, appName);
        contentValues.put(DATE, date);
        contentValues.put(DATAUSED, dataUsed);
        db.update(TABLE_NAME, contentValues, APPNAME + " = ? ", new String[]{appName});
        return true;
    }

    public boolean checkForApp(String appName)
    {
        Cursor result = getCursorDetails(appName);
        return result.getCount()>0;
    }

    private Cursor getCursorDetails(String appName)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " +
                ID + "=?", new String[]{appName});
        return result;
    }

    public void deleteAllContents()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,null,null);
        db.close();
    }

    public boolean checkForTableContents()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.query(TABLE_NAME,null,null,null,null,null,null);
        return result.getCount()>0;
    }

    public ArrayList<DataUsage> getAllRecords()
    {
        ArrayList<DataUsage> list = new ArrayList<DataUsage>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.query(TABLE_NAME, null, null, null, null, null, null);
        int i=0;
        if (result.moveToFirst()) {

            while (result.isAfterLast() == false) {
                String appName = result.getString(result
                        .getColumnIndex(APPNAME));
                String date = String.valueOf(result.getColumnIndex(DATE));
                Double dataUsed = Double.valueOf(result.getColumnIndex(DATAUSED));
                DataUsage dataobj = new DataUsage(appName,date,dataUsed);
                list.add(dataobj);
                result.moveToNext();
            }
        }
        return list;
    }

}
