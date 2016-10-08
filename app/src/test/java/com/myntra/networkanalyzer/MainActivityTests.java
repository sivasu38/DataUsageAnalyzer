package com.myntra.networkanalyzer;

import android.content.pm.PackageManager;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
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

    @Before
    public void setUp() throws Exception
    {
        mainActivity = Robolectric.buildActivity(MainActivity.class)
                .create()
                .resume()
                .get();
    }

    @Test
    public void activityPresenceTest()
    {
        Assert.assertNotNull(mainActivity);
    }

    @Test
    public void recyclerViewVisibleTest()
    {
        Assert.assertNotNull(mainActivity.findViewById(R.id.recycler_view));
    }

    @Test
    public void recyclerViewAdapterCountTest()
    {
        pm = mainActivity.getPackageManager();
        ArrayList<PInfo> packageNames= mainActivity.getInstalledApps(false, pm);
        customRecycleAdapter = new CustomRecycleAdapter(mainActivity,packageNames);
        Assert.assertEquals(packageNames.size(),customRecycleAdapter.getItemCount());
    }

}
