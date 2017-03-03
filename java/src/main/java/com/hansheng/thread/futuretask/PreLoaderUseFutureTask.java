package com.hansheng.Thread.Futuretask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by hansheng on 2016/7/15.
 * FutureTask���Է���ִ����ϵ����ݣ�����FutureTask��get����֧���������������ԣ�
 * ���ǿ�������Ԥ�ȼ���һЩ�����õ���Դ��Ȼ��Ҫ�õ�ʱ�򣬵���get������ȡ�������Դ�����ֱ꣬�ӷ��أ���������ȴ��������ɣ���
 *
 */
public class PreLoaderUseFutureTask {

    private final FutureTask<String> futureTask=new FutureTask<String>(new Callable<String>() {
        @Override
        public String call() throws Exception {
            Thread.sleep(3000);
            return "������Դ��Ҫ3��";
        }
    });
    public Thread thread=new Thread(futureTask);

    public void start(){
        thread.start();
    }

    public String getRes(){
        try {
            return futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws InterruptedException {

        PreLoaderUseFutureTask task=new PreLoaderUseFutureTask();

        task.start();
        Thread.sleep(2000);

        /**
         * ��ȡ��Դ
         */
        System.out.println(System.currentTimeMillis() + "����ʼ������Դ");
        String res = task.getRes();
        System.out.println(res);
        System.out.println(System.currentTimeMillis() + "��������Դ����");
    }



}
