package com.hansheng.Thread.share;

/**
 * Created by hansheng on 2016/7/29.
 * ���������ݷ�װ������һ�������з�װ������һ�������У�Ȼ�����������һ���ݸ�����Runnable����
 * ÿ���̶߳Թ������ݵĲ�������Ҳ���䵽�Ǹ�����������ɣ���������ʵ��������ݽ��и��������Ļ����ͨ��
 * ��Runnable������Ϊżһ������ڲ��࣬����������Ϊ�����ĳ�Ա������ÿ���̶߳Թ������ݵĲ�������Ҳ��װ���ⲿ�࣬�Ա�ʵ�ֶ����ݵĸ�
 * ��������ͬ���ͻ��⣬��Ϊ�ڲ���ĸ���Runnable��������ⲿ�����Щ������
 */
public class MyData {
    private int j = 0;

    public synchronized void add() {
        j++;
        System.out.println("�߳�" + Thread.currentThread().getName() + "jΪ" + j);

    }

    public synchronized void dec() {
        j--;
        System.out.println("�߳�" + Thread.currentThread().getName() + "jΪ��" + j);
    }

    public int getData() {
        return j;
    }
}
