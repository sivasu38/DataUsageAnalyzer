package com.myntra.networkanalyzer;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by c.sivasubramanian on 16/06/15.
 */
public class FeedListRowHolder extends RecyclerView.ViewHolder {

    protected ImageView thumbnail;
    protected TextView title;
    protected CheckBox checkBox;

    public FeedListRowHolder(View view)
    {
        super(view);
        this.thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        this.title = (TextView) view.findViewById(R.id.title);
        this.checkBox=(CheckBox)view.findViewById(R.id.chkSelected);

    }


}
