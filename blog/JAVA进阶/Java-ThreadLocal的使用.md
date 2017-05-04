ThreadLocal,直译为“线程本地”或“本地线程”，如果你真的这么认为，那就错了！其实，它就是一个容器，用于存放线程的局部变量，我认为应该叫做 ThreadLocalVariable（线程局部变量）才对

Java中的ThreadLocal类允许我们创建只能被同一个线程读写的变量。因此，如果一段代码含有一个ThreadLocal变量的引用，即使两个线程同时执行这段代码，他们也无法访问到对方的ThreadLocal的值。

#如何创建ThreadLocal变量
以下下代码展示如何创建一个ThreadLocal变量
```
private ThreadLocal myThreadLocal=new ThreadLocal();
```
我们可以看到，通过这段代码实例化一个ThreadLocal对象。我们只需要实例化对象一次，并且也不需要知道它是被哪个线程实例化。虽然所有的线程都能访问到这个ThreadLocal实例，但是每个线程却只能访问到自己通过调用ThreadLocal的set()方法设置的值。即使是两个不同的线程在同一个ThreadLocal对象设置了不同的值，他们依然无法访问到对方的值。

#如何访问到ThreadLocal变量
一旦创建了一个ThreadLocal的变量，你可以通过如下代码设置某个需要保存的值：
```
myThreadLocal.set("a Thread local value");
```
可以通过下面方法读取保存在ThreadLocal变量中的值
```
String threadLocalValue=(String)myThreadLocal.get();
```
#为ThreadLocal指定泛型类型
我们可以创建一个指定泛型类型的ThreadLocal对象，这样我们就不需要每次使用get()方法返回的值做强制类型转换。
```
private ThreadLocal muThreadLocal=new ThreadLocal<String>();
```
#如何初始化ThreadLocal变量的值
由于在ThreadLocal对象中设置的值只能被设置这个值得线程访问到，线程无法在ThreadLocal对象使用set()方法保存一个初始值，并且这个初始值能被所有的线程访问到

我们可以通过创建一个ThreadLocal的子类并且重写initialValue()方法，来为一个ThreadLocal对象指定一个初始值
```
private ThreadLocal myThreadLocal=new ThreadLocal<String>(){
        @Override
        protected String initialValue(){
            return "this is the initial value"
      }
};
```
#一个完整的ThreadLocal的例子
```
public class ThreadLocalExample{
      public static class MyRunnable implements Runnable{
              private ThreadLocal threadlocal=new ThreadLocal();
              @Override
              public void run(){
                    threadlocal.set((int)(Math.random()*1000));
                    tru{
                          Thread.sleep(2000);
                    }
                      catch(InterrupedException e){
                            
                    }
                    System.out.println(threadLocal.get());
              }
      }
    public static void main(String[] args){
          MyRunnable instance=new MyRunnable();
          Thread thread1=new Thread(instance);
          Thread thread2=new Thread(instance);
          
          thread1.start();
          thread2.start();
      }
}
```
上面的例子创建了一个MyRunnable实例，并将该实例作为参数传递给两个线程。两个线程分别执行run()方法，并且都在ThreadLocal实例上保存了不同的值。如果它们访问的不是ThreadLocal对象并且调用的set()方法被同步了，则第二个线程会覆盖掉第一个线程设置的值。但是，由于它们访问的是一个ThreadLocal对象，因此这两个线程都无法看到对方保存的值。也就是说，它们存取的是两个不同的值。

在做一个实例
先定义一个接口：
```
public interface Sequence {
	int getNumber();
}
```
在做一个线程类
```
public class ClientThread extends Thread {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0;i<3;i++){
			System.out.println(Thread.currentThread().getName()+"=>"+sequence.getNumber());
		}
	}
	/**
	 * @param args
	 */
	private Sequence sequence;
	public ClientThread(Sequence sequence){	
		this.sequence=sequence;
	}
	
}
```
在线程中连续输出三次线程名与其对应的序列号。
我们先不用 ThreadLocal，来做一个实现类吧。
```
public class SequenceA implements Sequence{
	
	private static int number=0;
	public int getNumber(){
		number=number+1;
		return number;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sequence sequence=new SequenceA();
		ClientThread thread1=new ClientThread(sequence);
		ClientThread thread2=new ClientThread(sequence);
		ClientThread thread3=new ClientThread(sequence);
		
		thread1.start();
		thread2.start();
		thread3.start();
		

	}

}
运行值
Thread-0=>1
Thread-0=>2
Thread-1=>3
Thread-1=>4
Thread-1=>5
Thread-0=>7
Thread-2=>6
Thread-2=>8
Thread-2=>9

```
由于线程启动顺序是随机的，所以并不是0、1、2这样的顺序，这个好理解。为什么当 Thread-0 输出了1、2、3之后，而 Thread-2 却输出了4、5、6呢？**线程之间竟然共享了 static 变量！**这就是所谓的“非线程安全”问题了。

那么如何来保证“线程安全”呢？对应于这个案例，就是说不同的线程可拥有自己的 static 变量，如何实现呢？下面看看另外一个实现吧。

```
public class SequenceB implements Sequence {
	
	private static ThreadLocal<Integer> numberContainer=new ThreadLocal<Integer>(){
		@Override
		protected Integer initialValue(){
			return 0;
		}
	};
	
	public int getNumber(){
		numberContainer.set(numberContainer.get()+1);
		return numberContainer.get();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sequence sequence=new SequenceB();
		
		ClientThread thread1=new ClientThread(sequence);
		ClientThread thread2=new ClientThread(sequence);
		ClientThread thread3=new ClientThread(sequence);
		
		thread1.start();
		thread2.start();
		thread3.start();

	}

}
运行值
Thread-0=>1
Thread-1=>1
Thread-0=>2
Thread-2=>1
Thread-0=>3
Thread-1=>2
Thread-1=>3
Thread-2=>2
Thread-2=>3
每个线程相互独立了，同样是 static 变量，对于不同的线程而言，它没有被共享，而是每个线程各一份，这样也就保证了线程安全。 也就是说，TheadLocal 为每一个线程提供了一个独立的副本！
```
通过 ThreadLocal 封装了一个 Integer 类型的 numberContainer 静态成员变量，并且初始值是0。再看 getNumber() 方法，首先从 numberContainer 中 get 出当前的值，加1，随后 set 到 numberContainer 中，最后将 numberContainer 中 get 出当前的值并返回。

搞清楚 ThreadLocal 的原理之后，有必要总结一下 ThreadLocal 的 API，其实很简单。

1、public void set(T value)：将值放入线程局部变量中
2、public T get()：从线程局部变量中获取值
3、public void remove()：从线程局部变量中移除值（有助于 JVM 垃圾回收）
4、protected T initialValue()：返回线程局部变量中的初始值（默认为 null）
