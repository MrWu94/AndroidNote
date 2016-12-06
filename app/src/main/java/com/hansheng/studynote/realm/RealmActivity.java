package com.hansheng.studynote.realm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.hansheng.studynote.R;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by hansheng on 16-12-6.
 */

public class RealmActivity extends AppCompatActivity {

    Realm myRealm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button_layout);
        myRealm=Realm.getInstance(
                new RealmConfiguration.Builder(getApplicationContext())
                        .name("myOtherRealm.realm")
                        .build()
        );
        myRealm.beginTransaction();

       // Create an object
        Country country = myRealm.createObject(Country.class);

       // Set its fields
        country.setName("Norway");
        country.setPopulation(5165800);
        country.setCode("NO");

        myRealm.commitTransaction();

        RealmResults<Country> results1 =
                myRealm.where(Country.class).findAll();

        for(Country c:results1) {
            Log.d("results1", c.getName());
        }

    // Prints Norway, RussiaRealmResults<Country> results1 =
        myRealm.where(Country.class).findAll();

        for(Country c:results1) {
            Log.d("results1", c.getName());
        }
    }
}
