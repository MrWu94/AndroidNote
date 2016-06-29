package com.hansheng.customview.curve;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;

import com.hansheng.customview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hansheng on 2016/6/21.
 */

public class CurveView extends View implements View.OnTouchListener {

    float mHigh = 0;
    float Width = 0;
    List<Integer> listValue = new ArrayList<Integer>();
    List<RectF> listRectF = new ArrayList<RectF>();
    List<String> listDAY = new ArrayList<String>();
    boolean isKcal = false;
    int MaxValue = 0;

    private Paint paintbg;
    private Paint paintCircle;
    private Paint paintLine;
    private Paint paint;
    private Paint paintText;
    private Paint paintText2;

    private Path path;
    Shader mShader;
    int color_w = Color.argb(200, 255, 255, 255);

    int CircleR = 5;
    //4fd4d0
    int bottomH = 0;//距离底部高度，给文字显示用的

    int topH = 0;//距离顶部部高度，给选中后的文字显示用的

    Context context;

    public int select = -1;
    public int selectbottom = -1;

    public CurveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        CircleR = DensityUtil.dip2px(context, 3);
        bottomH = DensityUtil.dip2px(context, 12);
        topH = DensityUtil.dip2px(context, 16);

        this.context = context;
        initView();
    }

    public CurveView(Context context) {
        super(context);
        CircleR = DensityUtil.dip2px(context, 3);
        bottomH = DensityUtil.dip2px(context, 12);
        topH = DensityUtil.dip2px(context, 16);

        this.context = context;
        initView();
    }

    private void initView() {
        setOnTouchListener(this);

        mHigh = getHeight();
        Width = getWidth();

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(2);
        paint.setColor(Color.rgb(255, 255, 255));
        paint.setStyle(Paint.Style.FILL);


        paintText = new Paint();
        paintText.setAntiAlias(true);
        paintText.setStrokeWidth(4);
        paintText.setColor(getResources().getColor(R.color.textColor));//Color.rgb(255, 255, 255));
        paintText.setStyle(Paint.Style.FILL);
        paintText.setTextSize(16);


        paintText2 = new Paint();
        paintText2.setAntiAlias(true);
        paintText2.setStrokeWidth(4);
        paintText2.setColor(Color.WHITE);//.rgb(33, 35, 59));
        paintText2.setStyle(Paint.Style.FILL);
        paintText2.setTextSize(20);


        paintLine = new Paint();
        paintLine.setAntiAlias(true);
        paintLine.setStrokeWidth(1);
        paintLine.setColor(Color.rgb(255, 255, 255));
        paintLine.setStyle(Paint.Style.STROKE);
        PathEffect effects = new DashPathEffect(new float[]{6, 4, 6, 4}, 1);
        paintLine.setPathEffect(effects);

        paintbg = new Paint();
        paintbg.setAntiAlias(true);
        paintbg.setStrokeWidth(0);
        paintbg.setColor(Color.rgb(255, 255, 255));
        paintbg.setStyle(Paint.Style.FILL);

        paintCircle = new Paint();
        paintCircle.setAntiAlias(true);
        paintCircle.setStrokeWidth(2);
        paintCircle.setStyle(Paint.Style.FILL);
        paintCircle.setColor(getResources().getColor(R.color.white));

        path = new Path();
        mShader = new LinearGradient(0, 0, 0, getHeight(), new int[]{color_w,
                getResources().getColor(R.color.transparency)}, null, Shader.TileMode.CLAMP);
        paintbg.setShader(mShader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);

        float High = mHigh - bottomH;

        if (listValue != null &&
                listValue.size() != 0 && listDAY != null && listDAY.size() != 0) {

            for (int i = 0; i < listValue.size(); i++) {
                float h = High - High * listValue.get(i) / MaxValue;


                h = h + topH;
                float w = (float) (Width / listValue.size() * (i + 0.5));

                if (High * listValue.get(i) / MaxValue < CircleR) {
                    h = High - CircleR - 1;
                }
                if (h < CircleR) {
                    h = CircleR + 1;
                }
                if (h > High - CircleR) {
                    h = High - CircleR + 1;
                }
                if (MaxValue == 0)
                    h = High - CircleR - 1;
                if (i == 0) {
                    path.moveTo(w, h);
                } else if (i == listValue.size() - 1) {

                    path.lineTo(w, h);
                    path.lineTo(w, High);

                    path.lineTo((float) (Width / listValue.size() * 0.5), High);

                    path.lineTo((float) (Width / listValue.size() * 0.5),
                            High - High * listValue.get(0) / MaxValue);

                    path.close();

                    canvas.drawPath(path, paintbg);

                } else {
                    path.lineTo(w, h);
                }

                if (i != listValue.size() - 1) {
                    float stopY = High - High * listValue.get(i + 1) / MaxValue + topH;


                    float stopX = (float) (Width / listValue.size() * (i + 1.5));


                    if (High * listValue.get(i + 1) / MaxValue < CircleR) {
                        stopY = High - CircleR - 1;
                    }
                    if (stopY < CircleR) {
                        stopY = CircleR + 1;
                    }
                    if (MaxValue == 0)
                        stopY = High - CircleR - 1;

                    if (stopY > High - CircleR) {
                        stopY = High - CircleR + 1;
                    }


                    canvas.drawLine(w, h, stopX, stopY, paint);
                }
            }
            listRectF.clear();

            for (int i = 0; i < listDAY.size(); i++) {
                float w = (float) (Width / listValue.size() * (i + 0.5));
                String day = listDAY.get(i);
                if (listDAY.size() == 7) {
                    paintText.setTextSize(DensityUtil.dip2px(getContext(), 10));
                    paintText2.setTextSize(DensityUtil.dip2px(getContext(), 10));
                    int width = getTextWidth(paintText, day);

                    if (selectbottom == i) {

                        canvas.drawText(day, w - width / 2, mHigh - 2, paintText2);

                        selectbottom = -1;
                    } else {

                        canvas.drawText(day, w - width / 2, mHigh - 2, paintText);
                    }
                } else if (listDAY.size() == 30) {
                    paintText.setTextSize(DensityUtil.dip2px(getContext(), 10));
                    paintText2.setTextSize(DensityUtil.dip2px(getContext(), 10));
                    if (i == 0 || i == 7 || i == 15 || i == 22 || i == 29) {

                        int width = getTextWidth(paintText, day);

                        if (selectbottom == i) {

                            if (i == 0) {
                                canvas.drawText(day, w, mHigh - 2, paintText2);

                            } else {
                                canvas.drawText(day, w - width / 2, mHigh - 2, paintText2);

                            }
                            selectbottom = -1;
                        } else {
                            if (i == 0) {
                                canvas.drawText(day, w, mHigh - 2, paintText);

                            } else {
                                canvas.drawText(day, w - width / 2, mHigh - 2, paintText);

                            }
                        }
                    }


                }


            }
            for (int i = 0; i < listValue.size(); i++) {
                float h = High - High * listValue.get(i) / MaxValue + topH;
                float w = (float) (Width / listValue.size() * (i + 0.5));

                if (High * listValue.get(i) / MaxValue < CircleR) {
                    h = High - CircleR - 1;
                }
                if (h < CircleR) {
                    h = CircleR + 1;
                }
                if (h > High - CircleR) {
                    h = High - CircleR + 1;
                }


                if (MaxValue == 0)
                    h = High - CircleR - 1;
                canvas.drawCircle(w, h, CircleR, paint);

                RectF f1 = new RectF();

                float wrf = (float) (Width / listValue.size() / 2);

                f1.set(w - wrf, 0, w + wrf, getHeight());


                if (select == i) {

                    int width = getTextWidth(paintText2, listValue.get(i) + "");
                    int high = (int) getTextHigh(paintText2);


                    if (w - width / 2 < 0) {
                        canvas.drawText(listValue.get(i) + "", w, high, paintText2);

                    } else if (w + width / 2 > Width) {
                        canvas.drawText(listValue.get(i) + "", w - width, high, paintText2);
                    } else {
                        canvas.drawText(listValue.get(i) + "", w - width / 2, high,
                                paintText2);

                    }

                    select = -1;
                }

                listRectF.add(f1);

            }

        }

    }

    Animation popup_enter_bottom;
    Animation popup_out_bottom;
    SelectItem mselectItem;
    int vid = 0;

    public void setValue(final List<Integer> listValue, final boolean cal, final boolean anim,
                         final List<String> listDay, SelectItem mselectItem, int vid) {
        this.mselectItem = mselectItem;
        this.vid = vid;
        this.listDAY = new ArrayList<String>();
        ;
        this.listDAY.addAll(listDay);
        this.isKcal = cal;
        if (this.listValue != null &&
                this.listValue.size() != 0 && anim) {

            popup_out_bottom = AnimationUtils.loadAnimation(getContext(), R.anim.sacle_bottom_out);
            startAnimation(popup_out_bottom);

            popup_out_bottom.setAnimationListener(new AnimationListener() {

                @Override
                public void onAnimationStart(Animation arg0) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void onAnimationRepeat(Animation arg0) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void onAnimationEnd(Animation arg0) {
                    setVisibility(View.INVISIBLE);
                    play(listValue, cal, anim, 10);

                }
            });

        } else {

            play(listValue, cal, anim, 600);

        }
    }

    private void play(final List<Integer> listValue, final boolean cal, boolean anim, int time) {
        this.listValue = new ArrayList<Integer>();
        this.listValue.addAll(listValue);
        MaxValue = 0;
        post(new Runnable() {

            @Override
            public void run() {
                for (int a : listValue) {
                    if (a > MaxValue)
                        MaxValue = a;
                }
                initView();
                invalidate();
            }
        });

        if (anim) {
            setVisibility(View.INVISIBLE);
            popup_enter_bottom = AnimationUtils.loadAnimation(getContext(), R.anim.sacle_bottom_in);
            popup_enter_bottom.setAnimationListener(new AnimationListener() {

                @Override
                public void onAnimationStart(Animation arg0) {
                    setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animation arg0) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void onAnimationEnd(Animation arg0) {
                    // TODO Auto-generated method stub

                }
            });
            postDelayed(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    startAnimation(popup_enter_bottom);
                }
            }, time);

        }

    }

    @Override
    public boolean onTouch(View arg0, MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_UP) {
            float x = event.getX();
            float y = event.getY();

            for (int i = 0; i < listRectF.size(); i++) {
                if (listRectF.get(i).contains(x, y)) {

                    if (mselectItem != null) {
                        select = i;
                        selectbottom = i;
                        mselectItem.onSelectItem(this.vid, i);
                    }

                    break;
                }

            }
        }
        return true;
    }

    public void ShowView() {
        setValue(this.listValue, this.isKcal, false, this.listDAY, mselectItem, this.vid);
    }

    public interface SelectItem {
        void onSelectItem(int vid, int item);
    }

    public int getTextWidth(Paint paint, String str) {
        int iRet = 0;
        if (str != null && str.length() > 0) {
            int len = str.length();
            float[] widths = new float[len];
            paint.getTextWidths(str, widths);
            for (int j = 0; j < len; j++) {
                iRet += (int) Math.ceil(widths[j]);
            }
        }
        return iRet;
    }

    public static float getTextHigh(Paint paint) {
        Paint.FontMetrics fm = paint.getFontMetrics();
        return fm.descent - fm.ascent;
    }

}