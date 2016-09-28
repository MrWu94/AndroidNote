package com.hansheng.block;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by hansheng on 16-9-28.
 * 从执行结果中可以看出，由于队列中元素的数量限制在了20个，因此添加20个元素后，其他元素便在队列外阻塞等待，程序并没有终止。
 */

public class BlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> bqueue = new ArrayBlockingQueue<String>(20);
        for (int i = 0; i < 30; i++) {
            //将指定元素添加到此队列中
            bqueue.put("加入元素" + i);
            System.out.println("向阻塞队列中添加了元素:" + i);
        }
        System.out.println("程序到此运行结束，即将退出----");
    }

}

/**
 * 从结果中可以看出，当添加了第20个元素后，我们从队首移出一个元素，这样便可以继续向队列中添加元素，
 * 之后每添加一个元素，便从将队首元素移除，这样程序便可以执行结束。
 */
class BlockingQueueTest1 {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> bqueue = new ArrayBlockingQueue<String>(20);
        for (int i = 0; i < 30; i++) {
            //将指定元素添加到此队列中
            bqueue.put("" + i);
            System.out.println("向阻塞队列中添加了元素:" + i);
            if (i > 18) {
                //从队列中获取队头元素，并将其移出队列
                System.out.println("从阻塞队列中移除元素：" + bqueue.take());
            }
        }
        System.out.println("程序到此运行结束，即将退出----");
    }
}


