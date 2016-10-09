package com.myntra.networkanalyzer;

import android.graphics.drawable.Drawable;

class PInfo {
    private String appName;
    private String pName;
    private Drawable drawable;
    private boolean isSelected;

    public PInfo(String appName,String pName,Drawable drawable, boolean isSelected)
    {
        this.appName=appName;
        this.pName=pName;
        this.drawable=drawable;
        this.isSelected=isSelected;
    }

    public PInfo(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public String getAppName() {
        return appName;
    }

    public String getpName() {
        return pName;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}


