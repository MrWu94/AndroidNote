package com.hansheng.studynote.snapshot;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 17-4-8.
 */

public class SnapshotActivity extends Activity implements View.OnClickListener {

    private ImageView img_display;
    private Button bt_screenshot;

    private Display mDisplay;
    private DisplayMetrics mDisplayMetrics;
    private Matrix mDisplayMatrix;
    private Bitmap mScreenBitmap;
    private WindowManager mWindowManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.snapshot_main);

        bt_screenshot = (Button)findViewById(R.id.bt_screenshot);
        img_display = (ImageView)findViewById(R.id.img_display);

        bt_screenshot.setOnClickListener(this);

        mDisplayMatrix = new Matrix();

        mWindowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        mDisplay = mWindowManager.getDefaultDisplay();
        mDisplayMetrics = new DisplayMetrics();
        mDisplay.getRealMetrics(mDisplayMetrics);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(bt_screenshot)){
            mDisplay.getRealMetrics(mDisplayMetrics);
            float[] dims = {mDisplayMetrics.widthPixels, mDisplayMetrics.heightPixels};
            float degrees = getDegreesForRotation(mDisplay.getRotation());
            boolean requiresRotation = (degrees > 0);
            if (requiresRotation) {
                // Get the dimensions of the device in its native orientation
                mDisplayMatrix.reset();
                mDisplayMatrix.preRotate(-degrees);
                mDisplayMatrix.mapPoints(dims);
                dims[0] = Math.abs(dims[0]);
                dims[1] = Math.abs(dims[1]);
            }
//            mScreenBitmap = Surface.screenshot((int) dims[0], (int) dims[1]);
            if (requiresRotation) {
                // Rotate the screenshot to the current orientation
                Bitmap ss = Bitmap.createBitmap(mDisplayMetrics.widthPixels,
                        mDisplayMetrics.heightPixels, Bitmap.Config.ARGB_8888);
                Canvas c = new Canvas(ss);
                c.translate(ss.getWidth() / 2, ss.getHeight() / 2);
                c.rotate(degrees);
                c.translate(-dims[0] / 2, -dims[1] / 2);
                c.drawBitmap(mScreenBitmap, 0, 0, null);
                c.setBitmap(null);
                mScreenBitmap = ss;
            }

            // If we couldn't take the screenshot, notify the user
            if (mScreenBitmap == null) {

                return;
            }

            // Optimizations
            mScreenBitmap.setHasAlpha(false);
            mScreenBitmap.prepareToDraw();

            img_display.setImageBitmap(mScreenBitmap);
        }

    }

    /**
     * @return the current display rotation in degrees
     */
    private float getDegreesForRotation(int value) {
        switch (value) {
            case Surface.ROTATION_90:
                return 360f - 90f;
            case Surface.ROTATION_180:
                return 360f - 180f;
            case Surface.ROTATION_270:
                return 360f - 270f;
        }
        return 0f;
    }
}
