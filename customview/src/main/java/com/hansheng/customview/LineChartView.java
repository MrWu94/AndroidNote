package com.hansheng.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by hansheng on 2016/6/26.
 */
public class LineChartView extends View {

    private Paint rectPaint;// 设置左侧为白色，显示数表
    private Paint hLinePaint;// 坐标轴水平内部 虚线画笔
    private Paint titlePaint;// 绘制文本的画笔
    private Paint linePaint;
    private Paint paint;// 矩形画笔 柱状图的样式信息
    private int[] text;// 折线的转折点
    int x, y, preX, preY;
    // 坐标轴左侧的数标
    private Bitmap mBitmap;
    // 坐标轴底部的星期数
    private String[] str = { "62", "72", "82", "92", "102", "112", "122",
            "132", "142" };

    private HistogramAnimation ani;
    private int flag;

    public LineChartView(Context context) {
        super(context);
        init(context, null);
    }

    public LineChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        text = new int[] { 6, 5, 5, 4, 5, 3, 2, 3, 1, 1 };

        ani = new HistogramAnimation();
        ani.setDuration(4000);

        rectPaint = new Paint();
        titlePaint = new Paint();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        linePaint = new Paint();

        //
        titlePaint.setAntiAlias(true);
        Rect bundle1 = new Rect();

        Rect bundle2 = new Rect();
        hLinePaint = new Paint();

        int perWidth = getWidth() / 10;// 将宽度分为10部分
        int hPerHeight = getHeight() / 10;// 将高度分为10部分
        rectPaint.setColor(Color.WHITE);

        canvas.drawRect(0, 0, dp2px(30), getHeight(), rectPaint);// 画一块白色区域

        Path path = new Path();// 折线图的路径
        mBitmap = Bitmap.createBitmap(getWidth(), getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas mCanvas = new Canvas(mBitmap);

        for (int i = 0; i < 10; i++) {// 画x线，并在左侧显示相应的数值
            hLinePaint.setTextAlign(Paint.Align.CENTER);
            hLinePaint.setColor(Color.WHITE);
            y = i * hPerHeight;
            if (i == 2) {
                hLinePaint.setStrokeWidth(4);
                for (int j = 0; j < 10; j++) {
                    canvas.drawLine(dp2px(30) + j * perWidth, y, dp2px(28)
                            + (j + 1) * perWidth, y, hLinePaint);
                }
                titlePaint.setTextSize(sp2px(20));
                titlePaint.getTextBounds(str[i - 1], 0, str[i - 1].length(),
                        bundle1);
                canvas.drawText(str[i - 1], dp2px(25) - bundle1.width(), i
                        * hPerHeight + (bundle1.height() / 2), titlePaint);
            } else {
                hLinePaint.setStrokeWidth(1);
                canvas.drawLine(dp2px(30), y, getWidth(), y, hLinePaint);
                if (i != 0) {
                    titlePaint.setTextSize(sp2px(15));
                    titlePaint.getTextBounds(str[i - 1], 0,
                            str[i - 1].length(), bundle2);
                    canvas.drawText(str[i - 1], dp2px(25) - bundle2.width(), i
                            * hPerHeight + (bundle2.height() / 2), titlePaint);
                }
            }

            x = i * perWidth + dp2px(30);
            if (i == 0) {
                path.moveTo(x, text[i] * hPerHeight);
            } else {
                path.lineTo(x, text[i] * hPerHeight);
            }
            linePaint.setColor(Color.parseColor("#bb2222"));
            linePaint.setAntiAlias(true);

            paint = new Paint();
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(dp2px(1));
            if (i != 0) {
                mCanvas.drawCircle(x, text[i] * hPerHeight, dp2px(3), linePaint);
            }
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER));
            mCanvas.drawPath(path, paint);
        }

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        paint.setStyle(Paint.Style.FILL);

        mCanvas.drawRect(preX + dp2px(30), 0, getWidth(), getHeight(), paint);
        canvas.drawBitmap(mBitmap, 0, 0, null);
        // Log.i("tag", "onDraw()1111");
    }

    private int dp2px(int value) {
        float v = getContext().getResources().getDisplayMetrics().density;
        return (int) (v * value + 0.5f);
    }

    private int sp2px(int value) {
        float v = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (v * value + 0.5f);
    }

    public void start(int flag) {
        startAnimation(ani);
        this.flag = flag;
    }

    /**
     * 集成animation的一个动画类
     *
     * @author
     */
    private class HistogramAnimation extends Animation {
        @Override
        protected void applyTransformation(float interpolatedTime,
                                           Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            if (interpolatedTime < 1.0f && flag == 2) {
                preX = (int) ((getWidth() - dp2px(30)) * interpolatedTime);
            } else {

                preX = getWidth();

            }
            invalidate();
        }
    }

}
