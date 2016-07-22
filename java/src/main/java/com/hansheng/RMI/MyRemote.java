package com.hansheng.RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by hansheng on 2016/7/22.
 */
public interface MyRemote extends Remote {

    public String sayHello() throws RemoteException;
}
