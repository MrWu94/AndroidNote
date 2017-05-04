[Android RelativeLayout 属性]
// 相对于给定ID控件
```xml
android:layout_above 将该控件的底部置于给定ID的控件之上;
android:layout_below 将该控件的底部置于给定ID的控件之下;
android:layout_toLeftOf    将该控件的右边缘与给定ID的控件左边缘对齐;
android:layout_toRightOf  将该控件的左边缘与给定ID的控件右边缘对齐;
 
android:layout_alignBaseline  将该控件的baseline与给定ID的baseline对齐;
android:layout_alignTop        将该控件的顶部边缘与给定ID的顶部边缘对齐;
android:layout_alignBottom   将该控件的底部边缘与给定ID的底部边缘对齐;
android:layout_alignLeft        将该控件的左边缘与给定ID的左边缘对齐;
android:layout_alignRight      将该控件的右边缘与给定ID的右边缘对齐;
// 相对于父组件
android:layout_alignParentTop      如果为true,将该控件的顶部与其父控件的顶部对齐;
android:layout_alignParentBottom 如果为true,将该控件的底部与其父控件的底部对齐;
android:layout_alignParentLeft      如果为true,将该控件的左部与其父控件的左部对齐;
android:layout_alignParentRight    如果为true,将该控件的右部与其父控件的右部对齐;
// 居中
android:layout_centerHorizontal 如果为true,将该控件的置于水平居中;
android:layout_centerVertical     如果为true,将该控件的置于垂直居中;
android:layout_centerInParent   如果为true,将该控件的置于父控件的中央;
// 指定移动像素
android:layout_marginTop      上偏移的值;
android:layout_marginBottom 下偏移的值;
android:layout_marginLeft 　　左偏移的值;
android:layout_marginRight 　 右偏移的值;
```
一直比较常用的就是这两个布局，我最畏惧的也是也布局，所以这几天我好好整理了一下怎么熟练使用这两个布局。
比如下面这个是最常用的布局。或许高手一下子就写出来了。那怎么写呢？
看到这个布局，你首先想到这是一个ListView或者RecyclerView，然后是一个item的布局，就是我们看到一列一列的布局，tiem理有一张图片和文字。
![item.jpg](http://upload-images.jianshu.io/upload_images/1990324-7c7b024b6501a0ec.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.v7.widget.CardView xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginBottom="@dimen/card_margin"
    android:layout_marginLeft="@dimen/card_margin"
    android:layout_marginRight="@dimen/card_margin"
    android:clickable="true">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:gravity="center_vertical"
        android:orientation="horizontal"
        android:padding="12dp">
        <ImageView
            android:id="@+id/ivNews"
            android:layout_width="86dp"
            android:layout_height="60dp"
            android:scaleType="centerCrop"/>

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:layout_marginLeft="8dp"
            android:orientation="vertical">

            <TextView
                android:id="@+id/tvTitle"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                 android:textAppearance="@style/TextAppearance.AppCompat.Title"
                android:textColor="@color/primary_text"
                android:textSize="@dimen/sn_16sp"
                android:singleLine="true"/>
            <TextView
                android:id="@+id/tvDesc"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginTop="6dp"
                android:textColor="@color/secondary_text"
                android:maxLines="2"
                android:textSize="@dimen/sn_12sp"/>
        </LinearLayout>
    </LinearLayout>
</android.support.v7.widget.CardView>
```
这代码放上去看上去很简单，不知道大家看到有没有

##LinearLayout里面有一个属性android:layout_weight比较重要
先上代码：
```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="horizontal" >

    <TextView
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:gravity="center"
        android:layout_weight="1"
        android:text="简述" />

    <TextView
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:gravity="center"
        android:text="简述"
        android:layout_weight="1"/>

    <TextView
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:gravity="center"
        android:layout_weight="1"
        android:text="简述" />
</LinearLayout>
```

![weight.jpg](http://upload-images.jianshu.io/upload_images/1990324-4bcb145c44fef3df.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
三个权重的比值是1：1：1，而layout_width的为0dp;那么宽度就按1：1：1比例划分。
##Android中SP与DP的区别
- 长度宽度的数值要使用dp作为单位放入dimens.xml文件中
- 字体大小的数值要使用sp作为单位,也放入dimens.xml文件中

```xml
<TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textSize="18sp"
        android:text="Hello World! in SP" />

<TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textSize="18dp"
        android:text="Hello World! in DP"
        />
```

![sp.jpg](http://upload-images.jianshu.io/upload_images/1990324-ff803c8bbbff5c82.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![Paste_Image.png](http://upload-images.jianshu.io/upload_images/1990324-70a1333d9e51f0d0.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
  改变字体后显示效果上述
- sp除了受屏幕密度影响外,还受到用户的字体大小影响
- 通常情况下,建议使用sp来跟随用户字体大小设置

##Android中使用ViewStub
 <ViewStub />标签最大的优点是当你需要时才会加载，使用他并不会影响UI初始化时的性能。各种不常用的布局想进度条、显示错误消息等可以使用<ViewStub />标签，以减少内存使用量，加快渲染速度。<ViewStub />是一个不可见的，大小为0的View。
ViewStub是什么
- ViewStub是View的子类
- 它不可见,大小为0
- 用来延迟加载布局资源
```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    android:paddingBottom="@dimen/activity_vertical_margin"
    >

    <Button
        android:id="@+id/clickMe"
        android:text="Hello World!"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>

    <ViewStub
        android:id="@+id/myViewStub"
        android:inflatedId="@+id/myInflatedViewId"
        android:layout="@layout/activity_test"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@id/clickMe"
        />
</RelativeLayout>
activity_test.xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
  >

    <TextView
        android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/hello_world" />

    <EditText
        android:id="@+id/editText"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_below="@+id/textView"
        android:hint="Enter your name here" />

    <Button
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_below="@+id/editText"
        android:onClick="sayHello"
        android:text="Say hello!" />
</RelativeLayout>

 ViewStub myViewStub = (ViewStub)findViewById(R.id.myViewStub);
                if (myViewStub != null) {
                    myViewStub.inflate();
                    //或者是下面的形式加载
                    //myViewStub.setVisibility(View.VISIBLE);
                }
```
关于ViewStub的事
- 除了inflate方法外,我们还可以调用setVisibility()方法加载布局文件
- 一旦加载布局完成后,ViewStub会从当前布局层级中删除
- android:id指定ViewStub ID,用于查找ViewStub进行延迟加载
- android:layout延迟加载布局的资源id
- android:inflatedId加载的布局被重写的id,这里为RelativeLayout的id
- ViewStub不支持<merge>标签.但是可以<include>
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
              android:orientation="vertical"
              android:layout_width="match_parent"
              android:layout_height="match_parent">
        <include layout="@layout/merge_layout"/>
</LinearLayout>
merge_layout.xml
<merge xmlns:android="http://schemas.android.com/apk/res/android">
    <Button
            android:layout_width="fill_parent"
            android:layout_height="wrap_content"
            android:text="Yes"/>

    <Button
            android:layout_width="fill_parent"
            android:layout_height="wrap_content"
            android:text="No"/>
</merge>
这样是可以的
```
Android中的减少视图层级<merge />
 <merge/>标签在UI的结构优化中起着非常重要的作用，它可以删减多余的层级，优化UI。<merge/>多用于替换FrameLayout或者当一个布局包含另一个时，<merge/>标签消除视图层次结构中多余的视图组。例如你的主布局文件是垂直布局，引入了一个垂直布局的include，这是如果include布局使用的LinearLayout就没意义了，使用的话反而减慢你的UI表现。这时可以使用<merge/>标签优化。

