package com.myntra.networkanalyzer;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;

/**
 * Created by c.sivasubramanian on 15/06/15.
 */
public class PInfo{

    private String appName;
    private String pName;
    private String versionName;
    private int versionCode;
    private Drawable drawable;
    private int UID;
    private boolean isSelected;

    public PInfo(boolean isSelected)
    {
        this.isSelected=isSelected;
    }


    public void setAppName(String appName)
    {
       this.appName=appName;
    }
    public void setpName(String pName)
    {
       this.pName=pName;
    }

    public void setVersionName(String versionName)
    {
       this.versionName=versionName;
    }
    public void setVersionCode(int versionCode)
    {
       this.versionCode=versionCode;
    }

    public void setDrawable(Drawable drawable)
    {
        this.drawable=drawable;
    }

    public void setUID(int UID)
    {
        this.UID=UID;
    }

    public String getAppName()
    {
        return appName;
    }

    public String getpName()
    {
        return pName;
    }

    public String getVersionName()
    {
        return versionName;
    }
    public int getVersionCode()
    {
        return versionCode;
    }
    public Drawable getDrawable()
    {
        return drawable;
    }
    public int getUID()
    {
        return UID;
    }

    public boolean isSelected()
    {
        return isSelected;
    }

    public void setSelected(boolean isSelected)
    {
        this.isSelected=isSelected;
    }




}


