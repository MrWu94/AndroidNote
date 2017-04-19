package com.hansheng.studynote.Http.Retrofit;

/**
 * Created by hansheng on 2016/7/1.
 */
public class ApiException extends RuntimeException {

    public static final int USER_NOT_EXIST=100;
    public static final int WRONG_PASSWORD=101;

    public ApiException(int resultCode){
        this(getApiExceptionMessage(resultCode));
    }
    public ApiException(String detailMessage){
        super(detailMessage);
    }
    public static String getApiExceptionMessage(int code){
        String message="";
        switch(code){
            case USER_NOT_EXIST:
                message="该用户不存在";
                break;
            case WRONG_PASSWORD:
                message="密码错误";
                break;
            default:
                message="未知错误";
                break;
        }
        return message;
    }
}
