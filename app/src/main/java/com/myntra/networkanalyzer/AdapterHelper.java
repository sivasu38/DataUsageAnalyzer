package com.myntra.networkanalyzer;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by c.sivasubramanian on 09/10/16.
 */
public class AdapterHelper {

    private CustomRecycleAdapter adapter;

    public ArrayList<PInfo> getInstalledApps(boolean getSysPackages, PackageManager pm) {
        ArrayList<PInfo> list = new ArrayList<>();
        List<ApplicationInfo> packs = pm.getInstalledApplications(0);
        for (int i = 0; i < packs.size(); i++) {
            ApplicationInfo p = packs.get(i);
            if ((!getSysPackages) && (p.packageName == null)) {
                continue;
            }
            PInfo newInfo = new PInfo(false);
            newInfo.setAppName(p.loadLabel(pm).toString());
            newInfo.setpName(p.packageName);
            newInfo.setDrawable(p.loadIcon(pm));
            newInfo.setSelected(false);
            list.add(newInfo);
        }
        Collections.sort(list, new appNameComparator());
        return list;
    }

}
