![](http://upload-images.jianshu.io/upload_images/1990324-51cbd2b56aa0a037.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
1. 用户参与
用户最常见的两种操作, 按Home键或Back键.
(1) 点击Home键, 暂停当前Activity, 调用onPause –> onSaveInstanceState –> onStop; 再次返回, 恢复当前页面, 调用onRestart -> onStart -> onResume.
(2) 点击Back键, 关闭当前Activity, 调用onPause -> onStop -> onDestroy; 再次启动则为重建, 从onCreate开始调用.
onStart()是Activity可见, 无法交互; **onResume()是Activity可见, 可以交互. onPause是Activity无法交互**, 必须执行前一个Activity的onPause完成, 后一个Activity才能启动, **在onPause中, 不能执行复杂的操作**, 否则会影响下一个Activity启动速度. onStop是Activity关闭显示.
（3）屏幕旋转
如果你不做任何配置
启动Activity会执行如下方法：
onCreate –> onStart –> onResume之后旋转屏幕，则Activity会被销毁并重新创建，之后便会执行如下方法：onPause –> onSaveInstanceState –> onStop –> onDestroy –> onCreate –> onStart –> onRestoreInstanceState –> onResume

在系统调用onCreate()之后，就会调用onStart(),然后继续调用onResume以进入Resumed状态，最后就会停在Resumed状态，完成启动。系统会调用onDestrory()来结束一个Activity的声明周期让它回到Killed形态。
onCreate():创建基本的UI元素。
onPause()与onStop():清除Activity的资源，避免浪费。
onDestory():因为引用会在Activity销毁的时候销毁，而线程不会，所以清除开启的线程。
**Activity启动模式**
+ standard（标准模式）
+ singleTop（栈顶复用模式）
+ singleTask（站内复用模式）
+ singleInstance（单实例复用模式）


![](http://upload-images.jianshu.io/upload_images/1990324-849ac7b1b7eee439.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
### standard
默认的启动模式，如果不指定Activity的启动模式，则使用这种方式启动Activity。这种启动模式每次都会创建新的实例，每次点击standard模式创建Activity后，都会创建新的Activity覆盖在原Activity上。
### singleTop
系统会判断当前栈顶Activity是不是要启动的Activity，如果是则不创建新的Activity而直接引用这个Acitivty。
### singleTask
singleTask是检查整个Activity栈中是否存在当前需要启动的Activity。如果存在，则将在Activity主语栈顶，并将该Activity以上的Acitivity以上的Activity都销毁。不过这里是指在同一个app中启动这个singleTask的Activity，如果是其他程序singleTask模式来启动这个，那么它将创建一个新的任务栈。
### singleInstance
singleInstance这种启动模式和使用的浏览器工作原理类似，比如多个程序访问浏览器，如果没有打开，则会打开浏览器。申明为singleInstance也一样，举个例子，如果应用A的任务栈中创建了MainActivity实例，且启动模式为singleInstance,如果B也要激活MainActivity,则不需要创建。

### Intent Flag模式
+ Intent.FLAG_Activity_NEW_TASK 
+ Intent.FLAG_ACTIVITY_CLEAR_TOP
+ Intent.FLAG_ACTIVITY_CLEAR_TOP
+ Intent.FLAG.ACTIVITY_NO_HISTORY

### 创建、配置Service
```java
public class FirstService extends Service{
       //必须实现的方法
      @Override
       public IBinder onBind(Intent arg0){
              return null
        }
        //Service被创建时回调该方法
        @Override
        public void onCreated(){
                super.onCreate()；
        }
        //Service被启动时回调该方法
        @Override
        public int onStartCommand(Intent intent,int flags,int startId){
              return   START_STICKY;
           }
        //Service被关闭之前回调该方法
        @Override
        public void onDestory(){
                super.onDestory();
        }
}
```
在AndroidManifest配置Service
```
<serivce name=".FirstService" />
```
当Service开发完成之后，接下来就要在程序中运行Service了，通过Context的**startService**方法，通过该方法启动Service,访问者与Service之间没有关联。

如果Service和访问者之间需要进行调用或交换数据，则应该使bingService()和unbingService()方法启动、关闭Service。
```java
public class BindService extends Service{
      private int count;
      private boolean quit;
      //定义onBinder方法锁返回的对象
      private MyBinder binder=new MyBinder();
      //通过继承Binder来实现IBinder类
      public class MyBinder extends Binder{
            public int getCount(){
                  //获取Service的运行状态
                  return count; 
            } 
      }
      //必须实现的方法，绑定该Service时回调该方法
      @Override
      public IBinder onBind(Intent intent){
              //返回IBinder对象
              return binder;
      }
      @Override
      public void onCreate(){
            super.onCreate();  
             new Thread(){
                    @Override
                     public void run(){
                           while(!quit){
                                    try{
                                           Thread.sleep(1000);
                                     }
                                      catch(InterruptedException){
                                                  
                                        }
                                         count++;
                            }
                      }
              }.start();
      }
      //Service被断开连接时回调改方法
      @Override
      public boolean onUnbind(Intent intent){
              return true;
      } 
      //Service被关闭之前回调该方法
       @Override
       public void onDestory(){
              super.onDestory();
              this.quit=true;
        }
}

pirvate ServiceConnection conn=new ServiceConnection(){
        @Override
        public void onSeriviceConnected(ComponentName name,IBinder serivce){
              //获取Serivce的onBind方法锁返回的MyBinder对象
              binder=(BinderService.MyBinder)serivce;
        }
        //当该Activity与Service断开连接时回调改方法
        @Override
        public void onSerivceDisconnected(ComponentName name){
                
        }
}
```

![](http://upload-images.jianshu.io/upload_images/1990324-8f94dc67b1a58fb7.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

###ContentProvider
ContentProvider相当于一个“网站”，它的作用是暴露可供操作的数据；其它应用程序则通过ContentResolver来操作ContentProvider所暴露的数据，ContentResolver相当于HttpClient.
