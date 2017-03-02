package com.hansheng.Tcp;

import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by hansheng on 2016/8/19.
 */
public class IoUtils {
    public static void closeQuitly(Closeable closeable) {
    if (closeable != null) {
        try {
            closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

    public static void closeSocket(Socket closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
