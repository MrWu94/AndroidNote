package com.hansheng.studynote.loader.ImageLoaderUtil;

/**
 * Created by hansheng on 17-1-9.
 */

public class ImageLoaderFactory {

    private static volatile ImageLoaderWrapper imageLoaderWrapper;

    private ImageLoaderFactory(){}


    public static ImageLoaderWrapper getLoader(){
        if(imageLoaderWrapper==null){
            synchronized (ImageLoaderFactory.class){
                if(imageLoaderWrapper==null){
                    imageLoaderWrapper=new UniversalAndroidImageLoader();
                }
            }
        }

        return imageLoaderWrapper;
    }
}
