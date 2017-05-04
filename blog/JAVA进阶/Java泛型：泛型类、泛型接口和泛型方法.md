###泛型类
容器类应该算得上最具重用性的类库之一。
```
public class Container{
      private String key;
      private String value;
      public Container(String k,String v){
                key=k;
                value=v;
      }
      public String getKey(){
             return key;
      }
      public Stirng setKey(){
             this.key=key;
      }
      public String getValue(){
                return value;
      }
      public String setValue(){
                this.value=value;
      }
}

```
Container类保存了一对key-value键值对，但类型是死定的，也就是说如果我要创建一个键值对String-integer类型的，当前这个Container是做不到的，必须在定义。
```
public class Container<k,V>{
        private k key;
        private V value;
        public Container(K k,V v){
                key=k;
                value=v;
        }
        public K getkey(){
                return key;
        }
        public V getValue(){
                  return value
        }
        public void setKey(){
                this.key=key;
        }
        public void setValue(){
              this.value=value;
        }
}
```
在编译器，是无法知道K和V具体是什么类型，只有在运行时才会真正根据类型来构造和分配内存。可以看一下现在Container类对于不同类型的支持情况。
```
public class Main{
        public static void main(String[] args){
                  Container<String,String> c1=new Container<String ,String>("name","hansheng");
                  Container<String,Integer> c2=new Container<String,Integer>("age",22);
                  Container<Double,Double> c3=new Container<Double,Double>(1.1,1.3);
                  System.out.println(c1.getKey() + " : " + c1.getValue());      
                  System.out.println(c2.getKey() + " : " + c2.getValue());                                                               
                  System.out.println(c3.getKey() + " : " + c3.getValue());
        }
}
```
###泛型接口
在泛型接口中，生成器是一个很好的理解
```
public interface Generator<T>{
          public T next();
}
//然后定义这个生成器类来实现这个接口
public class FruitGenerator implements Generator<String>{
          private String[] fruits=new String[]{"Apple","Banana","Pear"};
          @Override
          public String next(){
                    Ramdom rand=new Random();
                    return fruits[rand.nextInt(3)];
          }
}
```
###泛型方法
一个基本原则是：**无论何时，只要你能做到，你就应该尽量使用泛型方法。**也就是说，如果使用泛型方法可以取代将整个类型化，那么应该有限使用泛型方法。
```
public class Main{
      public static <T> void out(T,t){
                System.out.println(t);
      }
      public static void main(String[] args){
              out("hansheng");
              out(123);
      }
}
```
可以看到方法的参数彻底泛型化了，这个过程设计到编译器的类型推到和自动打包，也就是原来需要我们自己对类型进行的判断和处理，现在编译器帮我们做了。这样在定义方法的时候不必考虑以后到底需要处理哪些类型的参数，大大增加了编程的灵活性。
再看一个泛型方法和可变参数的例子：
```
public class Main {

    public static <T> void out(T... args) {
        for (T t : args) {
            System.out.println(t);
        }
    }

    public static void main(String[] args) {
        out("finding", 123, 11.11, true);
    }
}
```
###泛型接口实现的方式
```
interface Info<T>{
        public T getVar();
}
class InfoImpl<T> implements Info<T>T{
      private T var;
      public InfoImpl(T var){
                this.setVar(var);
      }
      public void setVar(R var){
                  this.var=var;
      }
      public T getVar(){
                  return this.var;
      }   
}
public class Test{
      public static void main(String args){
              Info<String> i=null;
              i=new InfoImpl<String>("hansheng");
              System.out.println("content"+i.getVar);
      }
}

```
###类型擦除
```
Class c1=new ArrayList<Integer>().getClass();
Class c2=new ArrayList<String>().getClass();
System.out.println(c1==c2);
```
显然在平时使用中，ArrayList<Integer>()和new ArrayList<String>()是完全不同的类型，但是在这里，程序却的的确确会输出true
###限制泛型可用类型
如果想限制使用泛型类别时，只能用某个特定类型或者是其子类型才能实例化该类型时，可以在定义类型时，使用extends关键字**指定这个类型必须是继承某个类，或者实现某个接口，也可以是这个类或接口本身。**
```
public class ListGenerator<T extends List>
{  
      private T[] fooArray;
      public T[] getfooArray(){
              return fooArray;  
      }
      public void setFooArray(T[] fooArray){
                this.fooArray=fooArray;
      }
      public static void main(String[] args){
              ListGenerator<LinkedList>  foo1=new ListGenerator<LinkedList>();
              ListGenerator<ArrayList>   foo2=new ListGenerator<ArrayList>();
              Link[] linkedLists=new LinkedList[10];
              foo1.setFooArray(linkedLists);
              ArrayList[] arrayLists=new ArrayList[10];
              foo2.setFooArray(arrayLists);
      }
}
```
类声明中：public class ListGenerator<T extends List>这样就规定了T必须是一个List继承系中的类，即实现了List接口的类。

当不使用泛型时，比如那些声明时带有<T>的集合类型，如果使用时没有指定类型，泛型类别为Object.

<T extends SomeClass>是一个限定通配符，?代表了一个未知的类型，并且是它SomeClass的子类，也可以SomeClass本身。
