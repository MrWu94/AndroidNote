在View Animation中，其改变的是View的绘制效果，真正的View的属性保持不变，比如无论如何改变Button的位置，大小，button的有效点击区域还是没有应用动画时的区域，其位置和大小都不变。而Property Animation中，改变的是对象的实际属性。如Button的改变大小与位置，Button的位置与大小属性值都改变了.而且Property Animation不止可以应用于View。还可以应用于任何对象。

### Property Animation 中可以改变的属性
+ **Duration**:动画的持续时间
+ ** TimeInterpolation**:属性值的计算方式，如先快后慢
+ **TypeEvaluator**:根据属性的开始，结束值与TimInterpolator计算出的因子计算出当前时间的属性值
+ **RepeatCount and behavior**:重复次数与方式，如播放3次，5次，无线循环，可以次动画一只重复，或播放完再播放。
+ **Animation sets**：动画集合，即可以同时对一个对象应用几个动画，这些动画可以同时播放也可以对不同动画设置不同开始偏移
+ **Frame refreash delay**：多少时间刷新一次，即每隔多少时间计算一次属性值，默认为10ms，最终刷新时间还受系统进程调度与硬件的影响
###ObjectAnimator
```java
ObjectAnimator animator=ObjectAnimator.ofFloat(
view,
"translationX",
300
);
animator.setDuration(300);
animator.start();
```
可以直接使用属性动画的属性值：
translationX与translationY:这两个属性作为一种增量来控制着View对象从它布局容器中的左上角开始的位置。
roation,roationX与roationY:这三个属性控制View对象围绕支点进行2D与3D旋转
scaleX和scaleY:这两个属性控制着View对象围绕它的支点进行2D缩放。
privotX与privotY:这两个属性控制着View对象的支点位置，围绕这个支点进行旋转和缩放变换处理，默认情况下，该支点的位置就是View对象的中心点。
x和y:这是两个实用的属性，它描述了View对象在它的容器中的最终位置，它是最初的左上角坐标和translationX、translationY值得累计和
alpha:View对象的alphade 透明度，默认值是1，0代表完全透明
### ValueAnimator
```java
ValurAnimator animator=new ValueAnimator.ofFloat(0,100);
animator.setTarget(view);
animator.setDuration(1000).start();
animator.addUpdateListener(new AnimatorUpdateListener(){
    @override
    public void onAnimationUpdate(){
          Float vlaue=(Float)animation.getAnimatedValue();
    }
});
```
### 动画事件的监听
```java
objectAnimator anim=ObjectAnimator.ofFloat(view,"alpha",0.5f);
anim.addListener(new AnimatorListener(){
    @Override
    public void onAnimatonStart(Animator animation){
    }
    @Override
    public void onAnimationRepeat(Animator animation){
    }
    @Override
    public void onAnimationEnd(Animator animation){
    }
    @Override
    public void onAnimationCancel(Animator animation){
    }
});
```
###AnimatorSet
在属性动画中通过playTogether(),playSequentially(),animSet.play().with()、defore(),after()这些方法控制多个动画的协同工作方式。
 ```java
ObjectAnimator animator1=ObjectAnimator.ofFloat(view,"translationX",300f);
ObjectAnimator animator2=ObjectAnimator.ofFloat(view,"translationY",1f,0f,1f);
ObjectAnimator animator3=ObjectAnimator.ofFloat(view,"scaleX",1f,0f,1f);
AnimatorSet set=new AnimatorSet();
set.setDuration(1000);
set.playTogether(animator1,animator2,animator3);
set.start();
```
属性动画除了可以通过代码实现之外，还可以通过XML来定义，属性动画需要在res/animator/目录下
```java
<set android:ordering=["togrther"|"sequentially"]>
      <objectAnimator
              android:propertyName="string" //属性的名称
              android:duration="int"  //动画的时长
              android:valueFrom="float|int|color"  //属性的起始值
              android:valueTo="float|int|color"  //属性的结束值
              android:startOffset="int"  //动画的延迟时间
              android:repeatCount="int"  //动画的重复次数
              android:repeatMode=["repeat"|"reverse"]  //动画的重复模式
              android:valueType=["intType"|"floatType"]  //属性的类型
            />
         <animator
              android:propertyName="string"
              android:duration="int"
              android:valueFrom="float|int|color"
              android:valueTo="float|int|color"
              android:startOffset="int"
              android:repeatCount="int"
              android:repeatMode=["repeat"|"reverse"]
              android:valueType=["intType"|"floatType"]
            />
</set>
```
如何使用上面的属性动画呢？
```java
AnimatiorSet set=(Animator)AnimatorInflater.loadAnimator(myContext,R.anim.animator);
set.setTarget(mButton);
set.start();
```
###讲解静态工厂类
```java
public final class Boolean implements java.io.Serializable,
        Comparable<Boolean> {

    public static final Boolean TRUE = new Boolean(true);
    public static final Boolean FALSE = new Boolean(false);
        
    public static Boolean valueOf(boolean b) {
        return (b ? TRUE : FALSE);
    }
}
```
我们要获取一个 Boolean 的一个对象，可以使用构造函数 new Boolean(true) 也可以使用里面的静态方法Boolean.valueOf(true)，后者便是静态工厂方法。

创建ObjectAnimaror只需通过他的静态工厂直接返回ObjectAnimator对象
```java
public final class ObjectAnimator extends ValueAnimator 
```





