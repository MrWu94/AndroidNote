## 1、教程Markdown
[在线网址](http://mdp.tylingsoft.com/), 动态权限授权示例.

## 2、在线视频剪切网站
非常不错的在线网站, 处理音频和视频. [参考](http://online-video-cutter.com/).

## 3、 Android测试工具
推荐两个常用的测试工具[Espresso](https://google.github.io/android-testing-support-library/docs/espresso/)和[Robolectric](http://robolectric.org/).

## 4、TextView的标准字体
样式
```xml
style="@style/TextAppearance.AppCompat.Display4"
style="@style/TextAppearance.AppCompat.Display3"
style="@style/TextAppearance.AppCompat.Display2"
style="@style/TextAppearance.AppCompat.Display1"
style="@style/TextAppearance.AppCompat.Headline"
style="@style/TextAppearance.AppCompat.Title"
style="@style/TextAppearance.AppCompat.Subhead"
style="@style/TextAppearance.AppCompat.Body2"
style="@style/TextAppearance.AppCompat.Body1"
style="@style/TextAppearance.AppCompat.Caption"
style="@style/TextAppearance.AppCompat.Button"
```

![Paste_Image.png](http://upload-images.jianshu.io/upload_images/1990324-f542adaf4c372fa3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 5、透明statusbar和全屏ImageView
status bar设置成为透明颜色.
```xml
<style name="AppTheme.NoStatusBar"> 
<item name="android:windowTranslucentStatus">true</item> </style>
```
页面的根布局是CollapsingToolbarLayout
```xml
style="@style/TextAppearance.AppCompat.Button"<android.support.design.widget.CollapsingToolbarLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true">
    <ImageView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:contentDescription="@null"
        android:fitsSystemWindows="true"
        android:scaleType="centerCrop"
        android:src="@drawable/christmas"/>
</android.support.design.widget.CollapsingToolbarLayout>
```
## GitHub标签
[网址](http://shields.io/), 比如:
![](https://img.shields.io/badge/platform-Android-green.svg)
![](https://img.shields.io/badge/architecture-Clean%20Architecture-green.svg)

## 6. dp和sp的区别
dp是Android页面常用的度量单位, sp主要用于字体度量.
在标准情况下, dp等于sp. 然而, Android系统允许用户设置字体大小, sp会随着字体的大小而改变, 放大或是缩小.
设置位置(红米): Android -> 设置 -> 字体大小 -> 标准(默认)或大小号.

## 7、显示Activity栈的Shell命令
```
adb shell dumpsys activity | sed -n -e '/Stack #/p' -e '/Running activities/,/Run #0/p'
```
## 8、修改string的颜色
在 Android 开发中，通常会吧文本放在 strings.xml 文件中，然后再引用。

有时候，有些需求需要修改文本中的部分字的字体颜色，可以用以下方式修改：

修改原本的strings.xml:
```
<string name="hh_no_order"><Data><![CDATA[sorry，没有任何订单，<font color="#fc2a56">前往买买买</font>]]></Data></string>
```
代码中的使用需要配合Html.fromHtml()，如：
```
mTvTip.setText(Html.fromHtml(mTips))
```

### Android log colors
- To do so
- In toolbar menu select File|Settings
- Choose Editor|Colors & Fonts|Android Logcat
- Click on Save As… button and create new color schema
- Change all colors to ‘Holo theme colors’ (Uncheck ‘Use inherited attributes’ for every color)

```xml
Assert:  #AA66CC
Debug:   #33B5E5
Error:   #FF4444
Info:    #99CC00
Verbose: #FFFFFF
Warning: #FFBB33
```
### 移除默认的 Window 背景

一般应用默认继承的主题都会有一个默认的 windowBackground ，比如默认的 Light 主题：
在 BaseActivity 的 onCreate() 方法中使用下面的代码移除：
```java
getWindow().setBackgroundDrawable(null);
// 或者
getWindow().setBackgroundDrawableResource(android.R.color.transparent);
```

### ImageView的background和imageDrawable重叠

Android中，所有的view均可以设置background。ImageView除了能够设置background之外，还能设置ImageDrawable。

在开发中，很多时候需要显示图片，在图片加载出来之前通常是需要显示一张默认图片的，很多时候会使用ImageView的background属性来设置默认背景图，而imageDrawable来设置需要加载的图片。这样会导致一个问题，当图片加载到页面后，默认背景图被挡住了，但是却任然需要绘制，导致过渡绘制情况的发生。

### copyright 年份
```
<string name="copyright">Copyright © 2016-%d mrwu</string>

 int year = Calendar.getInstance().get(Calendar.YEAR);
        tvCopyright.setText(getString(R.string.copyright, year));

```

## Live template
 ```
android.content.SharedPreferences sharedPreferences =getPreferences(Context.MODE_PRIVATE);
android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
editor.putBoolean(getString(R.string.$stringVal$), $spVal$);
editor.apply();

Snackbar.make($view$,“$text$”,Snackbar.LENGTH_LONG)
 .setAction(“Action”, null).show();
```

原文:
https://medium.com/google-developer-experts/configuring-android-studio-4aa4f54f1153
http://blog.csdn.net/a740169405
