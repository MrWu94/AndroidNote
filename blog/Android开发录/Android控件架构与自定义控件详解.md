### 目录
+ Android控件架构
+ View的测量与绘制
+ ViewGroup的测量与绘制
+ 自定义控件的三种方式
+ 事件的拦截机制
###Android界面的架构图


![
![](http://upload-images.jianshu.io/upload_images/1990324-6fde69321caafb65.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)](http://upload-images.jianshu.io/upload_images/1990324-da0af9714a998b2c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
###View的测量
想想我们在现实生活中，要去画一个图形，就必须知道他的大小和位置，同样，Android系统在绘制View前，也必须对View进行测量，告诉系统该画一个多大的View.
这个过程在onMeasure方法中进行
测量的模式可以分为以下三种
EXACTLY
**精确值模式**：layout_width与layout_heigth或者具体值时：系统使用的是EXACTLY
**AT_MOST** ：最大值模式 ，当layout_width或layout_height属性为wrap_content控件大小一般随着控件的子空间或内容的变化而变化。
**UNSPECIFIED**：不指定大小模式
View类默认的onMeasure方法只支持EXACTLY模式，所以如果在自定义控件的时候不重写onMeasure方法的话，就只能使用EXACTLY模式。控件可以响应你指定的具体宽高值或者match_parent属性。**如果要让自定义的View支持wrap_content属性，那么就必须重写onMeasure方法来指定wrap_content时的大小**

演示如何进行View 的测量。
```java
@Override
protected void onMeasure(int widthMeasureSpec,int heightMeasureSpec){
      super.onMeasure(widthMeasureSpec,heigthMeasureSpec);
}
```
这里要讲讲**widthMeasureSpec**和**heigthMeasureSpec**是怎么来的了。
这两个值都是有父视图经过计算传递给子视图的，说明父视图在一定程度上决定了子视图的大小。

重写onMeasure方法后，最终要做的工作就是把测量后的宽高值作为参数设置给setaMeasureDimension()
```java
@Override
proteced void onMeasure(int widthMeasureSpec,int heigthMeasureSpec){
        setMeasureDimension(measureWidth(widthMeasureSpec),measureHeigth(heigthMeasureSpec));
}
private int measureWidth(int measureSpec){
      int result=0;
      int specMode=MeasureSpec.getMode(measureSpec); 
      if(specMode==MeasureSpec.EXACTLY)
       {
              result=specSize;
       }else{
              reslut=200;
              if(spec==MeasureSpec.AT_MOST){
                    result==Math.min(result,specSize);
              }
        }
      return result;
}
private int measureHeigth(int measureSpec){
      int result=0;
      int specMode=MeasureSpec.getMode(measureSpec); 
      if(specMode==MeasureSpec.EXACTLY)
       {
              result=specSize;
       }else{
              reslut=200;
              if(spec==MeasureSpec.AT_MOST){
                    result==Math.min(result,specSize);
              }
        }
      return result;
}
```
### 自定义View
有三种方法来实现自定义控件
+ 对现有控件进行扩展
+ 通过组合来实现新的控件
+ 重写View来实现全新的控件。
在View通常有以下一些比较重要的回调方法
+ onFinishInfalte():从XML加载组建中回调
+ onSizeChanged: 组件大小改变时回调
+ onMeasure:回调该方法来进行测量
+ onLayout: 回调该方法来确定显示的位置
+ onTouchEvent :监听到触摸事件时回调

通过改变控件的绘制行为创建自定义View的思路，首先你要有画笔，所以你在重写onDraw()方法前都可以初始化画笔：
```java
mPaint1=new Paint();
mPaint1.setColor(#000000);//画笔颜色
mPaint1.setStyle(Paint.Style.FILL);
```
你有了画笔之后，当然要有纸，也就是Canvas画布
```java
canvas.draw(0,0,getMeasureWidth(),getMeasureHeight,mPaint1);
canvas.save();
canvas.translate(10,0);
canvas.restore();//回滚
```

未完。。。
