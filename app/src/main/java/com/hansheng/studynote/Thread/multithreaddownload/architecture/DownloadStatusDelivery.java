package com.hansheng.studynote.Thread.multithreaddownload.architecture;


public interface DownloadStatusDelivery {

    void post(DownloadStatus status);

}
