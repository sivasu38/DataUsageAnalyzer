package com.myntra.networkanalyzer;

import android.content.Context;
import android.content.pm.PackageManager;
import org.junit.Assert;
import org.junit.Before;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.robolectric.annotation.Config;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.ArrayList;

/**
 * Created by c.sivasubramanian on 08/10/16.
 */

@RunWith(MockitoJUnitRunner.class)
@Config(constants = BuildConfig.class)
public class MainActivityTests {

    private MainActivity mainActivity;
    private CustomRecycleAdapter customRecycleAdapter;
    private PackageManager pm;
    private AdapterHelper mockedAdapterHelper;

    @Before
    public void setUp()  {

        MockitoAnnotations.initMocks(this);
        Context mockcontext = Mockito.mock(Context.class);
        mockedAdapterHelper = Mockito.mock(AdapterHelper.class);
        ArrayList<PInfo> list = getSampleList();
        Mockito.when(mockedAdapterHelper.getInstalledApps(false,mockcontext.getPackageManager())).thenReturn(list);
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
