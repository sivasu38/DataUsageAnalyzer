package com.myntra.networkanalyzer;

import android.content.pm.PackageManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by c.sivasubramanian on 24/06/15.
 */
public interface IRenderViewListener {

    public void renderRecyclerView(PackageManager pm);

    public ArrayList<PInfo> getInstalledApps(boolean getSysPackages,PackageManager pm);
}
