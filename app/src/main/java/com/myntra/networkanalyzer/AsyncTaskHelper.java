package com.myntra.networkanalyzer;

import android.content.pm.PackageManager;
import android.os.AsyncTask;

import java.util.ArrayList;

class AsyncTaskHelper extends AsyncTask<Void, Void, ArrayList<PInfo>> {

    private String keyword;
    private IRenderViewListener renderView;
    private ArrayList<PInfo> res;
    private PackageManager pm;

    public AsyncTaskHelper()
    {
        super();
    }

    public void setInputs(String keyword,PackageManager pm,IRenderViewListener renderView)
    {
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
