package com.hansheng.Thread.productandConsumer.BlockQueue;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by hansheng on 17-3-22.
 */

public class Storage
{
    // 仓库最大存储量
    private final int MAX_SIZE = 100;

    // 仓库存储的载体
    private LinkedBlockingQueue<Object> list = new LinkedBlockingQueue<Object>(
            100);

    // 生产num个产品
    public void produce(int num)
    {
        // 如果仓库剩余容量为0
        if (list.size() == MAX_SIZE)
        {
            System.out.println("【库存量】:" + MAX_SIZE + "/t暂时不能执行生产任务!");
        }

        // 生产条件满足情况下，生产num个产品
        for (int i = 1; i <= num; ++i)
        {
            try
            {
                // 放入产品，自动阻塞
                list.put(new Object());
                System.out.println("生产【现仓储量为】:" + list.size());
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }


        }
    }

    // 消费num个产品
    public void consume(int num)
    {
        // 如果仓库存储量不足
        if (list.size() == 0)
        {
            System.out.println("【库存量】:0/t暂时不能执行生产任务!");
        }

        // 消费条件满足情况下，消费num个产品
        for (int i = 1; i <= num; ++i)
        {
            try
            {
                // 消费产品，自动阻塞
                list.take();
                System.out.println("消费【现仓储量为】:" + list.size());
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }


    }

    // set/get方法
    public LinkedBlockingQueue<Object> getList()
    {
        return list;
    }

    public void setList(LinkedBlockingQueue<Object> list)
    {
        this.list = list;
    }

    public int getMAX_SIZE()
    {
        return MAX_SIZE;
    }
}