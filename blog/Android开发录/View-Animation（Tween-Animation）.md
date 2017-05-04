 view动画的一个非常大的缺陷就是不具备交互性，当某个元素发生视图动画后，其响应事件的位置还依然留在动画前的地方，所以视图动画只能做普通的动画效果避免交互的发生。
###透明度动画
```
AlphaAnimation aa=new AlphaAnimation();
aa.setDuration(1000);
view.startAnimation();
```
###旋转动画
```
RotateAnimation ra=new RotateAnimation(0,360,100,100);
ra.setDuration(1000);
view.startAnimation();
```
###位移动画
```
TranslateAnimation ta=new TranslateAnimation(0,200,0,300);
ta.setDuration(1000);
view.startAnimation();
```
###缩放动画
```
ScaleAnimation sa=new ScaleAnimation(0,2,0,2);
sa.setDuration(1000);
view.startAnimation();
```

###动画集合
```
AnimationSet as=new AnimationSet(true);
as.setDuration(1000);

AlphaAnimation aa=new AlphaAnimation(0,1);
aa.setDuration(1000);
as.addAnimation(aa);

TranslateAnimation ta=new translateAnimation(0,100,0,200);
ta.setDuration(1000);
as.addAnimation(ta);

view.startAnimation(as);
```
对于动画事件，Android也提供了对应的监听回调，要添加相应的监听方法
```
animation.setAnimationListener(new Animation.AnimationListener(){
      @Override
      public void onAnimationStart(Animation animation){
      }
      @Override
      public void onAnimationEnd(Animation animation){
      }
       @Override
      public void  onAnimationRepeat(Animation animation){
      }
});
```
