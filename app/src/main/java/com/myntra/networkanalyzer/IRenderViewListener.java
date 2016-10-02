package com.myntra.networkanalyzer;

import android.content.pm.PackageManager;

import java.util.ArrayList;

interface IRenderViewListener {

    void renderRecyclerView(PackageManager pm);

    ArrayList<PInfo> getInstalledApps(boolean getSysPackages, PackageManager pm);
}
