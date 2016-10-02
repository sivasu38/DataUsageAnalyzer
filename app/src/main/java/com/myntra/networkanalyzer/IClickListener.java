package com.myntra.networkanalyzer;

import java.util.List;

interface IClickListener {

    void onClickRecyclerItem(FeedListRowHolder holder, List<PInfo> packageNames);

}
