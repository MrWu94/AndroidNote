## TextView显示下划线的两种方法
1、Android TextView中显示下划线的两种方法
1.在资源文件里的写法
```xml
<string name="key"><u>content</u></string>
```
2.在代码中的写法
```xml
TextView textView = (TextView)findViewById(R.id.testView);   
textView.setText(Html.fromHtml("<u>"+"content"+"</u>"));  
```
## 理解抽象，封装变化
```xml
//Activity 间使用 Intent 传递数据的两种写法 下面均是伪代码形式，请忽略一些细节

//写法一

//SrcActivity 传递数据给 DestActivity
Intent intent = new Intent(this,DestActivity.class);
intent.putExtra("param", "clock");
SrcActivity.startActivity(intent);

//DestActivity 获取 SrcActivity 传递过来的数据
String param = getIntent.getStringExtra("param");

//写法二

//SrcActivity 传递数据给 DestActivity
Intent intent = new Intent(this,DestActivity.class);
intent.putExtra(DestActivity.EXTRA_PARAM, "clock");
SrcActivity.startActivity(intent);

//DestActivity 获取 SrcActivity 传递过来的数据
public final static String EXTRA_PARAM = "param";
String param = getIntent.getStringExtra(EXTRA_PARAM);
```
写法一，存在的问题是，如果 SrcActivity 和 DestActivity 哪个把 "param" 打错成 "para" 或者 "paran" ，传递的数据都无法成功接收到。而写法二则不会出现此类问题，因为两个 Activity 之间传递数据只需要知道 EXTRA_PARAM 变量即可，至于 EXTRA_PARAM 变量到底是 "param" 、 "para" 、"paran" 这一点并不需要关心，这就是一种对可能发生变化的地方进行抽象封装的体现，它所带来的好处就是降低手抖出错的概率，同时方便我们进行修改。

## assert与raw的区别
res/raw和asserts的相同点：
1.两者目录下的文件在打包后会原封不动的保存在apk包中，不会被编译成二进制文件
res/raw和assets的不同点：
1.res/raw中的文件会被映射到R.[Java](http://lib.csdn.net/base/javaee)文件中，访问的时候直接使用资源ID，即
R.id.filename,.
assets文件夹下的文件不会被映射到到R.java文件中，访问的时候需要AssetManager类
2.res/raw不可以有目录，而assets则可以有目录结构，也就是assets目录下可以再建立文件夹

读取文件资源：
1.读取res/raw下的文件资源，通过以下方式获取输入流来进行读写操作

```java
  InputStream is =getResources().openRawResource(R.id.filename);
```  
2.读取assets下的文件资源，通过以下方式获取输入流来进行写操作·
     
```java
 AssetManager am = null;  
 am = getAssets();  
 InputStream is = am.open("filename");  
```
## Theme与Style的区别
**1.Theme(**是针对窗体级别的，可以改变窗体样式**)**
**A.应用到Application**
```
<application android:theme="@style/CustomTheme">
```

**B.应用到Activity**
```java
<activity android:theme="@android:style/Theme.Dialog">
```

**2.Style(是针对窗体元素级别的，改变指定控件或者Layout的样式)**
如果你要在java代码中加载主题的话，只要用setTheme（R.style.CustomTheme）就可以了，不过记得一定要在初始化任何view之前，比如一定要放在我们常用的setContentView()之前。通常，我们不建议这么做。
## ImageView中的src与background
**src** ：为ImageView 原图内容，存放原图大小，不会被拉伸；
**background**：为Imageview的背景，会根据ImageView给定的长宽进行拉伸；

在ImageView中，可以同时设置src和background属性（为了减少绘制，可以根据使用场景来设置相应属性）； 由于src中存放的是原图大小，如果需要对其缩放，就需要使用android:scaleTyle这个属性（scaleType 只对src属性有效），另外还可以对background设置透明度。

## 正确设置Imageview的透明度
**ImageView 设置透明度主要有以下三种方法：**
1、setAlpha(@FloatRange(from=0.0, to=1.0) float alpha) （**View提供**）
2、setAlpha(int alpha) （**已经标记为@Deprecated**）
3、setImageAlpha(int alpha) （**API>=16**）

合理使用src （前景）和background（背景）就可以实现

## Gone与INVISIBLE的区别
当控件visibility属性为INVISIBLE时，界面保留了view控件所占有的空间；而控件属性为GONE时，界面则不保留view控件所占有的空间。

## 异步、同步、并行、串行的区别
**异步：**发送方发出数据后，不用等接收方发回响应，接着发送下个数据包的通讯方式。【比如，主main函数的代码从上往下执行，new一个Thread并在子线程中途执行了sleep 10秒钟，而主main函数后面的代码不需要等子线程sleep完10秒再执行，而是直接继续执行下面的代码。】
**同步：**发送方发出数据后，需要等接收方发回响应以后才发下一个数据包的通讯方式。【比如，主main函数的代码从上往下执行，如果中途执行了sleep 10秒钟，则后面的代码都要等10秒后才会执行。】
**并行：**也称为并发。从宏观上来理解，就是在同一时间内同时执行多个线程任务。【比如，同时开启10张图片下载，宏观上他们是10张图同时下载的。】
**串行：**可以理解为，只有当一个线程执行完毕之后，才会执行下个线程。【比如，10张图片下载线程串行执行，只能是第一张下载完后，才会开始执行下一张图片下载。】

## Android Version与Android API Version


Android version | API version 
----------------|-------------
3.2   | API 13 
4.1   | API 16 
4.2   | API 17 
4.3   | API 18 
4.4   | API 19 
5.0   | API 21  
5.1   | API 22 
6.0   | API 23 
7.0   | API 24  
7.1.1 | API 25

## 在onCreate中获得View的宽度和高度
在onCreate里调用getWidth()和getHeight()会返回0，可以通过View的post方法获取宽度和高度。

## 发布时移除日志语句
在混淆文件中加入以下语句进混淆即可移除
```java
-assumenosideeffects class android.util.Log { 
     public static *** d(...);
}
```

