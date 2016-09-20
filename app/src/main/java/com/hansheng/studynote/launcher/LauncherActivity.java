package com.hansheng.studynote.launcher;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.hansheng.studynote.R;

import java.util.List;

/**
 * Created by hansheng on 16-9-19.
 */

public class LauncherActivity extends AppCompatActivity {
    private List<ResolveInfo> mApps;
    GridView mGrid;
    private AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
            ResolveInfo info = mApps.get(position);

            //该应用的包名
            String pkg = info.activityInfo.packageName;
            //应用的主activity类
            String cls = info.activityInfo.name;

            Log.e("launcher=",pkg+"===="+cls);

            ComponentName componet = new ComponentName(pkg, cls);

            Intent i = new Intent();
            i.setComponent(componet);
            startActivity(i);
        }

    };

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadApps();
        setContentView(R.layout.launcher_mian);
        mGrid = (GridView) findViewById(R.id.apps_list);
        mGrid.setAdapter(new AppsAdapter());

        mGrid.setOnItemClickListener(listener);
    }


    private void loadApps() {
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        mApps = getPackageManager().queryIntentActivities(mainIntent, 0);
    }

    public class AppsAdapter extends BaseAdapter {
        public AppsAdapter() {
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView i;

            if (convertView == null) {
                i = new ImageView(LauncherActivity.this);
                i.setScaleType(ImageView.ScaleType.FIT_CENTER);
                i.setLayoutParams(new GridView.LayoutParams(50, 50));
            } else {
                i = (ImageView) convertView;
            }

            ResolveInfo info = mApps.get(position);
            i.setImageDrawable(info.activityInfo.loadIcon(getPackageManager()));

            return i;
        }

        public final int getCount() {
            return mApps.size();
        }

        public final Object getItem(int position) {
            return mApps.get(position);
        }

        public final long getItemId(int position) {
            return position;
        }
    }
}
