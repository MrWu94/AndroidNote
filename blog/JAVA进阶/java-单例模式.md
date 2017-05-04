第一种（懒汉，线程不安全）
```java
public class Singleton{
     private static Singleton instance;
     private Singleton(){}  //私有的构造方法，使其不能用new来实例化对象
     private static Singleton getInstance(){
            instance=new Singleton();
      }
       return instance;
    }
}
Singleton s=Singleton.getInstance();
```
第二种（懒汉，线程安全）
```java
public class Singleton{
      private Singleton(){}
      //同步代码块
      public static synchronized Singleton getInstance(){
              if(instance==null){
                  instance=new Singleton();
              }
            return instance;
      }
}
```
这种写法效率很低，99%情况下不需要同步

第三种（饿汉）
```java
public class Singleton{
      private static Singleton instance=new Singleton();
      private Singleton(){}
      public static Singleton getInstance(){
            return instance;
      }
}
```
instance在类装载是就实例化，浪费

第四种（饿汉，变种）
```java
public class Singleton{
      private Singleton instance=null;
      static{
            instance=new Singleton();
      }
    private Single(){}
    public static Singleton getInstance(){
          return this.instance;
    }
}
```
第五种（静态内部类）
```java
public class Sigleton{
      private static class SingletonHolder{
            private static final Singleton instance=new Singleton();
      }
      private Singleton(){}
      public static final Singleton getInstance(){
            return SingletonHolder.instance;
      }
}
```
第六种
```java
public class Singleton{
     private volatile static Singleton singleton;
     private Singleton(){}
     public static Singleton getInstance(){
          //第一次判断singleton对象是否为空
          if(singleton==null){
                //同步代码块  第二次判断singleton为空是因为防止两个进程都进入到这里，防止实例化两次
                  synchronized(Singleton.class){
                    if(singleton==null){
                          singleton=new Singleton();
                  }
            }
          }
      }  
      return singleton;
 }
```
