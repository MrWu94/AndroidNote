## CheckStyle检验的主要内容：
Javadoc注释
命名约定
标题
Import语句
体积大小
空白
修饰符
块
代码问题
类设计
混合检查（包活一些有用的比如非必须的System.out和printstackTrace）

在Android开发中，也需要我们去定义，Android Studio继承了IDEA的可拓展特性，它也拥有CheckStyle的插件，在Android项目中，使用的Gradle配置。
1、添加Plugin
```
apply plugin: 'checkstyle'
```
2、设置CheckStyle版本
```
checkstyle {    toolVersion = "6.14"}
```
3、设置配置文件
```
task checkstyle(type: Checkstyle) {
    configFile file("${project.rootDir}/config/checkstyle/checkstyle.xml")
    source 'src/main/java'
    include '**/*.java'
    exclude '**/gen/**'
    classpath = files()
}
```

4、运行
```
linux
./gradlew checkstyle
window 
gradlew.bat checkstyle
```
5、运行还有一个插件方式
安装Idea的check Style插件。
要使用 checkstyle 需要安装一个 AndroidStudio 插件 CheckStyle-IDEA,你可以通过在线安装插件的方式去安装，也可以通过本地安装，[插件地址](https://github.com/jshiell/checkstyle-idea)
配置 checkstyle 文件
当安装好插件，打开 AndroidStudio 的设置页面 settings -> Other Settings 你会发现多了一个 Checkstyle , 点击打开,如下图。

![Paste_Image.png](http://upload-images.jianshu.io/upload_images/1990324-b6bfdc270b277eb2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
然后再java文件中右键

![Paste_Image.png](http://upload-images.jianshu.io/upload_images/1990324-9ef95928ac87efa8.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
AndroidStudio 的控制面板会多一个 CheckStyle，你可以在这里方便的进行代码检查。

![Paste_Image.png](http://upload-images.jianshu.io/upload_images/1990324-0c11928f778e14d4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)






