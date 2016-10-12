package com.myntra.networkanalyzer;

/**
 * Created by c.sivasubramanian on 08/10/16.
 */
public class DataUsage {

    private String appName;
    private String date;
    private Double dataUsed;


    public DataUsage(String appName,String date,Double dataUsed)
    {
        this.appName=appName;
        this.date=date;
        this.dataUsed=dataUsed;
    }

    public String getAppName()
    {
        return appName;
    }

    public String getDate()
    {
        return date;
    }

    public Double getDateUsed()
    {
        return dataUsed;
    }

}
