package com.myntra.networkanalyzer;

import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by c.sivasubramanian on 14/06/15.
 */
public class AsyncTaskHelper extends AsyncTask<Void, Void, ArrayList<PInfo>> {

    public String keyword;
    public IRenderViewListener renderView;
    public IClickListener clicklistener;
    public ArrayList<PInfo> res;
    public PackageManager pm;

    public AsyncTaskHelper(String keyword,PackageManager pm,IRenderViewListener renderView, IClickListener clickListener)
    {
        super();
        this.keyword=keyword;
        this.pm=pm;
        this.renderView=renderView;
        this.clicklistener=clickListener;
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
    protected void onPreExecute() {
        super.onPreExecute();
    }



    @Override
    protected void onPostExecute(ArrayList<PInfo> o) {
        super.onPostExecute(o);
        renderView.renderRecyclerView(pm);

    }
}
