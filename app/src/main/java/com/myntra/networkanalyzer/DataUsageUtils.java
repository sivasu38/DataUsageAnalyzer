package com.myntra.networkanalyzer;

import java.util.Date;
import java.util.HashMap;

import static java.lang.Math.max;

class DataUsageUtils {
    private static final double MEGABYTE = 1024 * 1024;
    private final TrafficStatsDelegate stats;

    public DataUsageUtils(TrafficStatsDelegate trafficStats) {
        this.stats = trafficStats;
    }

    public double computeAppUsage(int uid) {
        return inMB(stats.getUidRxBytes(uid) + stats.getUidTxBytes(uid));
    }

    public double computeTotalUsage() {
        return inMB(stats.getTotalRxBytes() + stats.getTotalTxBytes());
    }

    private double inMB(double dataBytes) {
        return max(0.0, dataBytes / MEGABYTE);
    }

    public HashMap<String,Object> setdatainMap(String appName,double dataUsed)
    {
        HashMap<String,Object> data = new HashMap<>();
        data.put("appName",appName);
        data.put("date",getCurrentDate());
        data.put("dataUsed",dataUsed);
        return data;
    }

    private String getCurrentDate() {
        Date date = new Date();
        return date.getDate()+"-"+date.getMonth();
    }
}
