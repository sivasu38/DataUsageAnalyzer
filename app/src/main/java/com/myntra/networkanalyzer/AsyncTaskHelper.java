package com.myntra.networkanalyzer;

import android.content.pm.PackageManager;
import android.os.AsyncTask;

import java.util.ArrayList;

class AsyncTaskHelper extends AsyncTask<Void, Void, ArrayList<PInfo>> {

    private final String keyword;
    private final IRenderViewListener renderView;
    private ArrayList<PInfo> res;
    private final PackageManager pm;

    public AsyncTaskHelper(String keyword,PackageManager pm,IRenderViewListener renderView)
    {
        super();
        this.keyword=keyword;
        this.pm=pm;
        this.renderView=renderView;
    }

    @Override
    protected ArrayList<PInfo> doInBackground(Void... voids) {
        switch (keyword)
        {
            case "INSTALLEDPACKAGES" :
                res = renderView.getInstalledApps(false,pm);
                break;
        }
         return res;
}

    @Override
    protected void onPostExecute(ArrayList<PInfo> o) {
        super.onPostExecute(o);
        renderView.renderRecyclerView(pm);

    }
}
