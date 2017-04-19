package com.hansheng.studynote.ImageViewandDrawable.Picasso;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by hansheng on 17-2-14.
 */

public class PicassoUtils {

    public static void loadImage(Context context, String url, int placeholder, int error, Object tag, ImageView target) {
        if (!TextUtils.isEmpty(url)) {
            Picasso.with(context).load(url)
                    .placeholder(placeholder)
                    .error(error)
                    .tag(tag)
                    .into(target);
        } else {
            target.setImageResource(error);
        }
    }

    public static void loadImage(Context context, String url, Drawable placeholder, Drawable error, Object tag, ImageView target) {
        if (!TextUtils.isEmpty(url)) {
            Picasso.with(context).load(url)
                    .placeholder(placeholder)
                    .error(error)
                    .tag(tag)
                    .into(target);
        } else {
            target.setImageDrawable(error);
        }
    }

    public static void loadImage(Context context, String url, int placeholder, int error,
                                 Object tag, ImageView target, com.squareup.picasso.Callback callback) {
        if (!TextUtils.isEmpty(url)) {
            Picasso.with(context).load(url)
                    .placeholder(placeholder)
                    .error(error)
                    .tag(tag)
                    .into(target, callback);
        } else {
            target.setImageResource(error);
        }
    }

    public static void loadImage(Context context, String url, Drawable placeholder, Drawable error,
                                 Object tag, ImageView target, com.squareup.picasso.Callback callback) {
        if (!TextUtils.isEmpty(url)) {
            Picasso.with(context).load(url)
                    .placeholder(placeholder)
                    .error(error)
                    .tag(tag)
                    .into(target, callback);
        } else {
            target.setImageDrawable(error);
        }
    }

    public static void loadImage(Context context, String url, Drawable placeholder, Drawable error, int width, int height,
                                 Object tag, ImageView target) {
        if (!TextUtils.isEmpty(url)) {
            Picasso.with(context).load(url)
                    .placeholder(placeholder)
                    .error(error)
                    .resize(width, height)
                    .tag(tag)
                    .into(target);
        } else {
            target.setImageDrawable(error);
        }
    }

    public static void loadImage(Context context, String url, Drawable placeholder, Drawable error, int width, int height,
                                 Object tag, ImageView target, com.squareup.picasso.Callback callback) {
        if (!TextUtils.isEmpty(url)) {
            Picasso.with(context).load(url)
                    .placeholder(placeholder)
                    .error(error)
                    .resize(width, height)
                    .tag(tag)
                    .into(target, callback);
        } else {
            target.setImageDrawable(error);
        }
    }

    public static void loadImage(Context context, String url, int placeholder, int error, int width, int height,
                                 Object tag, ImageView target) {
        if (!TextUtils.isEmpty(url)) {
            Picasso.with(context).load(url)
                    .placeholder(placeholder)
                    .error(error)
                    .resize(width, height)
                    .tag(tag)
                    .into(target);
        } else {
            target.setImageResource(error);
        }
    }

    public static void loadImage(Context context, String url, int placeholder, int error, int width, int height,
                                 Object tag, ImageView target, com.squareup.picasso.Callback callback) {
        if (!TextUtils.isEmpty(url)) {
            Picasso.with(context).load(url)
                    .placeholder(placeholder)
                    .error(error)
                    .resize(width, height)
                    .tag(tag)
                    .into(target, callback);
        } else {
            target.setImageResource(error);
        }
    }


}
