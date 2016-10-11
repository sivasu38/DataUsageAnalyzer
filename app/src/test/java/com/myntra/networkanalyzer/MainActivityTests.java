package com.myntra.networkanalyzer;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Rule;


import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowApplication;
import org.robolectric.util.ActivityController;
import org.robolectric.util.Transcript;

import java.util.ArrayList;

/**
 * Created by c.sivasubramanian on 08/10/16.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainActivityTests {

    private MainActivity mainActivity;
    private CustomRecycleAdapter customRecycleAdapter;
    private PackageManager pm;
    private AdapterHelper mockedAdapterHelper;
    private Context mockcontext;

    @Before
    public void setUp()  {

//        context = RuntimeEnvironment.application.getApplicationContext();
        MockitoAnnotations.initMocks(this);
        mockcontext = Mockito.mock(Context.class);
        mockedAdapterHelper = Mockito.mock(AdapterHelper.class);
        ArrayList<PInfo> list = getSampleList();
        Mockito.when(mockedAdapterHelper.getInstalledApps(false,mockcontext.getPackageManager())).thenReturn(list);
//        mainActivity = Robolectric.buildActivity( MainActivity.class )
//                .create()
//                .resume()
//                .get();
        mainActivity = Mockito.mock(MainActivity.class);

    }

    private ArrayList<PInfo> getSampleList() {
        ArrayList<PInfo> list = new ArrayList<>();
        list.add(new PInfo("SampleApp1","com.SampleApp1",null,false));
        list.add(new PInfo("SampleApp2","com.SampleApp2",null,false));
        return list;
    }

    @Test
    public void activityPresenceTest()
    {
        Assert.assertNotNull(mainActivity);

    }

    @Test
    public void recyclerViewAdapterCountTest()
    {
        pm = mainActivity.getPackageManager();
        ArrayList<PInfo> packageNames= mockedAdapterHelper.getInstalledApps(false, pm);
        customRecycleAdapter = new CustomRecycleAdapter(mainActivity,packageNames);
        Assert.assertEquals(packageNames.size(),customRecycleAdapter.getItemCount());
    }

}
