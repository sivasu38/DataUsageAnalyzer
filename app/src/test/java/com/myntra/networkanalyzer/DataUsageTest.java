package com.myntra.networkanalyzer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static java.util.Arrays.asList;
import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class DataUsageTest {
    private final double rxBytes;
    private final double txBytes;
    private final double expectedResult;
    private final TrafficStatsDelegate trafficStats = mock(TrafficStatsDelegate.class);
    private final DataUsageUtils dataUsage = new DataUsageUtils(trafficStats);

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return asList(new Object[][]{
                {-1.0, -1.0, 0.0},
                {0.0, 0.0, 0.0},
                {64, 64, 0.0001},
                {1024, 1024, 0.0019},
                {1024 * 1024, 1024 * 1024, 2.0}
        });
    }

    public DataUsageTest(double rxBytes, double txBytes, double expectedResult) {
        this.rxBytes = rxBytes;
        this.txBytes = txBytes;
        this.expectedResult = expectedResult;
    }

    @Test
    public void reportsAppUsageInMB() {
        int uid = 9;
        when(trafficStats.getUidRxBytes(uid)).thenReturn(rxBytes);
        when(trafficStats.getUidTxBytes(uid)).thenReturn(txBytes);
        assertEquals(expectedResult, dataUsage.computeAppUsage(uid), 0.0001);
    }

    @Test
    public void reportsTotalUsageInMB() {
        when(trafficStats.getTotalRxBytes()).thenReturn(rxBytes);
        when(trafficStats.getTotalTxBytes()).thenReturn(txBytes);
        assertEquals(expectedResult, dataUsage.computeTotalUsage(), 0.0001);
    }
}