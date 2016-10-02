package com.myntra.networkanalyzer;

import static java.lang.Math.max;

class DataUsage {
    private static final double MEGABYTE = 1024 * 1024;
    private final TrafficStatsDelegate stats;

    public DataUsage(TrafficStatsDelegate trafficStats) {
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
}
