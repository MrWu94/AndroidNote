package com.hansheng.chain;

/**
 * Created by hansheng on 2016/7/16.
 * 一个请求沿着一条“链”传递，直到该“链”上的某个处理者处理它为止。
 * 请求和处理分离开来，对于程序猿来说，不需要知道是谁给他批复的钱，
 * 而对于领导们来说，也不需要确切地知道是批给哪个程序猿，只要根据自己的责任做出处理即可，由此将两者优雅地解耦。
 */
abstract class Logger {
    public static int ERR = 3;
    public static int NOTICE = 5;
    public static int DEBUG = 7;
    protected int mask;

    // The next element in the chain of responsibility
    protected Logger next;

    public Logger setNext(Logger l) {
        next = l;
        return this;
    }

    public final void message(String msg, int priority) {
        if (priority <= mask) {
            writeMessage(msg);
            if (next != null) {
                next.message(msg, priority);
            }
        }
    }

    protected abstract void writeMessage(String msg);

}

class StdoutLogger extends Logger {

    public StdoutLogger(int mask) {
        this.mask = mask;
    }

    protected void writeMessage(String msg) {
        System.out.println("Writting to stdout: " + msg);
    }
}


class EmailLogger extends Logger {

    public EmailLogger(int mask) {
        this.mask = mask;
    }

    protected void writeMessage(String msg) {
        System.out.println("Sending via email: " + msg);
    }
}

class StderrLogger extends Logger {

    public StderrLogger(int mask) {
        this.mask = mask;
    }

    protected void writeMessage(String msg) {
        System.out.println("Sending to stderr: " + msg);
    }
}

class ChainOfResponsibilityExample {
    public static void main(String[] args) {
        // Build the chain of responsibility
        Logger l = new StdoutLogger(Logger.DEBUG).setNext(
                new EmailLogger(Logger.NOTICE).setNext(
                        new StderrLogger(Logger.ERR)));

        // Handled by StdoutLogger
        l.message("Entering function y.", Logger.DEBUG);

//        // Handled by StdoutLogger and EmailLogger
//        l.message("Step1 completed.", Logger.NOTICE);
//
//        // Handled by all three loggers
//        l.message("An error has occurred.", Logger.ERR);
    }
}