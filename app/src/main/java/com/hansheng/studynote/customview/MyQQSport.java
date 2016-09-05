package com.hansheng.studynote.customview;

/**
 * Created by hansheng on 16-9-2.
 */

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

public class MyQQSport extends View {

    //总步数,好友平均步数,排名,当前时间,每天平均步数
    private int totalText,freAvgText,rankText,avgText;
    private String nowTime;
    private int[] dayStepNum;//步数
    private String[] dates;//最近7日
    private float percent;//外圆弧百分比
    //文字颜色
    private int textColor;
    //内外圈的颜色
    private int outCircleColor;
    private int inCircleColor;
    //头像
    private Bitmap headBitmap;

    //宽高,中心点,字体大小
    private int width,height;
    private float centerX,centerY,textGraySize,textSmallSize,textBigSize,arcStrokeWidth;

    //宽高比例
    private float heightScale = 1/6f;
    private float widthScale = 0.25f;

    private RectF mRect;//圆弧所需的矩形
    private Paint textSmallPaint;//排名的画笔
    private Paint textBigPaint;//总步数的画笔
    private Paint textGrayPaint;//其他字的画笔
    private Paint outSideLinePaint;//外圆弧的画笔
    private Paint inSideLinePaint;//内圆弧的画笔
    private Paint barPaint;//竖条画笔
    private Paint backgroundPaint;//背景画笔
    private Paint dashLinePaint;//虚线画笔

    //计步动画，圆弧动画
    private ValueAnimator mStepAnimator;
    private ValueAnimator mArcAnimator;

    public MyQQSport(Context context) {
        super(context);
    }

    public MyQQSport(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttr(context,attrs);
        //init(context);
    }

    //初始化配置参数
    public void initAttr(Context context, AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.MyQQSport);
        textColor = typedArray.getColor(R.styleable.MyQQSport_textColor, Color.parseColor("#FF8ED0FF"));
        outCircleColor = typedArray.getColor(R.styleable.MyQQSport_outCircleColor, Color.parseColor("#FF8ED0FF"));
        inCircleColor = typedArray.getColor(R.styleable.MyQQSport_inCircleColor, Color.parseColor("#FFD3EAFA"));
        typedArray.recycle();
        init(context);
    }

    //数据初始化
    private void init(Context context){
        //圆弧所需的矩形
        mRect = new RectF();
        //其他字的画笔
        textGrayPaint = new Paint();
        textGrayPaint.setAntiAlias(true);
        textGrayPaint.setStyle(Paint.Style.FILL);
        textGrayPaint.setColor(0xFFC6C1C3);
        textGrayPaint.setTextAlign(Paint.Align.CENTER);
        //排名的画笔
        textSmallPaint = new Paint();
        textSmallPaint.setAntiAlias(true);
        textSmallPaint.setStyle(Paint.Style.FILL);
        textSmallPaint.setColor(textColor);
        textSmallPaint.setTextAlign(Paint.Align.CENTER);
        //总步数的画笔
        textBigPaint = new Paint();
        textBigPaint.setAntiAlias(true);
        textBigPaint.setStyle(Paint.Style.FILL);
        textBigPaint.setColor(textColor);
        textBigPaint.setTextAlign(Paint.Align.CENTER);
        //外圆弧的画笔
        outSideLinePaint = new Paint();
        outSideLinePaint.setAntiAlias(true);
        outSideLinePaint.setStyle(Paint.Style.STROKE);
        outSideLinePaint.setColor(outCircleColor);
        outSideLinePaint.setStrokeCap(Paint.Cap.ROUND);
        //内圆弧的画笔
        inSideLinePaint = new Paint();
        inSideLinePaint.setAntiAlias(true);
        inSideLinePaint.setStyle(Paint.Style.STROKE);
        inSideLinePaint.setColor(inCircleColor);
        inSideLinePaint.setStrokeCap(Paint.Cap.ROUND);
        //竖条画笔
        barPaint = new Paint();
        barPaint.setAntiAlias(true);
        barPaint.setStyle(Paint.Style.FILL);
        barPaint.setColor(outCircleColor);
        barPaint.setStrokeCap(Paint.Cap.ROUND);
        //背景画笔
        backgroundPaint = new Paint();
        barPaint.setAntiAlias(true);
        barPaint.setStyle(Paint.Style.FILL);
        //虚线画笔
        dashLinePaint = new Paint();
        dashLinePaint.setAntiAlias(true);
        dashLinePaint.setColor(Color.parseColor("#C1C1C1"));
        dashLinePaint.setStyle(Paint.Style.STROKE);
        dashLinePaint.setPathEffect(new DashPathEffect(new float[]{8, 4}, 0));//画虚线
        //头像
//        headBitmap = BitmapFactory.decodeResource(context.getResources(),R.mipmap.icon_head);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        setMeasuredDimension(getWidthMeasure(widthMeasureSpec),getHeightMeasure(heightMeasureSpec));
    }

    //测量高度
    private int getHeightMeasure(int measureSpec){
        int result = getSuggestedMinimumHeight();
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        switch (specMode){
            case MeasureSpec.UNSPECIFIED:
                break;
            case MeasureSpec.EXACTLY:
            case MeasureSpec.AT_MOST:
                result = (int) (specSize*0.7);
                break;
        }
        return result;
    }

    //测量宽度
    private int getWidthMeasure(int measureSpec){
        int result = getSuggestedMinimumWidth();
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        switch (specMode){
            case MeasureSpec.UNSPECIFIED:
                break;
            case MeasureSpec.EXACTLY:
            case MeasureSpec.AT_MOST:
                result = (int) (specSize*0.9);
                break;
        }
        return result;
    }

    //计算所需各个数值大小
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        centerX = 0;
        centerY = -height*heightScale;
        mRect = new RectF(-width*widthScale,-width*widthScale+centerY,width*widthScale,width*widthScale+centerY);
        arcStrokeWidth = width/200f*6;
        textGraySize = width/200f*7;
        textSmallSize = width/200f*8;
        textBigSize = textSmallSize*3;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置各个paint所需的大小
        textGrayPaint.setTextSize(textGraySize);
        textSmallPaint.setTextSize(textSmallSize);
        textBigPaint.setTextSize(textBigSize);
        outSideLinePaint.setStrokeWidth(arcStrokeWidth);
        inSideLinePaint.setStrokeWidth(arcStrokeWidth);
        barPaint.setStrokeWidth(arcStrokeWidth);

        paintBelowBackground(canvas);//画下层背景
        paintUpBackground(canvas);//画上层背景
        paintDashLine(canvas);//画虚线
        paintBar(canvas);//画近七天的竖条
        paintBottom(canvas);

        canvas.translate(width/2,height/2); //移动坐标原点到中间
        //画圆弧
        Path path = new Path();
        path.addArc(mRect,120,300);
        canvas.drawPath(path,inSideLinePaint);
        path.reset();
        path.addArc(mRect,120,300*percent);
        canvas.drawPath(path,outSideLinePaint);
        //圆弧间的文字
        textGrayPaint.setTextAlign(Paint.Align.CENTER);
        textGrayPaint.setTextSize(textGraySize);
        canvas.drawText("截至"+nowTime+"已走",0,-textBigSize+centerY,textGrayPaint);
        canvas.drawText(totalText+"",0,textSmallSize/2+centerY,textBigPaint);
        canvas.drawText("好友平均"+freAvgText+"步",0,textBigSize+centerY,textGrayPaint);
        canvas.drawText("第",-textSmallSize/2*3+centerX,width*widthScale+centerY,textGrayPaint);
        canvas.drawText(rankText+"",0+centerX,width*widthScale+centerY,textSmallPaint);
        canvas.drawText("名",textSmallSize/2*3+centerX,width*widthScale+centerY,textGrayPaint);
    }

    //添加动画
    private void addAnimator(){
        //步数动画
        mStepAnimator = ValueAnimator.ofInt(0,totalText);
        mStepAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                totalText = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        //圆弧动画
        mArcAnimator = ValueAnimator.ofFloat(0,percent);
        mArcAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                percent = (Float) animation.getAnimatedValue();
                invalidate();
            }
        });
    }

    //开启动画
    public void startAnimator(){
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(2000);
        animatorSet.playTogether(mStepAnimator,mArcAnimator);
        animatorSet.start();
    }

    //画下层背景
    private void paintBelowBackground(Canvas canvas){
        RectF rectF = new RectF(0,0,width,height);
        backgroundPaint.setColor(Color.parseColor("#FF46A6F5"));
        canvas.drawRoundRect(rectF,textGraySize,textGraySize,backgroundPaint);
    }

    //画上层背景
    private void paintUpBackground(Canvas canvas){
        Path path = new Path();
        path.moveTo(0,0);
        path.lineTo(width-20,0);
        path.quadTo(width,0,width,20);
        path.lineTo(width,height/6*5);
        path.quadTo(width/2,height/14*13,0,height/6*5);
        path.lineTo(0,textGraySize);
        path.quadTo(0,0,textGraySize,0);
        backgroundPaint.setColor(Color.parseColor("#FFFFFFFF"));
        canvas.drawPath(path,backgroundPaint);
    }

    //画虚线
    private void paintDashLine(Canvas canvas){
        canvas.drawLine(textGraySize,height/7*5,width-textGraySize,height/7*5,dashLinePaint);
    }

    //画竖条
    private void paintBar(Canvas canvas){
        float avgWidth = width/8;
        textGrayPaint.setTextSize(textGraySize-10);
        textGrayPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("最近7日",textGraySize,height/7*5-textSmallSize-textGraySize,textGrayPaint);
        textGrayPaint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("平均"+avgText+"步/天",width-textGraySize,height/7*5-textSmallSize-textGraySize,textGrayPaint);
        textGrayPaint.setTextAlign(Paint.Align.CENTER);
        float avg = avgText;
        for(int i=0;i<7;i++){
            canvas.drawText(dates[i],avgWidth*(i+1),height/7*5+textBigSize,textGrayPaint);
            if(dayStepNum[i]<avgText){//当天步数小于平均值
                barPaint.setColor(Color.parseColor("#FFC6C1C3"));
                canvas.drawLine(avgWidth*(i+1),height/7*5+textSmallSize,avgWidth*(i+1),
                        height/7*5+11+textSmallSize*(1-dayStepNum[i]/avg),barPaint);
            }else {//当天步数大于平均值
                barPaint.setColor(outCircleColor);
                if(dayStepNum[i]/avgText>=2){
                    canvas.drawLine(avgWidth*(i+1),height/7*5+textSmallSize,avgWidth*(i+1),
                            height/7*5-textSmallSize,barPaint);
                }else {
                    canvas.drawLine(avgWidth*(i+1),height/7*5+textSmallSize,avgWidth*(i+1),
                            height/7*5-(dayStepNum[i]/avg-1)*textSmallSize,barPaint);
                }
            }
        }
    }

    //画低栏的头像和文字
    private void paintBottom(Canvas canvas){
        int len = height/7;
        Rect dst = new Rect((int) textGraySize,len*6+len/4,
                (int) textGraySize+len/2,len*6+len/4*3);
        canvas.drawBitmap(toRoundBitmap(headBitmap),null,dst,null);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(textGraySize/4*3);
        paint.setColor(Color.parseColor("#FFFFFF"));
        canvas.drawText("SFLin",textGraySize+len/4*3,len*6+len/4*2+10,paint);
        canvas.drawText("获得今日冠军",textGraySize+len/4*3+textBigSize,len*6+len/4*2+10,paint);
        canvas.drawText("查看 >",width-textBigSize,len*6+len/4*2+10,paint);
    }

    //将图片转换成圆形
    private Bitmap toRoundBitmap(Bitmap bitmap){
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int r;
        if (width > height) {
            r = height/2;
        } else {
            r = width/2;
        }
        Bitmap newBitmap = Bitmap.createBitmap(r*2,r*2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0,0,r*2,r*2);
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP);
        paint.setShader(bitmapShader);
        canvas.drawRoundRect(rectF,r,r,paint);
        return newBitmap;
    }

    //设置各个所需数据
    public void setData(int totalText,int freAvgText,int rankText,String nowTime,int avgText,int[] dayStepNum,String[] dates){
        this.totalText = totalText;
        this.freAvgText =freAvgText;
        this.rankText = rankText;
        this.nowTime = nowTime;
        this.avgText = avgText;
        this.dayStepNum = dayStepNum;
        this.dates = dates;
        if(totalText>freAvgText){
            percent = 1f;
        }else {
            percent = totalText/(float)freAvgText;
        }
        addAnimator();
    }
}