package com.hansheng.Thread.Futuretask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by hansheng on 2016/7/15.
 */
public class BookInstance {
    /**
     * ��ǰ��ҳ��
     */
    private volatile int currentPage = 1;
    /**
     * �첽�������ȡ��ǰҳ������
     */
    FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
        @Override
        public String call() throws Exception {
            return loadDataFromNet();
        }
    });

    /**
     * ʵ����һ���飬�����뵱ǰ������ҳ��
     *
     * @param currentPage
     */

    public BookInstance(int currentPage) {
        this.currentPage = currentPage;

        Thread thread = new Thread(futureTask);
        thread.start();
    }

    /**
     * ��ȡ��ǰҳ������
     *
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */

    public String getCurrentPageContent() throws ExecutionException, InterruptedException {

        String con = futureTask.get();
        /**
         * Ԥ������һҳ������
         * */
        this.currentPage = currentPage + 1;

        Thread thread = new Thread(futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return loadDataFromNet();
            }
        }));
        thread.start();
        return con;
    }

    /**
     * ����ҳ�������ץȡ����
     *
     * @return
     * @throws InterruptedException
     */

    private String loadDataFromNet() throws InterruptedException {
        Thread.sleep(1000);
        return "Page " + this.currentPage + " : the content ....";
    }

    public static void main(String... args) throws ExecutionException, InterruptedException {
        BookInstance instance = new BookInstance(1);
        for (int i = 0; i < 10; i++) {
            long start = System.currentTimeMillis();
            String content = instance.getCurrentPageContent();
            System.out.println("[1���Ķ�ʱ��]read:" + content);
            Thread.sleep(1000);
            System.out.println(System.currentTimeMillis() - start);
        }
    }

}
