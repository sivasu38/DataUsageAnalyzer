package com.myntra.networkanalyzer;

import android.content.Context;

import java.util.List;

/**
 * Created by c.sivasubramanian on 24/06/15.
 */
public interface IClickListener {

    public void onClickRecyclerItem(FeedListRowHolder holder,List<PInfo> packageNames);

}
