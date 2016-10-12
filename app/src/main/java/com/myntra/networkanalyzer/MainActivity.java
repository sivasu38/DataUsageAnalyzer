package com.myntra.networkanalyzer;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends ActionBarActivity implements IClickListener, IRenderViewListener, View.OnClickListener, ICheckedListener {
    private RecyclerView recyclerView;
    private EditText searchtext;
    private ArrayList<PInfo> res;
    private CustomRecycleAdapter adapter;
    private int UID;
    private PackageManager pm;
    private ToggleButton compareToggle;
    private boolean toggleFlag;
    private static int count = 0;
    private static Intent intent;
    private ProgressBar progressBar;
    private DBHelper dbHelper;
    private AlarmUtils alarmUtils;
    private AdapterHelper adapterHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchtext = (EditText) findViewById(R.id.editText6);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        compareToggle = (ToggleButton) findViewById(R.id.toggleButton);
        compareToggle.setChecked(false);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        pm = this.getPackageManager();
        searchtext.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        adapterHelper = new AdapterHelper();
        res = adapterHelper.getInstalledApps(false, pm);
        this.renderRecyclerView(pm);
        compareToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    adapter.enableAllCheckBoxes();
                } else {
                    adapter.disableAllCheckBoxes();
                }
            }
        });
        searchtext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (adapter != null) {
                    adapter.getFilter().filter(charSequence);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        dbHelper = new DBHelper(this);
        alarmUtils = new AlarmUtils();
        alarmUtils.setupAlarm(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        compareToggle.setChecked(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClickRecyclerItem(FeedListRowHolder holder, List<PInfo> packageNames) {
        Toast.makeText(this, packageNames.get(holder.getAdapterPosition()).getAppName(), Toast.LENGTH_SHORT).show();
        int uid = getUID(packageNames.get(holder.getAdapterPosition()).getpName());
        Intent intent = new Intent(this, DateUsageDetailActivity.class);
        intent.putExtra("UID1", uid);
        intent.putExtra("packageName1", packageNames.get(holder.getAdapterPosition()).getpName());
        intent.putExtra("appName1", packageNames.get(holder.getAdapterPosition()).getAppName());
        intent.putExtra("compareFlag", false);
        this.startActivity(intent);
    }

    @Override
    public void renderRecyclerView(PackageManager pm) {
        adapter = new CustomRecycleAdapter(this, res);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        searchtext.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    private int getUID(String packageName) {
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        for (ApplicationInfo packageInfo : packages) {
            if (packageInfo.packageName.equals(packageName)) {
                //get the UID for the selected app
                UID = packageInfo.uid;
            }
        }
        return UID;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.toggleButton) {
            if (toggleFlag) {
                compareToggle.setChecked(false);
                toggleFlag = false;
                adapter.disableAllCheckBoxes();
            } else {
                compareToggle.setChecked(true);
                toggleFlag = true;
                adapter.enableAllCheckBoxes();
            }
        }
    }

    @Override
    public void checkedTwoTimes(FeedListRowHolder holder, List<PInfo> packageNames, int position) {
        if (holder.checkBox.isChecked()) {
            packageNames.get(position).setSelected(true);
            if (count == 0) {
                intent = new Intent(this, DateUsageDetailActivity.class);
                intent.putExtra("compareFlag", true);
                int uid1 = getUID(packageNames.get(holder.getAdapterPosition()).getpName());
                intent.putExtra("UID1", uid1);
                intent.putExtra("packageName1", packageNames.get(holder.getAdapterPosition()).getpName());
                intent.putExtra("appName1", packageNames.get(holder.getAdapterPosition()).getAppName());
                count++;
            } else if (count == 1) {
                int uid2 = getUID(packageNames.get(holder.getAdapterPosition()).getpName());
                intent.putExtra("UID2", uid2);
                intent.putExtra("packageName2", packageNames.get(holder.getAdapterPosition()).getpName());
                intent.putExtra("appName2", packageNames.get(holder.getAdapterPosition()).getAppName());
                count = 0;
                this.startActivity(intent);
            }
        } else {
            count = 0;
        }
    }
}

class appNameComparator implements Comparator<PInfo> {
    @Override
    public int compare(PInfo p1, PInfo p2) {
        String appname1 = p1.getAppName();
        String appname2 = p2.getAppName();
        if (appname1.compareTo(appname2) > 0) {
            return 1;
        } else if (appname1.compareTo(appname2) < 0) {
            return -1;
        } else
            return 0;
    }
}

