package com.myntra.networkanalyzer;

import android.database.Cursor;
import android.os.AsyncTask;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by c.sivasubramanian on 07/10/16.
 */
public class AsyncDBHelper extends AsyncTask{

    private final DBHelper dbHelper;
    private final String keyword;
    private final HashMap<String,Object> dbData;


    public AsyncDBHelper(String keyword,DBHelper dbHelper,HashMap<String,Object> dbData)
    {
        this.keyword=keyword;
        this.dbHelper=dbHelper;
        this.dbData=dbData;
    }


    @Override
    protected Object doInBackground(Object[] params) {
        Object flag =false;
        switch (keyword) {
            case "pumpin":
                String appName = (String) dbData.get("appName");
                String date = (String)dbData.get("date");
                Double dataUsed = (Double) dbData.get("dataUsed");
                if(dbHelper.checkForApp(appName)) {
                    flag  = dbHelper.updateDateUsageDetail(appName, date, dataUsed);
                }
                else
                {
                    flag  = dbHelper.insertDataUsageDetail(appName, date, dataUsed);
                }
             break;
            case "delete":
                dbHelper.deleteAllContents();
                break;
        }
        return flag;
    }
}
