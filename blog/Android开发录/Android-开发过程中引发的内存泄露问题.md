##什么是内存泄漏

内存泄露，简单的说，就是该被释放的内存没有被释放，一直被某个或某些实例所引用但不能被使用，导致GC不能回收，造成内存泄漏。总结的说，可以理解为长生命周期的对象一直持有短生命周期对象的引用，导致短生命周期对象一直被引用而无法被GC回收，内存泄漏是造成OOM的主要原因之一，当一个应用中产生的内存泄漏比较多时，就难免会导致应用所需要的内存超过这个系统分配的内存限额，这就造成了内存溢出而导致应用Crash。

##安卓中常见的内存泄漏场景
1、单例造成内存泄漏、
2、非静态内部类创建其静态实例造成内存泄漏
3、匿名内部类/异步线程造成内存泄漏
4、Handler机制造成内存泄漏
5、资源对象未关闭导致内存泄漏
6、各种注册没取消。
对于使用了BraodcastReceiver，ContentObserver，File，Cursor，Stream，Bitmap等资源的使用，应该在Activity销毁时及时关闭或者注销 ，否则这些资源将不会被回收，造成内存泄漏(这个可以根据IDE的警告改进一部分)。

**GridView的滥用。**
GridView和ListView的实现方式不太一样。GridView的View不是即时创建的，而是全部保存在内存中的。比如一个GridView有100项，虽然我们只能看到10项，但是其实整个100项都是在内存中的。所以在应用程序退出之前，要讲gridView和adapter全部置为null。

**集合容器对象没清理造成的内存泄露。**
我们通常把一些对象的引用加入到了集合容器（比如ArrayList）中，当我们不需要该对象时，并没有把它的引用从集合中清理掉，这样这个集合就会越来越大。如果这个集合是static的话，那情况就更严重了。所以要在退出程序之前，将集合里的东西clear，然后置为null，再退出程序。

**Bitmap的回收和置空。**Bitmap 对象不在使用时调用 recycle()释放内存，有时我们会手工的操作 Bitmap 对象, 如果一个Bitmap 对象比较占内存, Bitmap对象在不使用时，我们应该先调用recycle()释放内存，然后才它设置为null。虽然recycle()从源码上看，调用它应该能立即释放Bitmap的主要内存，但是测试结果显示它并没能立即释放内存。但是我它应该还是能大大的加速Bitmap的主要内存的释放。

**构造Adapter时，没有使用缓存的convertView。**
初始时ListView会从BaseAdapter中根据当前的屏幕布局实例化一定数量的view对象，同时ListView会将这些view对象 缓存起来。当向上滚动ListView时，原先位于最上面的list item的view对象会被回收，然后被用来构造新出现的最下面的list item。这个构造过程就是由getView()方法完成的，getView()的第二个形参View convertView就是被缓存起来的list item的view对象(初始化时缓存中没有view对象则convertView是null)。
由此可以看出，如果我们不去使用convertView，而是每次都在getView()中重新实例化一个View对象的话，即浪费时间，也造 成内存垃圾，给垃圾回收增加压力，如果垃圾回收来不及的话，虚拟机将不得不给该应用进程分配更多的内存，造成不必要的内存开支。

```
public View getView(int position, View convertView, ViewGroup parent) {
　　ViewHolder holder;
if (convertView == null) {
　　    convertView = mInflater.inflate(R.layout.list_item, null);
　　    holder = new ViewHolder();
　　    holder.text = (TextView) convertView.findViewById(R.id.text);
　　    convertView.setTag(holder);
　　} else {
　　    holder = (ViewHolder) convertView.getTag();
　　}
　　holder.text.setText("line" + position);
　　return convertView;
}
 
private class ViewHolder {
　　TextView text;
}
```

##代码“微优化”
 当今时代已经进入了“微时代”。这里的“微优化”指的是代码层面的细节优化，即不改动代码整体结构，不改变程序原有的逻辑。尽管Android使用的是Dalvik虚拟机，但是传统的[Java]方面的代码优化技巧在Android开发中也都是适用的。

下面简要列举一部分。因为一般Java开发者都能够理解，就不再做具体的代码说明。
创建新的对象都需要额外的内存空间，要尽量减少创建新的对象。
将类、变量、方法等等的可见性修改为最小。
针对字符串的拼接，使用StringBuffer替代String。
不要在循环当中声明临时变量，不要在循环中捕获异常。
如果对于线程安全没有要求，尽量使用线程不安全的集合对象。
使用集合对象，如果事先知道其大小，则可以在构造方法中设置初始大小。
文件读取操作需要使用缓存类，及时关闭文件。
慎用异常，使用异常会导致性能降低。
如果程序会频繁创建线程，则可以考虑使用线程池。

看一个类：
```
public class MemoryActivity extends AppCompatActivity {

  //正确写法
    private final MyHandler mHandler = new MyHandler(this);
    private TextView mTextView;

    private static class MyHandler extends Handler {
        private WeakReference<Context> reference;

        public MyHandler(Context context) {
            reference = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            MemoryActivity activity = (MemoryActivity) reference.get();
            if (activity != null) {
                activity.mTextView.setText("");
            }
        }
    }
 //错误的写法
  private Handler mHandler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //...
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_main);
        mTextView = (TextView) findViewById(R.id.textview);
        loadData();
        exampleOne();
         executeAsy();
        new Thread(new MyRunnable()).start();
        new MyAsyncTask(this).execute();
    }

    private void loadData() {
        //...request
        Message message = Message.obtain();
        mHandler.sendMessage(message);
    }

    /**
     * 正确写法
     */

    static class MyAsyncTask extends AsyncTask<Void, Void, Void> {
        private WeakReference<Context> weakReference;

        public MyAsyncTask(Context context) {
            weakReference = new WeakReference<>(context);
        }

        @Override
        protected Void doInBackground(Void... params) {
            SystemClock.sleep(10000);
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            MemoryActivity activity = (MemoryActivity) weakReference.get();
            if (activity != null) {
            }
        }
    }
    /**
     * 异步任务和Runnable都是一个匿名内部类，因此它们对当前Activity都有一个隐   式引用。如果Activity在销毁之前
     * ，任务还未完成， 那么将导致Activity的内存资源无法回收，造成内存泄漏
     */

    private void executeAsy() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                SystemClock.sleep(10000);
                return null;
            }
        }.execute();
    }
    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            SystemClock.sleep(10000);
        }
    }
    /**
     * 只要非静态的匿名类对象没有被回收，MemoryActivity就不会被回收，MemoryActivity所关联的资源和视图都不会被回收，发生比较严重的内存泄漏。
     */


    private void exampleOne() {

        new Thread() {//匿名内部类，非静态的匿名类会持有外部类的一个隐式引用
            @Override
            public void run() {
                while (true) {
                    SystemClock.sleep(1000);
                }
            }
        }.start();
    }

    /**
     *单例模式非常受开发者的喜爱，不过使用的不恰当的话也会造成内存泄漏，由于单例的静态特性使得单例的生命周期和应用的生命周期一样长，
     * 这就说明了如果一个对象已经不需要使用了，
     * 而单例对象还持有该对象的引用，那么这个对象将不能被正常回收，这就导致了内存泄漏。
     */

    public static class AppManager {
        private static AppManager instance;
        private Context context;

        private AppManager(Context context) {
            this.context = context;
        }
        public static AppManager getInstance(Context context) {
            if (instance != null) {
                instance = new AppManager(context);
            }
            return instance;
        }
    }
    /**
     *正确写法
     */
    public static class AppManager1 {
        private static AppManager instance;
        private Context context;
        private AppManager1(Context context) {
            this.context = context.getApplicationContext();
        }
        public static AppManager getInstance(Context context) {
            if (instance != null) {
                instance = new AppManager(context);
            }
            return instance;
        }
    }
}

```

对以上几点做一个总结 - 不要让生命周期长于Activity的对象持有到Activity的引用 
- 尽量使用Application的Context而不是Activity的Context
- 尽量不要在Activity中使用非静态内部类，因为非静态内部类会隐式持有外部类实例的引用。如果使用静态内部类，将外部实例引用作为弱引用持有。
- 垃圾回收不能解决内存泄露，了解Android中垃圾回收机制

##Context

注意Context与ApplicationContext的区别 - View.getContext,返回当前View对象的Context对象，通常是当前正在展示的Activity对象。 
- Activity.getApplicationContext,获取当前Activity所在的(应用)进程的Context对象，通常我们使用Context对象时，要优先考虑这个全局的进程Context。
- ContextWrapper.getBaseContext():用来获取一个ContextWrapper进行装饰之前的Context，可以使用这个方法，这个方法在实际开发中使用并不多，也不建议使用。
- Activity.this 返回当前的Activity实例，如果是UI控件需要使用Activity作为Context对象，但是默认的Toast实际上使用ApplicationContext也可以。


![Paste_Image.png](http://upload-images.jianshu.io/upload_images/1990324-7f62855e088593c4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

大家注意看到有一些NO上添加了一些数字，其实这些从能力上来说是YES，但是为什么说是NO呢？ 
- 数字1：启动Activity在这些类中是可以的，但是需要创建一个新的task。一般情况不推荐。
- 数字2：在这些类中去layout inflate是合法的，但是会使用系统默认的主题样式，如果你自定义了某些样式可能不会被使用。
- 数字3：在receiver为null时允许，在4.2或以上的版本中，用于获取黏性广播的当前值。（可以无视） 注：ContentProvider、BroadcastReceiver之所以在上述表格中，是因为在其内部方法中都有一个context用于使用。

参考：http://blog.chinaunix.net/uid-26930580-id-3844811.html
