###初始化ViewDragHelper
首先，自然是需要ViewDragHelper。ViewDrapHelper通常定义在一个ViewGroup的内部，并通过其静态工厂方法进行初始化
```
    /**
     *静态工厂方法创建一个ViewDragHelper
     * Factory method to create a new ViewDragHelper.
     *
     * @param forParent Parent view to monitor
     * @param cb Callback to provide information and receive events
     * @return a new ViewDragHelper instance
      *返回一个ViewDragHelper实例
     */
    public static ViewDragHelper create(ViewGroup forParent, Callback cb) {
        return new ViewDragHelper(forParent.getContext(), forParent, cb);
    }
//初始化一个ViewDragHelper
mViewDragHelper=ViewDragHelper.create(this,callback);
```
它的第一个参数是要监听的View,通常需要是一个ViewGroup，即parentview;第二个参数是一个CallBack回调
```

    public static abstract class Callback {

       //这个事件在用户触摸到View后回调
        public void onViewDragStateChanged(int state) {}

        //常用于滑动时更改Scale进行缩放等效果。
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {}
       //这个事件在用户触摸到View后回调
        public void onViewCaptured(View capturedChild, int activePointerId) {} 
        //手指离开屏幕之后，子view滑动会初始位置，当我们监听ACTION_UP事件，并通过调用Scroller来实现。
        public void onViewReleased(View releasedChild, float xvel, float yvel) {}  

        public void onEdgeTouched(int edgeFlags, int pointerId) {}

        //是否锁定该边缘的触摸,默认返回false,返回true表示锁定
        public boolean onEdgeLock(int edgeFlags) {
            return false;
        }    
        //边缘触摸开始时回调
        //edgeFlags : 当前触摸的flag 有: EDGE_LEFT,EDGE_TOP,EDGE_RIGHT,EDGE_BOTTOM
        //pointerId : 用来描述边缘触摸操作的id
        public void onEdgeDragStarted(int edgeFlags, int pointerId) {}

        public int getOrderedChildIndex(int index) {
            return index;
        }
        public int getViewHorizontalDragRange(View child) {
            return 0;
        }  
        public int getViewVerticalDragRange(View child) {
            return 0;
        }
        
        public abstract boolean tryCaptureView(View child, int pointerId); 
        //水平方向上的滑动
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            return 0;
        }
        //垂直方向上的滑动，参数top，代表在垂直方向上child移动的距离，而dy则表示比较前一次的增量
        public int clampViewPositionVertical(View child, int top, int dy) {
            return 0;
        }
    }
```
###拦截事件
重写事件拦截方法，将事件传递给ViewDragHelper进行处理
```
@Override
public boolean onInterceptTouchEvent(MotionEvent event){
      return mViewDragHelper.shouldInterceptTouchEvent(event);
}
@Override
public boolean onTouchEvent(MotionEvent event){
        mViewDragHelper.processTouchEvent(event);
        return true;
}
```
处理computeScroll,使用ViewDragHelper同样需要重写computeScroll方法，因为ViewDragHelper内部也是通过Scroller来实现平滑移动的。
```
@Override、
public void computeScroll(){
      if(mViewDragHelper.continueSettling(true){
            ViewCompat.postInvalidateOnAnimation(this); 
      }
}
```
处理CallBack
```
private ViewDragHelper.CallBack callback=new ViewDragHelper.Callback(){
      @Override
      public boolean tryCaptureview(View child,int pointId){
            return false;
       }
}
```
开源项目中的使用ViewDragHelper在各种关于拖拽和各种手势动画的开源库中使用广泛,我这里就简要列出一些,大家可以多去看看是如何使用ViewDragHelper的:
[SwipeBackLayout](https://github.com/ikew0ng/SwipeBackLayout)
[android-card-slide-panel](https://github.com/xmuSistone/android-card-slide-panel)
[FlowingDrawer](https://github.com/mxn21/FlowingDrawer)

