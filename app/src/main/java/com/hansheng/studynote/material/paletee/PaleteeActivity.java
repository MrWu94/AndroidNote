package com.hansheng.studynote.material.paletee;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.graphics.Palette;
import android.widget.TextView;

import com.hansheng.studynote.R;
import com.hansheng.studynote.ui.activity.BaseActivity;

import butterknife.Bind;

/**
 * Created by hansheng on 17-5-16.
 */

public class PaleteeActivity extends BaseActivity {
    private Bitmap bitmap;

    @Bind(R.id.tv_vibrant)
    TextView tvVibrant;
    @Bind(R.id.tv_dark_vibrant)
    TextView tvDatkVibrant;
    @Bind(R.id.tv_lighed_vibrant)
    TextView tvLightVibrant;
    @Bind(R.id.tv_muted_vibrant)
    TextView tvMutedVibrant;
    @Bind(R.id.tv_dark_muted)
    TextView tvDarkMuted;
    @Bind(R.id.tv_light_muted)
    TextView tvLightMuted;


    @Override
    protected int initContentView() {
        return R.layout.activity_paletee;
    }

    @Override
    protected void initView() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.zhangjinxuan);
        // Palette的部分
        Palette.Builder builder = Palette.from(bitmap);
        builder.generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                //获取到充满活力的这种色调
                Palette.Swatch vibrant = palette.getVibrantSwatch();
                //根据调色板Palette获取到图片中的颜色设置到toolbar和tab中背景，标题等，使整个UI界面颜色统一
                tvDarkMuted.setBackgroundColor(vibrant.getBodyTextColor());
            }
        });
        Palette palette=Palette.generate(bitmap);
        tvDatkVibrant.setBackgroundColor(palette.getDarkVibrantColor(0x000000));
        tvLightMuted.setBackgroundColor(palette.getLightMutedColor(0x000000));
        tvMutedVibrant.setBackgroundColor(palette.getMutedColor(0x000000));
        tvLightVibrant.setBackgroundColor(palette.getLightVibrantColor(0x000000));
        tvVibrant.setBackgroundColor(palette.getVibrantColor(0x000000));
    }
}
