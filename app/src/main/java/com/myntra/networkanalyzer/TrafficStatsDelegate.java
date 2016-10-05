package com.myntra.networkanalyzer;

import android.net.TrafficStats;

class TrafficStatsDelegate {
    public double getUidRxBytes(int uid) {
        return TrafficStats.getUidRxBytes(uid);
    }

    public double getUidTxBytes(int uid) {
        return TrafficStats.getUidTxBytes(uid);
    }

    public double getTotalRxBytes() {
        return TrafficStats.getTotalRxBytes();
    }

    public double getTotalTxBytes() {
        return TrafficStats.getTotalTxBytes();
    }
}