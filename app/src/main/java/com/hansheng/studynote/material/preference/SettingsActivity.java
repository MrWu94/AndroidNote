package com.hansheng.studynote.material.preference;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.Preference;
import android.view.MenuItem;
import android.view.View;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 17-5-17.
 */

public class SettingsActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(android.R.id.content, new SettingsFragment())
                    .commit();
        }

//        setTitle();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setTitle(){
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("设置");
    }



    public static class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener {
        private static final String KEY_TIP_OF_RECITE = "TIP_OF_RECITE";

        private Preference mDurationPreference;
        private Preference mIntervalPreference;
        private SwitchPreference mShowIconInNotification;
        private SwitchPreference mUseReciteOrNot;



        @Override public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.prefs);
            // 第一次点击单词本开关需要给用户一个功能提示框

        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            mShowIconInNotification = (SwitchPreference) findPreference("preference_show_icon_in_notification");
            mUseReciteOrNot = (SwitchPreference) findPreference("preference_use_recite_or_not");

        }



        @Override
        public boolean onPreferenceClick(Preference preference) {

            return false;
        }


        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {

            return true;
        }


    }

}

