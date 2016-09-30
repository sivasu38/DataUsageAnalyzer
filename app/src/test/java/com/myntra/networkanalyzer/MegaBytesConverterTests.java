package com.myntra.networkanalyzer;

//import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by c.sivasubramanian on 29/09/16.
 */
@RunWith(JUnit4.class)
public class MegaBytesConverterTests {

    DataUsageUtils dataUsageUtils;
    @Before
    public void setup()
    {
        dataUsageUtils=new DataUsageUtils();
    }

    @Test
    public void checkForPositiveValue()
    {
        double dataBytes = dataUsageUtils.getDataBytesinMB(1048576);
        Assert.assertTrue("Checking for the data Usage with exactly equal to 1MB ", dataBytes == 1);
    }

    @Test
    public void checkForNegativeValue()
    {
        double dataBytes = dataUsageUtils.getDataBytesinMB(-1);
        Assert.assertTrue("Checking for the data Usage with value less than 0 ",dataBytes==0);
    }

    @Test
    public void checkForZeroValue()
    {
        double dataBytes = dataUsageUtils.getDataBytesinMB(0);
        Assert.assertTrue("Checking for the data Usage with value less than 0 ",dataBytes==0);
    }


}
