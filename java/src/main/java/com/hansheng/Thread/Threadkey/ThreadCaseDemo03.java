package com.hansheng.Thread.Threadkey;

/**
 * Created by hansheng on 16-9-27.
 */

class Info{ // 定义信息类
    private String name = "name";//定义name属性，为了与下面set的name属性区别开
    private String content = "content" ;// 定义content属性，为了与下面set的content属性区别开
    private boolean flag = true ;   // 设置标志位,初始时先生产
    public synchronized void set(String name,String content){
        while(!flag){
            try{
                super.wait() ;
            }catch(InterruptedException e){
                e.printStackTrace() ;
            }
        }
        this.setName(name) ;    // 设置名称
        try{
            Thread.sleep(300) ;
        }catch(InterruptedException e){
            e.printStackTrace() ;
        }
        this.setContent(content) ;  // 设置内容
        flag  = false ; // 改变标志位，表示可以取走
        super.notify();
    }
    public synchronized void get(){
        while(flag){
            try{
                super.wait() ;
            }catch(InterruptedException e){
                e.printStackTrace() ;
            }
        }
        try{
            Thread.sleep(300) ;
        }catch(InterruptedException e){
            e.printStackTrace() ;
        }
        System.out.println(this.getName() +
                " --> " + this.getContent()) ;
        flag  = true ;  // 改变标志位，表示可以生产
        super.notify();
    }
    public void setName(String name){
        this.name = name ;
    }
    public void setContent(String content){
        this.content = content ;
    }
    public String getName(){
        return this.name ;
    }
    public String getContent(){
        return this.content ;
    }
}
class Producer implements Runnable{ // 通过Runnable实现多线程
    private Info info = null ;      // 保存Info引用
    public Producer(Info info){
        this.info = info ;
    }
    public void run(){
        boolean flag = true ;   // 定义标记位
        for(int i=0;i<10;i++){
            if(flag){
                this.info.set("姓名--1","内容--1") ;    // 设置名称
                flag = false ;
            }else{
                this.info.set("姓名--2","内容--2") ;    // 设置名称
                flag = true ;
            }
        }
    }
}
class Consumer implements Runnable{
    private Info info = null ;
    public Consumer(Info info){
        this.info = info ;
    }
    public void run(){
        for(int i=0;i<10;i++){
            this.info.get() ;
        }
    }
}
public class ThreadCaseDemo03{
    public static void main(String args[]){
        Info info = new Info(); // 实例化Info对象
        Producer pro = new Producer(info) ; // 生产者
        Consumer con = new Consumer(info) ; // 消费者
        new Thread(pro).start() ;
        //启动了生产者线程后，再启动消费者线程
        try{
            Thread.sleep(500) ;
        }catch(InterruptedException e){
            e.printStackTrace() ;
        }

        new Thread(con).start() ;
    }
}