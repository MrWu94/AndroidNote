将变量、方法和类声明为final代表了什么？使用final的好处是什么？最后也有一些使用final关键字的实。final经常和static一起使用来声明常量。

1、final方法在编译阶段绑定，称为静态绑定(static binding),编译时常量，永远不可改变。
2、运行期初始化，我们希望它不会改变。
3、接口中声明的所有变量本身是final的。
4、对于集合对象声明为final指的是引用不能被更改，但是你可以向其中增加，删除或者改变内容。譬如：
```java
private final List Loans = new ArrayList();
list.add(“home loan”);  //valid
list.add("personal loan"); //valid
loans = new Vector();  //not valid
```

 有些变量，我们希望它可以根据对象的不同而表现不同，但同时又不希望它被改变，这个时候我们就可以使用运行期常量。对于运行期常量，它既可是基本数据类型，也可是引用数据类型。**基本数据类型不可变的是其内容，而引用数据类型不可变的是其引用，引用所指定的对象内容是可变的。**
```java
public class FinalText {

    private final String final01="hansheng"; //编译期常量，必须要进行初始化，且不可更改
    private final String final02;//构造器常量，在实例化一个对象时被初始化

    private static Random random=new Random();
    private final int final03=random.nextInt(50);//使用随机数来进行初始化
    //基本数据类型不可变的是其内容，而引用数据类型不可变的是其引用，引用所指定的对象内容是可变的。
    public final Person final04=new Person("hansheng");//final指向引用数据类型


    public FinalText(String final02) {
        this.final02 = final02;
    }

    @Override
    public String toString() {
        return "final01="+final01+"   final02="+final02+"   final03="+final03+"    final04="+final04.getName();
    }

    public static void main(String[] args){

        System.out.println("---------第一次创建对象------");
        FinalText final1=new FinalText("cm");
        System.out.println(final1);
        System.out.println("---------第二次创建对象------");
        FinalText final2=new FinalText("zj");
        System.out.println(final1);
        System.out.println("---------修改引用对象------");
        final2.final04.setName("hanming");
        System.out.println(final2);
    }
}
public class Person {

    private String name;
    Person(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```


# final关键字的含义？
final在Java是一个保留的关键字，可以声明成员变量，方法，类以及本地变量。一旦你将引用声明做final，你将不能官弁这个引用了，编译器会检查代码，如果你将试图将变量再次初始化的话，编译器会报编译错误。
什么事final变量？
凡是对成员变量或者本地变量（在方法中的常量或者代码块中的变量称为本地变量）声明为final的都叫做final变量（编译时常量）。final变量经常和static关键字一起使用，作为变量
```java
public static final String LOAN="loan";
LOAN=new String("loan");//invalid compilation error
```
# 什么是final方法？
final也可以声明方法。方法前面加上final关键字，代表这个方法不可以被子类的方法重写。如果你认为一个方法的功能已经足够完整了，子类中不需要改变的话，你可以声明此方法为final。final方法比非final方法要快，因为在编译的时候已经静态绑定了，不需要在运行时再动态绑定。
```java
class Person{
  public final String getName(){
        return "personal loan"
    }
}

class loan extends Person{
      @Override
        public final Stirng getName(){
                return "cheap personal loan";//compilation error: overridden method is final
        }
}
```
# 什么是final类？
使用final来修饰的类叫作final类。final类通常功能是完整的，它们不能被继承。Java中有许多类是final的，譬如String, Interger以及其他包装类。
```java
final class PersonalLoan{
 
   }
   class CheapPersonalLoan extends PersonalLoan{  //compilation error: cannot inherit from final class
```


# 不可变类

创建不可变类要使用final关键字。不可变类是指它的对象一旦被创建了就不能被更改了。String是不可变类的代表。不可变类有很多好处，譬如它们的对象是只读的，可以在多线程环境下安全的共享，不用额外的同步开销等等。


同final修饰参数在内部类中是非常有用的，**在匿名内部类中，为了保持参数的一致性，若所在的方法的形参需要被内部类里面使用时，该形参必须为final。**

直到这里还没有解释为什么是final？在内部类中的属性和外部方法的参数两者从外表上看是同一个东西，但实际上却不是，所以他们两者是可以任意变化的，也就是说在**内部类中我对属性的改变并不会影响到外部的形参**，而然这从程序员的角度来看这是不可行的，毕竟站在程序的角度来看这两个根本就是同一个，如果内部类该变了，而外部方法的形参却没有改变这是难以理解和不可接受的，所以为了保持参数的一致性，就规定使用final来避免形参的不改变。
**简单理解就是，拷贝引用，为了避免引用值发生改变，例如被外部类的方法修改等，而导致内部类得到的值不一致，于是用final来让该引用不可改变。**
**      故如果定义了一个匿名内部类，并且希望它使用一个其外部定义的参数，那么编译器会要求该参数引用是final的。**

