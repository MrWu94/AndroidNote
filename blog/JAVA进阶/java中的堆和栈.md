###独有还是共享

栈内存归属于单个线程，每个线程都会有一个栈内存，其存储的变量只能在其所属线程中可见，即栈内存可以理解成线程的私有内存。
而堆内存中的对象对所有线程可见。堆内存中的对象可以被所有线程访问。
+ 堆：存放所有new出来的对象；
+ 栈：存放几倍呢类型的变量数据和对象的引用，对象（new出来的对象）本身并不在栈中，而是存放在堆中或者常量中
+ 常量池：存放基本类型常量和字符串常量
**栈和常量池**中的对象可以共享，对于堆中的对象不可以共享。
```
String str1="abc";
String str2="abc";
String str3="abc";
String str4=new String("abc");
String str5=new String("abc");
```

![](http://upload-images.jianshu.io/upload_images/1990324-9dfd8612b0fcf206.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
通过new操作产生一个字符串，会先去常量池中查找有没有“abc”对象，如果没有，则在常量池中创建一个词字符串对象，然后堆中在创建一个常量池中次“abc“对象的拷贝，所以，对于String str=new String("abc")，如果常量池原来没有”abc“,则产生两个对象，否则产生一个对象。
```
int a1=1,a2=2,a3=3;
public static final int INT1=1;
public static final int INT2=2;
public static final int INT3=3;
```

![](http://upload-images.jianshu.io/upload_images/1990324-010b604de51ebad9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
