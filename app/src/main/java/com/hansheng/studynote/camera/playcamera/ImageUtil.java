package com.hansheng.studynote.camera.playcamera;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * Created by hansheng on 16-12-12.
 */

public class ImageUtil {
    /**
     * 旋转Bitmap
     *
     * @param b
     * @param rotateDegree
     * @return
     */
    public static Bitmap getRotateBitmap(Bitmap b, float rotateDegree) {
        Matrix matrix = new Matrix();
        matrix.postRotate((float) rotateDegree);
        Bitmap rotaBitmap = Bitmap.createBitmap(b, 500, 200, 800, 800, matrix, false);
        return rotaBitmap;
    }
}