package com.myntra.networkanalyzer;

import android.net.TrafficStats;

/**
 * Created by c.sivasubramanian on 29/09/16.
 */
public class DataUsageUtils {

    private static final double  MEGABYTE = 1024 * 1024;

    public double getDataUsageForApp(int uid)
    {
        double dataBytes = (TrafficStats.getUidRxBytes(uid))+(TrafficStats.getUidTxBytes(uid));
        dataBytes = getDataBytesinMB(dataBytes);
        return dataBytes;
    }

    public double getDataBytesinMB(double dataBytes) {
        return (isNegative(dataBytes)? 0.0:dataBytes/MEGABYTE);
    }

    public double getTotalUsage()
    {
        double dataBytes = (TrafficStats.getTotalRxBytes())+(TrafficStats.getTotalTxBytes());
        dataBytes = getDataBytesinMB(dataBytes);
        return dataBytes;
    }

    public static boolean isNegative(double d) {
        return Double.compare(d, 0.0) < 0;
    }


}
