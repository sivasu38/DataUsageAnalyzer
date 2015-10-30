package com.myntra.networkanalyzer;

import java.util.List;

/**
 * Created by c.sivasubramanian on 30/07/15.
 */
public interface ICheckedListener {

    public void checkedTwoTimes(FeedListRowHolder holder,List<PInfo> packageNames,int position);
}
