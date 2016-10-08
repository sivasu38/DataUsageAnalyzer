package com.myntra.networkanalyzer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by c.sivasubramanian on 08/10/16.
 */
public class AlarmReceiver extends BroadcastReceiver{


    @Override
    public void onReceive(final Context context, Intent intent) {
        final DBHelper dbHelper = new DBHelper(context);

        if(dbHelper.checkForTableContents())
        {
            ArrayList<DataUsage> list = dbHelper.getAllRecords();
            DataUsageClient dataUsageClient = RetrofitService.createService(DataUsageClient.class);
            Call<ArrayList<DataUsage>> postReq = dataUsageClient.postHistoricalDataUsage(list);
            postReq.enqueue(new Callback<ArrayList<DataUsage>>() {
                @Override
                public void onResponse(Call<ArrayList<DataUsage>> call, Response<ArrayList<DataUsage>> response) {
                    Toast.makeText(context,"Data Usage got Stored Successfully", Toast.LENGTH_SHORT);
                    dbHelper.deleteAllContents();
                }

                @Override
                public void onFailure(Call<ArrayList<DataUsage>> call, Throwable t) {
                    Toast.makeText(context,"Data Usage Storage Failed !!!", Toast.LENGTH_SHORT);
                }
            });

        }
    }
}
