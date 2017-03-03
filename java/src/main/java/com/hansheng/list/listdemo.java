package com.hansheng.List;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by hansheng on 16-10-17.
 * List是一个接口
 * List list = new ArrayList();这句创建了一个ArrayList的对象后把上溯到了List
 * 此时它是一个List对象了，有些ArrayList有但是List没有的属性和方法，它就不能再用了。
 * 在使用List集合时需要注意区分
 * add(int index, Object obj)方法和set(int index, Object obj)方法，前者是向指定索引位置添加对象，而后者是修改指定索引位置的对象，
 * 因为List集合可以通过索引位置访问对象，所以还可以通过for循环遍历List集合
 */

public class listdemo {
    public static void main(String[] args) {
        String a = "A", b = "B", c = "C", d = "D", e = "E";
        List<String> list = new LinkedList<String>();
        list.add(a);
        list.add(e);
        list.add(d);
        list.set(1, b);// 将索引位置为1的对象e修改为对象b
        list.add(2, c);// 将对象c添加到索引位置为2的位置
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));// 利用get(int index)方法获得指定索引位置的对象
        }
    }
}
