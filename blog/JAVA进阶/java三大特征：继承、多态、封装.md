### 封装
java中类的属性的访问权限默认值不是private,要想隐藏该属性的方法，就可以加private修饰符，来限制只能够在类的内部进行访问。
对于**类中的私有属性**，要对其给出一对方法（getXXX，setXXX()）访问私有属性，保证对私有属性的操作和安全性。
**方法的封装**，该公开的公开，该隐藏的隐藏。
### java的继承
继承，是对有着共同特性的多类事物，进行再抽象成一个类。
java中的继承要使用extends关键字，并且java中指允许单继承，也就是一个类只能有一个父类。
**构造方法不能被继承**。
java方法中的覆盖
子类中有和父类中可访问的同名同返回同参数列表的方法时，就会覆盖从父类继承来的方法。 
**super()关键字**
super(),表示在子类的构造方法调用父类的构造方法时，super()也只能在构造方法中的第一句。
### java中的多态
有两种多态的机制：编译时多态、运行时多态
1、方法的重载：重载是指同一类中有多个同名的方法，但这些方法有着不同的参数。，因此在编译时就可以确定到底调用哪个方法，它是一种编译时多态。
2、方法的覆盖：子类可以覆盖父类的方法，因此同样的方法会在父类中与子类中有着不同的表现形式。在**java语言中，基类的引用变量不仅可以指向基类的实例对象，也可以指向子类的实例对象，同样，接口中的引用变量也可以指向其实现类的实例对象**。
```java
public class A {
    public String show(D obj) {
        return ("A and D");
    }

    public String show(A obj) {
        return ("A and A");
    } 
}
public class B extends A{
    public String show(B obj){
        return ("B and B");
    }
    
    public String show(A obj){
        return ("B and A");
    } 
}
public class C extends B{

}
public class D extends B{

}
public class Test {
    public static void main(String[] args) {
        A a1 = new A();
        A a2 = new B();
        B b = new B();
        C c = new C();
        D d = new D();     
        System.out.println("1--" + a1.show(b));
        System.out.println("2--" + a1.show(c));
        System.out.println("3--" + a1.show(d));
        System.out.println("4--" + a2.show(b));
        System.out.println("5--" + a2.show(c));
        System.out.println("6--" + a2.show(d));
        System.out.println("7--" + b.show(b));
        System.out.println("8--" + b.show(c));
        System.out.println("9--" + b.show(d));      
    }
}
1--A and A
2--A and A
3--A and D
4--B and A
5--B and A
6--A and D
7--B and B
8--B and B
9--A and D

```
**当超类对象引用变量引用子类对象时，被引用对象的类型而不是引用变量的类型决定了调用谁的成员方法，但是这个被调用的方法必须是在超类中定义过的，也就是说被子类覆盖的方法。**
这我们用一个例子来说明这句话所代表的含义：a2.show(b)；
      这里**a2是引用变量，为A类型，它引用的是B对象**，因此按照上面那句话的意思是说有B来决定调用谁的方法,所以a2.show(b)应该要调用B中的show(B obj)，产生的结果应该是“B and B”，但是为什么会与前面的运行结果产生差异呢？这里我们忽略了后面那句话“但是这儿被**调用的方法必须是在超类中定义过的**”，那么show(B obj)在A类中存在吗？根本就不存在！所以这句话在这里不适用？那么难道是这句话错误了？非也！其实这句话还隐含这这句话：它仍然要按照继承链中调用方法的优先级来确认。所以它才会在A类中找到show(A obj)，同时由于B重写了该方法所以才会调用B类中的方法，否则就会调用A类中的方法。

