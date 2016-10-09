package com.myntra.networkanalyzer;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowAlarmManager;
import org.robolectric.shadows.ShadowApplication;

/**
 * Created by c.sivasubramanian on 09/10/16.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class AlarmUtilsTests {

    Context context;
    ShadowAlarmManager shadowAlarmManager;
    AlarmUtils utils;

    @Before
    public void setup()
    {
        context = RuntimeEnvironment.application.getApplicationContext();
        utils = new AlarmUtils();
        AlarmManager alarmManager = (AlarmManager) RuntimeEnvironment.application.getSystemService(Context.ALARM_SERVICE);
        shadowAlarmManager = Shadows.shadowOf(alarmManager);
    }

    @Test
    public void checkForAlarmManagerBeforeSettingAlarmTest()
    {
        Assert.assertNull(shadowAlarmManager.getNextScheduledAlarm());
    }

    @Test
    public void checkForAlarmManagerAfterSettingAlarmTest()
    {
        utils.setupAlarm(context);
        Assert.assertNotNull(shadowAlarmManager.getNextScheduledAlarm());
    }

    @Test
    public void checkForSingleInstanceAlarmManagerTest()
    {
        utils.setupAlarm(context);
        utils.setupAlarm(context);
        utils.setupAlarm(context);
        utils.setupAlarm(context);
        Assert.assertEquals(1,shadowAlarmManager.getScheduledAlarms().size());
    }

}
