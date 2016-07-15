package com.hansheng.thread.futuretask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by hansheng on 2016/7/15.
 */
public class BookInstance {
    /**
     * 当前的页码
     */
    private volatile int currentPage = 1;
    /**
     * 异步的任务获取当前页的内容
     */
    FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
        @Override
        public String call() throws Exception {
            return loadDataFromNet();
        }
    });

    /**
     * 实例化一本书，并传入当前读到的页码
     *
     * @param currentPage
     */

    public BookInstance(int currentPage) {
        this.currentPage = currentPage;

        Thread thread = new Thread(futureTask);
        thread.start();
    }

    /**
     * 获取当前页的内容
     *
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */

    public String getCurrentPageContent() throws ExecutionException, InterruptedException {

        String con = futureTask.get();
        /**
         * 预加载下一页的内容
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
     * 根据页码从网络抓取数据
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
            System.out.println("[1秒阅读时间]read:" + content);
            Thread.sleep(1000);
            System.out.println(System.currentTimeMillis() - start);
        }
    }

}
