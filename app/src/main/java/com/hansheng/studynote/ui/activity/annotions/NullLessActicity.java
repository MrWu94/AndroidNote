package com.hansheng.studynote.ui.activity.annotions;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 17-5-8.
 */

public class NullLessActicity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sayHello(null);
        sayNollable(null);
        getName(null);
        getStringResource(R.string.about);
        IceCreamFlavourManager iceCreamFlavourManager=new IceCreamFlavourManager();
        iceCreamFlavourManager.setFlavour(IceCreamFlavourManager.VANILLA);
    }

    public void sayHello(@NonNull String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    public void sayNollable(@Nullable String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Nullable
    String getName(@NonNull String s) {
        return s;
    }

    public void getStringResource(@StringRes int resource) {
        Toast.makeText(this, getString(resource), Toast.LENGTH_SHORT).show();
    }

}
