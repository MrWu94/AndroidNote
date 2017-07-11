package com.hansheng.deepcopy.CloneConstructor;

/**
 * Created by mrwu on 2017/7/11.
 * 使用复制构造器也可以实现对象的拷贝。
 * <p>
 * 复制构造器也是构造器的一种
 * 只接受一个参数，参数类型为当前的类
 * 目的是生成一个与参数相同的新对象
 * 复制构造器相比clone方法的优势是简单，易于实现。
 * <p>
 * <p>
 * public static Car newInstance(Car car) {
 * return new Car(car);
 * }
 */

public class Car {
    Wheel wheel;
    String manufacturer;

    public Car(Wheel wheel, String manufacturer) {
        this.wheel = wheel;
        this.manufacturer = manufacturer;
    }

    //copy constructor
    public Car(Car car) {
        this(car.wheel, car.manufacturer);
    }

    public static class Wheel {
        String brand;
    }

    /**注意，上面的代码实现为浅拷贝，如果想要实现深拷贝，参考如下代码

     //copy constructor
     public Car(Car car) {
     Wheel wheel = new Wheel();
     wheel.brand = car.wheel.brand;

     this.wheel = wheel;
     this.manufacturer = car.manufacturer;
     }*/

}