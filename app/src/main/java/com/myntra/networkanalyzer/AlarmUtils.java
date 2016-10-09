package com.myntra.networkanalyzer;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by c.sivasubramanian on 08/10/16.
 */
public class AlarmUtils {


    private PendingIntent pendingIntent;
    private AlarmManager manager;
    private Context context;
    private static final int interval = 24*60*60*60;// To set interval for one day

    public void registerAlarmReceiver()
    {
        Intent alarmIntent = new Intent(context, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);
    }

    public void startAlarm()
    {
        manager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
        Toast.makeText(context, "Alarm Set For Data Retrieval", Toast.LENGTH_SHORT).show();
    }

    public void endAlarm()
    {
        if(manager!=null) {
            manager.cancel(pendingIntent);
            Toast.makeText(context, "Alarm Cancelled", Toast.LENGTH_SHORT).show();
        }
    }

    public void setupAlarm(Context context)
    {
        Intent alarmIntent = new Intent(context, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);
        manager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
        Toast.makeText(context, "Alarm Set For Data Retrieval", Toast.LENGTH_SHORT).show();
    }

}
