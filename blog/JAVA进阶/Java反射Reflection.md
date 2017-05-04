Java反射是可以让我们在运行时**获取类的函数、属性、父类、接口等Class内部信息的机制。**通过反射还可以让我们在运行期**实例化对象，调用方法，通过调用get/set方法获取变量的值，即使方法或属性是私有的也可以通过反射的形式调用。**
###反射Class以及构造对象
如果你在编译期知道一个类的名字，那么你可以使用如下的方式获取一个类的Class对象
```
Class<?> myClass=Student.class;
```
如果你在编译期得到了某个对象，但是你想获取这个对象的Class对象，那么你可以通过如下的形式来获取Class 对象
```
Student me=new Student("hansheng");
Class<?> class=me.getClass();
```
如果你在编译期获取不到目标类型，但是你知道他的完整类路径，那么你可以通过如下的形式来获取Class对象
```
Class<?> myClass=Class.forName("com.hansheng");
```
接口说明
```
//加载完整路径
public static Class<?> forName(String className)

```
通过Class对象构造目标类型的对象
在java中药构造对象，必须通过该类的构造函数，那么其实反射也是如此。通过反射构造对象，我们首先获取类的Constructor(构造器)对象，然后通过Constructor来创建目标类的对象。
```
//获取class对象
Class<?> clazz=Class.forName("com.hansheng");
//通过class对象获取Constructor,Student的构造函数有一个字符串函数，这里需要传递参数的类型
Constructor<?> constructor =clazz.getConstructor(String.class);
//通过Constructor来创建Student对象
Object obj=constructor.newInstance("hansheng");
System.out.println("obj:"+obj.toStirng());

```
获取构造函数的接口
```
public constructor<T> getConstructor(Class...<?>paramterTypes)
```
