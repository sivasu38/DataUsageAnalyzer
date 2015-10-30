package com.myntra.networkanalyzer;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by c.sivasubramanian on 16/06/15.
 */
public class CustomRecycleAdapter extends RecyclerView.Adapter<FeedListRowHolder> implements Filterable {


    private List<PInfo> packageNames;
    private List<PInfo> origPackageNames;
    private Context context;
    private PackageManager pm;
    private int UID;
    private IClickListener iClickListener;
    public  FeedListRowHolder listholder;
    private ICheckedListener iCheckedListener;

    private boolean enableAll = false;


    public CustomRecycleAdapter(Context context , ArrayList<PInfo> packageNames,PackageManager pm){
        this.context=context;
        if(context instanceof IClickListener){
            iClickListener = (IClickListener) context;
        }
        if(context instanceof ICheckedListener)
        {
            iCheckedListener=(ICheckedListener)context;
        }
        this.packageNames=packageNames;
        this.pm=pm;
    }

    @Override
    public FeedListRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleviewlayout,null);
        listholder = new FeedListRowHolder(v);
        return listholder;
    }

    @Override
    public void onBindViewHolder(final FeedListRowHolder holder, final int position) {
        if(packageNames.size()==0 || packageNames !=null)
        {
          Toast.makeText(context,"No Search Results Found...",Toast.LENGTH_LONG);
        }
        holder.thumbnail.setImageDrawable(packageNames.get(position).getDrawable());
        holder.title.setText(Html.fromHtml(packageNames.get(position).getAppName()));
        if(packageNames.get(position).isSelected() == true) {
            holder.checkBox.setTag(true);
        }
        else
        {
            holder.checkBox.setTag(false);
        }

        if(enableAll){
            holder.checkBox.setEnabled(true);
            holder.checkBox.setChecked((Boolean) holder.checkBox.getTag());
            holder.itemView.setOnClickListener(null);
        }else{
            packageNames.get(position).setSelected(false);
            holder.checkBox.setEnabled(false);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (iClickListener != null) {
                        iClickListener.onClickRecyclerItem(holder,packageNames);
                    }
                }
            });
        }

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iCheckedListener != null) {
                    iCheckedListener.checkedTwoTimes(holder,packageNames,position);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return (null != packageNames ? packageNames.size() : 0);
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();
                if(origPackageNames == null){
                    origPackageNames = new ArrayList<>(packageNames);
                }

                List<PInfo> tempList = new ArrayList<>();
                for (PInfo info: origPackageNames){
                    if(info.getAppName().toLowerCase().contains(charSequence.toString().toLowerCase().trim())){
                        tempList.add(info);
                    }
                }

                filterResults.values = tempList;
                filterResults.count = tempList.size();
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    packageNames = (List<PInfo>) filterResults.values;
                    notifyDataSetChanged();
            }
        };
    }

    public void enableAllCheckBoxes()
    {
        enableAll = true;
        notifyDataSetChanged();
    }

    public void disableAllCheckBoxes()
    {
        enableAll = false;
        notifyDataSetChanged();
    }



}
