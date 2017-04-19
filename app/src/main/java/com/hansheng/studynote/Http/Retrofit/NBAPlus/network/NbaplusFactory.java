package com.hansheng.studynote.Http.Retrofit.NBAPlus.network;

/**
 * Created by hansheng on 2016/7/5.
 */
public class NbaplusFactory {

    private static NbaplusAPI sInstance=null;
    private static NewsDetileAPI sNewsDetitleInstance=null;

    private static final Object WATCH_DOG=new Object();

    public static NbaplusAPI getNbaplusInstance(){

        synchronized (WATCH_DOG){
            if(sInstance==null){
                NbaplusClient nbaplusClient=new NbaplusClient();
                sInstance=nbaplusClient.getClient();
                sNewsDetitleInstance=nbaplusClient.getNewsDetileClient();
            }
            return sInstance;
        }

    }
    public static NewsDetileAPI getNewsDetileInstance() {
        synchronized (WATCH_DOG) {
            if(sNewsDetitleInstance==null){
                NbaplusClient nbaplusClient = new NbaplusClient();
                sInstance= nbaplusClient.getClient();
                sNewsDetitleInstance= nbaplusClient.getNewsDetileClient();
            }
            return sNewsDetitleInstance;
        }
    }

}
