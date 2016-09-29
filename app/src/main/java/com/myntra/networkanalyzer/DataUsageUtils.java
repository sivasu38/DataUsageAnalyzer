package com.myntra.networkanalyzer;

import android.net.TrafficStats;

/**
 * Created by c.sivasubramanian on 29/09/16.
 */
public class DataUsageUtils {

    private static final double  MEGABYTE = 1024 * 1024;

    public static double getDataUsageForApp(int uid)
    {
        double dataBytes = (TrafficStats.getUidRxBytes(uid))+(TrafficStats.getUidTxBytes(uid));
        dataBytes = getDataBytesinMB(dataBytes);
        return dataBytes;
    }

    public static double getDataBytesinMB(double dataBytes) {
        double finalBytes;
        if(isNegative(dataBytes))
        {
            finalBytes=0.0;
        }
        else
        {
            finalBytes=dataBytes/MEGABYTE;
        }
        return finalBytes;
    }

    public static double getTotalUsage()
    {
        double dataBytes = (TrafficStats.getTotalRxBytes())+(TrafficStats.getTotalTxBytes());
        dataBytes = getDataBytesinMB(dataBytes);
        return dataBytes;
    }

    public static boolean isNegative(double d) {
        return Double.compare(d, 0.0) < 0;
    }


}
