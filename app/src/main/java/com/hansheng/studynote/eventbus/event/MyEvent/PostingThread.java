package com.hansheng.studynote.eventbus.event.MyEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hansheng on 2016/7/13.
 */
public class PostingThread {
    List<Object> mEventQueue=new ArrayList<>();
    boolean isMainThread;
    boolean isPosting;

    PostingThread(){}
}
