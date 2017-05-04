### Broadcast
一个是广播的注册过程，另一个是广播的发送和接收过程。
广播的接收者，只需要继承BroadcaseReceiver并冲重写onReceive方法即可。
```java
public class MyReceiver extends BroadcastReceiver{
          @Overide
          public void onReceive(Context context,Intent intent){
                  //onReceive函数不能做耗时的操作，参考值：10s内
                  Log.d("hansheng","on receive action="+intent.getAction());
                  String action=intent.getAction();
          }
}
```
定义好了广播接收者，接着还需要注册广播接收者，注册方式分为两种，即可以在AndroidManifest文件中静态注册，也可以在代码中动态注册。
###静态注册
```java
<receiver android:name=".MyReceiver">
  <intent-filter>
          <action android:name="com.hansheng">
  </intent-filter>
</receiver>
```
###动态注册
```java
IntentFilter filter=new IntentFilter();
filter.addAction("com.hansheng");
registerReceiver(new MyReveiver(),filter);
```
前面两部都完成以后，就可以通过send方法来发送广播了
```java
Intent intent=new Intent();
intent.setAction(com.hansheng);
sendBroadcast(intent);
```
###Eventbus
Android应用内常用通信方式
1、使用Handler进行线程间通信
handler是和Thread绑定的，主线程中创建Handler直接通过new Handler()来创建即可。自定义线程则需调用Looper.prepare(),Looper.loop()来让Handler起作用。

Handler主要有两个作用，一个是线程间传递消息，另一个则是post Runnable对象，让Runnable对象在Handler对应的那个线程里运行

```java
public class MainActivity extends Activity{
          private Handler mHandler;
          private Button button;
          @Override
          Protected void onCreate(Bundle savedInstanceState){
                   super.onCreate(savedInstancedState);
                   setContentView(R.layout.activity_main);
                   button=(Button)findViewById(R.id.button1);
                    mHandler=new Handler(){
                           @Override
                           public void handleMessage(Message msg){
                                    if(msg.what==oxff){
                                            Log.e("what", "I got the Message");
                                      }
                           } 
                    } 
                  button.serOnClickListener(new OnClickListener(){
                              @Override
                              public void onClick(View v){
                                        new Thread(new Runnable(){
                                                @Override
                                                public void run(){
                                                        try{
                                                                  Thread.sleep(1000);
                                                           }catch  (InterruptedException e) { 
                                                                      e.printStackTrace(); }
                                                }
                                                mHandler.sendEmptyMessage(oxff);
                                         }).start();
                              }
                  });
          }
          
 }
```
使用Interface定义回调，不懂得可以看看[回调机制](http://www.jianshu.com/p/18ec24b6a39f),可以用在任意两个对象之间。比如Activity和Fragment通信
```java
public class LeftFragment extends Fragment {
    
    private Button button;
    private EditText editText;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_left, null);
        editText = (EditText) view.findViewById(R.id.editText1);
        return view;
    }
    
    @Override
    public void onPause() {
        super.onPause();
    }
    
    //接口回调
    public void getEditText(CallBack callBack) {
        String msg = editText.getText().toString();
        callBack.getResult(msg);
    }

    public interface CallBack {
        public void getResult(String result);
    }
}

public class MainActivity extends Activity {
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);        
        button = (Button)findViewById(R.id.button);
        
        //动态加载leftFragment
        manager = getFragmentManager();
        transaction = manager.beginTransaction();
        final LeftFragment leftFragment = new LeftFragment();
        transaction.add(R.id.left, leftFragment, "left");
        transaction.commit();
        button.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                //点击按钮后，通过接口回调，获取fragment当中EditText的值，并弹出吐司
                leftFragment.getEditText(new CallBack(){
                    @Override
                    public void getResult(String result) {
                        // TODO Auto-generated method stub
                        Toast.makeText(MainActivity.this, result, 1).show();
                    }                    
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }    
}
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="horizontal"
    tools:context=".MainActivity" >

     <LinearLayout
        android:id="@+id/left"
        android:layout_width="224dp"
        android:layout_height="match_parent"
        android:background="#CCCCCC"
        android:orientation="vertical" >
    </LinearLayout>

    <LinearLayout
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:orientation="vertical" >

        <EditText
            android:id="@+id/editText1"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:ems="10" >
            <requestFocus />
        </EditText>

        <Button
            android:id="@+id/button"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="获得Fragment的值" />
    </LinearLayout> 

</LinearLayout>
```
使用Intent进行组件间的通信
通过Intent来比如启动Service，启动Activity， 通过Intent来发送Broadcast，然后注册一个BroadcastReceiver来接收并处理Intent.

###EventBus进行应用内通信
简介
EventBus是Android端的一个订阅/发布的消息总线，用在应用程序中，组件之间，线程之间的通信，并且由于事件可以是任意类型的对象，所以使用起来更加的方便快捷。
角色;
Event：事件
Subscriber:订阅者，接收特定事件的对象，主要通过onEventXXX()回调接口接收
Publisher:发布者，post事件，通知订阅者
使用Eventbus只有简单的4部
1、注册一个Event事件
2、注册一个订阅者
3、发布一个事件
4、接收这个事件
用代码实现着4不就是：
```java
1、public class MyEvent()
2、EventBus.getDefault().register(this)---比如这是一个Activity,那就等于把Activity注册为一个订阅者
3、EventBus.getDefault.post(event)-———随便哪个组件或者线程中发送出一个MyEvent的对象
4、public void onEvent(MyEvent event)———第2步中的Activity中的onEvent方法接收到该事件并处理。注意一定Event类型要对应。如果写成了onEvent(Object event)，那就是post什么类型的Event都能接受了。
```
```java
public class MainActivity extends Activity{

    private TextView textView;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button) findViewById(R.id.button1);
        textView= (TextView) findViewById(R.id.textView1);
        //1.注册订阅者
        EventBus.getDefault().register(this);
        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
            //post事件
                EventBus.getDefault().post(new MyEvent());
            }
        });
    }
    //接收事件的回调
    public void onEvent(MyEvent event){
        Log.e("What", "[onEvent]My Thread is "+Thread.currentThread().getName());
    }
}
```
onEventXXXX
onEvent函数一共有四种，前面的例子只用到一个onEvent。

函数名 | 含义 | ThreadMode
-------|-------|-------
onEvent|事件处理在事件发送的那个线程执行|PostThread
onEventMainThread|事件在主线程-UI线程执行|MainThread
onEventBackgroundThread|事件在一个后台线程执行（就一个后台线程）|BackgroundThread
onEventAsync	|事件会单独启动一个线程执行(每个事件都会启动一个线程)|Async

onEventMainThread使用演示

onEventMainThread就是会放到主线程去执行的事件处理，一般在其中进行比如UI的更新的操作，比如TextView的更改。下面通过代码展示一下。一个自定义的线程发送该事件，Activity通过onEventMainThread接收到该事件,并更新UI.这个时候就不能使用onEvent了，因为根据上表所示，onEvent会放在发送事件的那个线程中去执行，其实不能进行UI更新操作的。
```java
public class MainActivity extends Activity{

    private TextView textView;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button) findViewById(R.id.button1);
        textView= (TextView) findViewById(R.id.textView1);
        //1.注册订阅者
        EventBus.getDefault().register(this);
        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        EventBus.getDefault().post(new MyEvent());              
                    }
                }).start();
            }
        });
    }

    public void onEvent(MyEvent event){
        Log.e("What", "[onEvent]My Thread is "+Thread.currentThread().getName());
    }
    public void onEventMainThread(MyEvent event){
        Log.e("What", "[onEventMainThreadMy] Thread is "+Thread.currentThread().getName());
        textView.setText("onEventMainThread called");
    }

}
```
###Sticky Event
Sticky Event是指我注册一个sticky的订阅，这样注册之前发送的sticky事件的最近的一个会保存在内存中，错过这个事件的发送的情况下，也可以通过getStickyEvent收到。
+ postSticky(event) ：发送sticky的event
+ registerSticky(subscriber) :注册接收sticky事件的订阅者
+ getStickyEvent(Class)
```java
public class MainActivity extends Activity {
    private TextView textView;
    private Button sendEvent;
    private Button quaryEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendEvent = (Button) findViewById(R.id.button1);
        quaryEvent = (Button) findViewById(R.id.button2);
        textView = (TextView) findViewById(R.id.textView1);
        // 1.注册订阅者
        EventBus.getDefault().registerSticky(this);
        sendEvent.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // 点击按键发送5个Sticky event
                for (int i = 0; i < 5; i++) {
                    EventBus.getDefault().postSticky(
                            new MyEvent(String.valueOf(i)));
                }

            }
        });

        quaryEvent.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                MyEvent lastEvent = EventBus.getDefault().getStickyEvent(
                        MyEvent.class);
                Log.e("What", "[quaryEvent] last Event is" + lastEvent.what);
                //打印出最后一个Event: ----What(20520): [quaryEvent] last Event is4
            }
        });
    }
    public void onEvent(MyEvent event) {
        Log.e("What", "[onEvent] this Event is" + event.what);
    }
}
```
