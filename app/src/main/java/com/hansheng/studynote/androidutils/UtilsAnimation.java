package com.hansheng.studynote.androidutils;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 2016/9/25.
 */

public class UtilsAnimation {
    static void zoomInAndOutAnimation(Context context, final ImageView image) {
        Animation zoomInAnimation = AnimationUtils.loadAnimation(context, R.anim.md_styled_zoom_in);
        final Animation zoomOutAnimation = AnimationUtils.loadAnimation(context, R.anim.md_styled_zoom_out);

        zoomInAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                image.startAnimation(zoomOutAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        image.startAnimation(zoomInAnimation);
    }
}
