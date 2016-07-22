package com.hansheng.RMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by hansheng on 2016/7/22.
 */
public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote {


    protected MyRemoteImpl() throws RemoteException {
    }

    @Override
    public String sayHello() throws RemoteException {
        return "Server says hello";
    }

    public static void main(String... args)  {
        MyRemote service= null;
        try {
            service = new MyRemoteImpl();
            Naming.rebind("RemoteHello",service);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}
