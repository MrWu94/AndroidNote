Handler:**谁发送，谁处理**，主要工作就是消息的发送和接收过程。消息的发送可以通过post的一系列方法以及send的一系列方法来实现，post的方式最终是通过send的方法来实现的。
Handler发送一条消息的过程
```
public final boolean sendMessage(Message msg){
        return sendMessageDelayed(msg,0);
}
public final boolean sendMessageDelayed(Message msg,long delayMillis){
      if(delayMillis<0){
              delayedMillis=0;
      }
      return sendMessageAtTime(msg,SystemColock.uptimeMillis()+delayMillis);
}
public boolean sendMessageAtTime(Message msg,long uptimeMillis){
        MessageQueue queue=mQueue;
        if(queue==null){
               RuntimeExection e=new RuntimeException(this+"sendMessageAtTime() called with no mQuene")
                Log.w("looper",e.getMessage(),e);
                return false;
         }
           //向队列中插入一条消息
          return enqueueMessage(queue,msg,uptimeMillis);
 }
private boolean enqueueMessage(MessageQueue queue,Message msg,long uptimeMillis){
        msg.target=this;
        if(mAsynchronous){
              msg.setAsychronous(true);
        } 
        return queue.enqueueMessage(msg,uptimeMillis);
}
```
Handler发送消息的过程是向消息队列中插入一条消息，MessageQueue的next方法就会返回这条消息给Looper，Looper收到这条消息之后就开始处理了，最终消息由looper交由Handler处理，即Handler的dispatchmessage方法会被调用，这是Handler就进入处理消息的阶段。
```
public void dispatchMessage(Message msg){
          //检查message的callback是否为null
          if(msg.calllback!=null){
              handleCallback(msg);           
           }
            else{
                  if(mCallback!=null){
                            if(mCallback.handleMessage(mssg)){
                                    return;
                              } 
                   }
                    handleMessage(msg);
             }
}
```

Handlet处理消息的过程
首先，检查Message的callback是否为null,不为空就通过handleCallback来处理消息。message的callback是一个Runnbale对象，实际上就是Handler的post方法所传递的Runnable参数。
```
private static void handleCallback(Message message){
        message.callback.run(); 
}
```
其次，检查mCallback是否为空，不为null及调用mCallback的handleMessage方法来处理消息
```
public interface Callback{
      public boolean handleMessage(Message msg); 
}
```

![](http://upload-images.jianshu.io/upload_images/1990324-37f423ad4a8691cb.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
从这个图中我们很清楚可以看到**调用sendEmptyMessage后，会把Message对象放入一个MessageQueue队列，该队列属于某个Looper对象，每个Looper对象通过ThreadLocal.set(new Looper())跟一个Thread绑定了，Looper对象所属的线程在Looper.Loop方法中循环执行从MessageQueue队列读取Message对象，并把Message对象交由Handler处理，调用Handler的dispatchMessage方法**。
     现在我们再来看一下使用Handler的基本实现代码：
```
       // 主线程中新建一个handler
         Handler  h= new Handler() {
                  public void handleMessage(android.os.Message msg) {             
                    }
            };
```



Android的消息机制主要是Handler的运行机制，Handler的运行需要底层MessageQueue和looper的支撑。
**MessageQueue**:消息队列，内部存储了 一组消息，以队列的形式提供插入和删除的工作。
**Looper**:消息循环，由于MessageQueue只是一个消息的存储单元 ，他不能去处理消息，Looper就填补了这个功能，Looper会以无限循环的形式去找是否有这个消息，如果有消息的话就处理消息，否则就一直等待。线程是默认没有Looper的，如果需要使用Handler及必须为线程创建Looper。



在一个Android程序中开始运行的时候，会单独开启一个进程。默认的情况下所有这个程序中（四大组件）Activity,Service,Content Provider ，Broadcase Receiver 都会跑在这个进程中。一个Android程序默认情况下也只有一个进程，**但一个进程却可以有多个线程（Thread）**。在这么多Thread当中，有一个Thread,我们称之为UI Thread.UI Thread在Android程序运行的时候就被创建，是一个Process当中的主线程Main Thread，主要是负责控制UI界面的显示、更新和控件交互。在Android程序创建之初，一个Process呈现的是单线程模型，所有的任务都在一个线程中运行。因此，我们认为，UI Thread所执行的每一个函数，所花费的时间都应该是越短越好。而其他比较**费时的工作（访问网络，下载数据，查询数据库等），都应该交由子线程去执行**，以免阻塞主线程，导致ANR。那么问题来了，UI 主线程和子线程是怎么通信的呢。这就要提到我们这里要讲的Handler机制。﻿﻿


Handler机制被引入的目的就是为了实现线程间通信。Handler一共干了两件事：在子线程中发出massage,在主线程获取，处理message。听起来好像很容易，如果面试中让你阐述下Handler机制，我们这么回答显然不是面试官想要的答案。我们忽略了一个问题：**子线程何时发送message,主线程何时获取处理message。**

![](http://upload-images.jianshu.io/upload_images/1990324-920a0bdaef9a621c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

在这张图我们可以看到首先在ActivityThread创建UI线程，然后会看到有一个Looper.prepareMainLooper(),此时会创建Looper对象和MessageQueue对象，当在UI线程new Handler()时，此时，Handler与Looper，MessageQueue建立关联，这个，当子线程handler.sendMessage(message)时，Looper.loop()会在messageQueue循环读取对象Message msg=queue.next(),然后分发消息给Handler,有他来处理消息msg.target.dispatchMessage(msg),最后调用handleMessage(msg)处理消息，更细UI线程。



![](http://upload-images.jianshu.io/upload_images/1990324-a4a6ba47fd625a6f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

助理小怡（Looper）

助理小怡的工作内容主要有两件事情，第一件事情是做好准备工作（**Looper.prepare()**），即到某宝上包邮购买了一个消息盒子（**MessageQueue**），用来收集攻城狮们（**Thread**）的各种诉求（**Message**）。第二件事情是开启无限循环的工作模式（**Looper.loop()**），她无比勤劳的等待着攻城狮们的各种召唤，收集他们的诉求，并传达给技术总监。

技术总监(Handler)
助理Looper在loop()中通过**msg.target.dispatchMessage(msg)**将消息分发给技术总监Handler，那么Handler将如何对这些Message进行处理.
