1、滑动一个View，本质上来说就是移动一个View。改变其当前所处的位置，他的原理与动画效果的实现相似，都是通过不断改变View的坐标来实现这一效果。要实现View的滑动，**必须监听触摸的事件，并根据事件传入的坐标，动态且不断地改变View的坐标，从而实现跟随用户触摸的滑动而滑动**
###Android坐标系
在Android中，将屏幕最左上角的顶点作为Android坐标系的原点，从这个向右X轴正方向，从这个点向下就是Y轴正方向。在触控事件中使用**getRawX(),getRawY()**方法获取Android坐标系的坐标。
###视图坐标系
在触控事件中，通过getX(),getY获得的坐标就是视图坐标系的坐标。


![坐标系](http://upload-images.jianshu.io/upload_images/1990324-10cd9aceae5d42e2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

2、触控事件---MotionEvent
MotionEvent封装的一些常用的事件常量，**触控事件的不同类型**
```
//单点触摸按下的动作
public final int ACTION_DOWN=0;
//单点触摸离开的动作
public final int ACTION_UP =1;
//触摸点移动的动作
public final int ACTION_MOVE=2;
//触摸动作取消
public final int ACTION_CANCEL=3；
//触摸动作超出边界
public final int ACTION_OUTSIDE=4;
//多点触摸按下的动作
public final int ACTION_POINTER_DOWN=5;
//多点离开的动作
public final int ACTION_POINTER_UP=6;
```
我们会在onTouchEvent(MotionEvent event)方法中通过event.getAction()方法来获取触控事件的类型，并使用switch-case方法来进行筛选，这个代码的模式基本固定
```
@Override
public boolean onTouchEvent(MotionEvent event){
      //获取当前输入点的X，Yz坐标（视图坐标）
      int x=(int)event.getX();
      int y=(int)event.getY();
      switch(event.getAction()){
              case MotionEvent.ACTION_DOWN
                      break;
              case MotionEvent.ACTION_MOVE
                    break;
              case MotionEvent_ACTION_UP
                   break;
        }
        return true
}
```



![获取坐标系的方法.png](http://upload-images.jianshu.io/upload_images/1990324-e89243e423c82e91.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
**View提供的获取坐标方法**
+ getTop():获取到的是View自身的顶边到父布局顶边的距离。
+ getLeft():获取到的是View自身的左边到其父布局左边的距离
+ getRight()：获取的是View自身的右边到其父布局左边的距离
+ getBottom():获取的是View自身的底边到其父布局顶边的距离
**MotionEvent提供的方法**
+ getX():获取点击事件距离控件左边的距离，即视图坐标
+ getY()：获取点击事件距离控件顶边的距离，即视图坐标
+ getRawX()： 获取点击事件距离整个屏幕左边的距离，即绝对坐标
+ getRawY():  获取点击事件距离整个屏幕顶边的距离，即绝对坐标

实现滑动的七种方法
**layout方法**
在每次回调onTouchEvent的时候，我们都来获取一下触摸点的坐标
```
int x=(int)event.getX();
int y=(int )event.getY();
```
接着，在ACTION_DOWN事件中记录触摸点的坐标
```
case MotionEvent.ACTION_DOWN
       lastX=x;
       laseY=y;
       break;
```
最后，可以在ACTION_MOVE事件中计算偏移量，并将偏移量作用到Layout方法中，在目前layout的left,top,right,bottom基础上，增加计算出来的偏移量
```
case MotionEvent.ACTION_MOVE:
        int offsetX=x-laseX;
        int offsetY=y-laseY;
        layout(getLeft()+offsetX(),getTop()+offsetY,getRight()+offsetX,getBottom()+offsetY);
        break;
```

**offsetLeftAndRight()与offsetTopAndBottom()**
```
offsetLeftAndRight(offsetX);
offsetTopAndBottom(offsetY);
```

**LayoutParams**
LayoutParsms保存了一个View的布局参数。因此可以在程序中，通过改变layoutParams来动态地修改一个布局的位置参数，从而达到改变View位置的效果。我们可以很方便地在程序中使用getLayoutParam来获取一个View的LayoutParams
```
LinearLayout.LayoutParams layoutParams=(LinearLayout.LayoutParams)getLayoutParams;
layoutParams.leftMargain=getLeft()+offsetX；、
layoutParams.topMargin=getTop+offsetY;
setLayoutParams(layoutParams);
```
在通过该变LayoutParams来改变一个View的位置时，通常改变的是这个View的Margin属性，所以除了使用=布局的LayoutParam之外，还可以使用ViewGroup.MarginLayoutParams来实现这样的一个功能
```
ViewGroup.MarginLayoutParams layoutParams=(ViewGroup.MarginLayoutParams)getLayoutParams();
layoutParams.leftMargin=getLeft()+offsetX;
layoutParams.topMargin=getTop()+offsetY;
setLayoutParams(layoutParams);
```

**scrollTo与scrollBy**
在提供View中，系统提供了scrollTo、scrollBy两种方式来改变一个View的位置
scrollTo表示移动到一个具体的坐标点（x,y）.
scrollBy表示移动的增量dx，dy
```
int offsetX=x-lastX;
int offsetY=y-lastY;
scrollBy(offsetX,offsetY);
```
这种方式不好的是，只移动View的内容（content）
((View)getParent()).scrollBy(offsetX,offsetY);当再次移动View的时候，你会View乱动。

![](http://upload-images.jianshu.io/upload_images/1990324-b25dceed56f04f03.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
如图可以看出，当scrollX(100,0),屏幕却向X轴负方向移动了，这就是因为参考系选择的不同
要实现随手指移动而滑动的效果，就必须改为负值
```
int offsetX=x-lastX;
int offsetY=y-lastY;
((View)getParent()).ScrollTo(-offsetX,-offsetY);
```

    
