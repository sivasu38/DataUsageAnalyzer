package com.myntra.networkanalyzer;

import java.util.List;

interface ICheckedListener {

    void checkedTwoTimes(FeedListRowHolder holder, List<PInfo> packageNames, int position);
}
