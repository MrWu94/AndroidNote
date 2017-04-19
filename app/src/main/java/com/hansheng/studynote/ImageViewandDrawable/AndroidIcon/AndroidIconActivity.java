package com.hansheng.studynote.ImageViewandDrawable.AndroidIcon;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ScrollView;
import android.widget.TextView;

import com.hansheng.studynote.R;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.typeface.IIcon;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hansheng on 17-4-13.
 */

public class AndroidIconActivity extends AppCompatActivity {

    @Bind(R.id.all)
    TextView mAll;
    @Bind(R.id.fuli)
    TextView mFuli;
    @Bind(R.id.android)
    TextView mAndroid;
    @Bind(R.id.ios)
    TextView mIos;
    @Bind(R.id.video)
    TextView mVideo;
    @Bind(R.id.front)
    TextView mFront;
    @Bind(R.id.resource)
    TextView mResource;
    @Bind(R.id.app)
    TextView mApp;
    @Bind(R.id.more)
    TextView mMore;
    @Bind(R.id.scrollView)
    ScrollView mScrollView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.icon_main);
        ButterKnife.bind(this);
        setIconDrawable(mAll, MaterialDesignIconic.Icon.gmi_view_comfy);
        setIconDrawable(mFuli, MaterialDesignIconic.Icon.gmi_mood);
        setIconDrawable(mAndroid, MaterialDesignIconic.Icon.gmi_android);
        setIconDrawable(mIos, MaterialDesignIconic.Icon.gmi_apple);
        setIconDrawable(mVideo, MaterialDesignIconic.Icon.gmi_collection_video);
        setIconDrawable(mFront, MaterialDesignIconic.Icon.gmi_language_javascript);
        setIconDrawable(mResource, FontAwesome.Icon.faw_location_arrow);
        setIconDrawable(mApp, MaterialDesignIconic.Icon.gmi_apps);
        setIconDrawable(mMore, MaterialDesignIconic.Icon.gmi_more);
    }

    private void setIconDrawable(TextView view, IIcon icon) {
        view.setCompoundDrawablesWithIntrinsicBounds(new IconicsDrawable(this)
                        .icon(icon)
                        .color(Color.WHITE)
                        .sizeDp(16),
                null, null, null);
        view.setCompoundDrawablePadding(DimenUtils.dp2px(this, 10));
    }
}
