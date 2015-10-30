package com.myntra.networkanalyzer;

/**
 * Created by c.sivasubramanian on 09/08/15.
 */
public interface IRenderFragmentListener {

    public void setDataUsageDetails(int uid,String appNameValue);
    public void storeInLocalSharedPreferenes(String appName,double receivedDataUsage);
    public double getDataUsageFromSharedPerferences(String appName);
    public boolean isNegative(double d);
}
