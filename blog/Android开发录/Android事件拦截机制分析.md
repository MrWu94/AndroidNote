**1、基础知识**
(1) 所有Touch事件都被封装成了MotionEvent对象，包括Touch的位置、时间、历史记录以及第几个手指(多指触摸)等。
 
(2) 事件类型分为ACTION_DOWN, ACTION_UP, ACTION_MOVE, ACTION_POINTER_DOWN, ACTION_POINTER_UP, ACTION_CANCEL，每个事件都是以ACTION_DOWN开始ACTION_UP结束。
**2、传递流程**
(1) 事件从Activity.dispatchTouchEvent()开始传递，只要没有被停止或拦截，从最上层的View(ViewGroup)开始一直往下(子View)传递。子View可以通过onTouchEvent()对事件进行处理。
 
(2) 事件由父View(ViewGroup)传递给子View，ViewGroup可以通过onInterceptTouchEvent()对事件做拦截，停止其往下传递。
 
(3) 如果事件从上往下传递过程中一直没有被停止，且最底层子View没有消费事件，事件会反向往上传递，这时父View(ViewGroup)可以进行消费，如果还是没有被消费的话，最后会到Activity的onTouchEvent()函数。
 
(4) 如果View没有对ACTION_DOWN进行消费，之后的其他事件不会传递过来。
 
(5) OnTouchListener优先于onTouchEvent()对事件进行消费。
 
上面的消费即表示相应函数返回值为true。
对于ViewGroup,重写了三个方法
```
@Override
public boolean dispatchTouchEvent(MotionEvent event){
      return super.dispatchTouchEvent(event);
}
//事件拦截
@Override
public boolean onInterceptTouchEvent(MotionEvent event){
        return super.onInterceptTouchEvent(event);
}
@Override
public boolean onTouchEvent(MotionEvent event){
        return super.onTouchEvent(event);
}
```
对于View来说，重写了如下的方法
```
@Override
public boolean onTouchEvent(MotionEvent event){
          return super.onTouchEvent(event); 
}
@Override
public boolean dispatchTouchEvent(MotionEvent event){
          return super.dispatchTouchEvent(event);
  }
```
![](http://upload-images.jianshu.io/upload_images/1990324-85879489a91185f9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

然后我们来看一下View中dispatchTouchEvent方法的源码：
```
public boolean dispatchTouchEvent(MotionEvent event){
        if(mOnTouchListener!=null&&(mViewFlags&ENABLED_MASK)==ENABLED&&mOnTouchListener.onTouch(this,event)){
          return true;    
}
      return onTouchEvent(event);
}
```
见上图也可以看到View处理事件的过程，在这个方法中，首先是进行了一个判断，如果mOnTouchListener != null，(mViewFlags & ENABLED_MASK) == ENABLED和mOnTouchListener.onTouch(this, event)这三个条件为真，返回true,否则执行onTouchEvent(event)






