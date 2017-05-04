###弹性滑动
在看Scroller的使用方法之前我们需要先了解一下View的scrollTo和scrollTo方法，
```
public void scrollTo(int x,int y){
      //如果当前偏移量变化
      if(mScrollX!=x||mScrollY!=y){
              int oldX=mScrollX;
              int oldY=mScrollY();
               //赋值偏移量
              mScrollX=x;
              mScrollY=y;
              invalidateParentCaches();
              //回调onScrollChanged方法
              onScrollChanged(mScrollX,mScrollY,oldX,oldY);
              if(!awakenScrollBars()){
                    postInvalidateOnAnimation();             
              }            
      }
}
```

scrollTo()是指将前视图内容横向偏移x距离，纵向偏移y距离。注意这里是View的内容的偏移，而不是View本身。而scrollBy()方法如下：
```
public void scrollBy(int x,int y){
        scrollTo(mScrollX+x,mScrollY+y); 
}
```
scrollBy()方法里直接调用了scrollTo()方法，表示在当前偏移量的基础上继续偏移(x,y)。
###使用Scroller开始
```
Scroller scroller=new Scroller(mContext);
//缓慢滚动到指定位置
private void smoothScrollTo(int destX,int destY){
      int scrollX=getScrollX();
      int deltaX=destX-scrollX;
      //1000ms内滑动destX,效果就是慢慢移动
      mScroller.startScroll(scrollX,0,detaX,0,1000);\
      //invalidate方法会导致View的重绘，在View的draw方法中又会去调用computeScroll方法
      //invalidate-draw-computeScroll
      invalidate();
}
@Override
public void computeScroll(){
      if(mScroller.computeScrollOffset()){
              scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
              //view第二次重绘
              postInvalidate();
      }
}
```
上面是Scroller典型的使用方法，当我们构造一个Scroller对象时并且调用它的startScroll方法时，Scroller内部其实什么也不做，它只是保存了我们传入的几个参数，这几个参数从startScroll的原型上就可以看出来
```
public void startScroll(int startX, int startY, int dx, int dy, int duration) {
  // mMode 分两种方式 1.滑动:SCROLL_MODE 2. **加速度滑动:FLING_MODE**
  mMode = SCROLL_MODE;
  // 是否滑动结束 这里是开始所以设置为false
  mFinished = false;
  // 滑动的时间
  mDuration = duration;
  // 开始的时间
  mStartTime = AnimationUtils.currentAnimationTimeMillis();
  // 开始滑动点的X坐标
  mStartX = startX;
  // 开始滑动点的Y坐标
  mStartY = startY;
  // 最终滑动到位置的X坐标
  mFinalX = startX + dx;
  // 最终滑动到位置的Y坐标
  mFinalY = startY + dy;
  // X方向上滑动的偏移量
  mDeltaX = dx;
  // Y方向上滑动的偏移量
  mDeltaY = dy;
  // 持续时间的倒数 最终用来计算得到插值器返回的值
  mDurationReciprocal = 1.0f / (float) mDuration;
}
```
###1.startScroll(int startX, int startY, int dx, int dy, int duration):
通过起始点、偏移的距离和滑动的时间来开始滑动。
+ startX 起始滑动点的X坐标
+ startY 起始滑动点的Y坐标
+ dx 滑动的水平偏移量。>0 则表示往左滑动。
+ dy 滑动的垂直偏移量。>0 则表示往上滑动。
+ duration 滑动执行的时间

startX和startY表示的是滑动的起点，dx与dy表示的是滑动的距离，而duration表示的是滑动的时间，即整个滑动过程完成所需要的时间，这里的滑动是指**View的内容（content）的滑动**，而非View本身位置的滑动。

