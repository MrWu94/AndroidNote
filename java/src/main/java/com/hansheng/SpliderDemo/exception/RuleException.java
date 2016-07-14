package com.hansheng.SpliderDemo.exception;

/**
 * Created by hansheng on 2016/7/14.
 */
public class RuleException extends RuntimeException{
    public RuleException() {
    }

    public RuleException(String message, Throwable cause) {
        super(message, cause);
    }

    public RuleException(String message) {
        super(message);
    }

    public RuleException(Throwable cause) {
        super(cause);
    }
}
