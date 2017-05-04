```java
class OuterClass { 
      static class StaticNestedClass {} //静态嵌套类 
      class InnerClass {} //内部类
}
```
使用嵌套类的好处在于:
+ 当某个类为旁类专用时，将其写成嵌套类能使得代码结构更紧凑。
+ 嵌套类增加了封装性
内部类和静态嵌套类的不同根源来自于static关键字；
**内部类和静态嵌套类的最大区别在于是否能够访问外部类成员**。
**静态嵌套类**
static修饰符使得嵌套类**成为外部类的静态成员**，与**外部类相关联**，而**不是与外部类对象相关联**。
这样静态嵌套类作为一个静态成员，**仅能引用外部类静态成员**，外部类中的非静态成员与外部类对象相关，静态嵌套类就不能访问他们，这使得静态嵌套类的功能变的很弱，可用之处很少。
另外因为静态嵌套类是**依附于外部类而非外部类对象**的，所以**不同的外部类对象共享一个静态嵌套类**，这一点与内部类不同，可以用来包装main方法。
静态嵌套类的声明示例:
```java
OuterClass.StaticNestedClass nestedObject = new OuterClass.StaticNestedClass();
```
**内部类**
没有static修饰意味着一个内部类是和**外部类对象**关联的，也可以说**一个内部类对象存在于外部类对象**中，是外部类**对象**的一个成员，因此内部类对象可以访问外部类对象的全部成员，包括私有成员。
正是**因为内部类存在于外部类对象中，所以它不能定义任何静态成员**。
内部类对象可以访问外部类的所有成员变量，包括私有成员，这是Java闭包的原理;
因为内部类引用外部类，其外部类就不能被JVM的垃圾回收机制自动垃圾回收。
不同的外部类对象之间没**有公共的内部类对象成员**。
内部类的声明示例:
```java
OuterClass.InnerClass innerObject = outerObject.new InnerClass();
```
**变量遮蔽(Shadowing)**
外部类，嵌套类以及局部方法区的变量作用域有重叠，如果出现**同名变量**将发生**变量遮蔽**。
```java
public class ShadowTest {

    public int x = 0;

    class FirstLevel {

        public int x = 1;

        void methodInFirstLevel(int x) {
            System.out.println("x = " + x);
            System.out.println("this.x = " + this.x);
            System.out.println("ShadowTest.this.x = " + ShadowTest.this.x);
        }
    }

    public static void main(String... args) {
        ShadowTest st = new ShadowTest();
        ShadowTest.FirstLevel fl = st.new FirstLevel();
        fl.methodInFirstLevel(23);
    }
}
x = 23 //1. 局部变量
this.x = 1 //2.内部类变量
ShadowTest.this.x = 0 //3.外部类变量
```
这里

+ 内部类中的this指针指向内部类对象自己
+ ShadowTest.this指针指向外部类对象
+ 不加修饰的变量，将执行就近匹配原则；如果名称相同将发生变量遮蔽效应；


静态方法只能访问静态成员，实例方法可以访问静态和实例成员。之所以不允许静态方法访问实例成员变量，是因为实例成员变量是属于某个对象的，而静态方法在执行时，并不一定存在对象。同样，因为实例方法可以访问实例成员变量，如果允许静态方法调用实例方法，将间接地允许它使用实例成员变量，所以它也不能调用实例方法。基于同样的道理，静态方法中也不能使用关键字this。
