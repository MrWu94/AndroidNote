package com.hansheng.OutterandInnerClass.Closure;

/**
 * Created by hansheng on 17-3-3.
 * 闭包（Closure）是一种能被调用的对象，它保存了创建它的作用域的信息。JAVA并不能显式地支持闭包，
 * 但是在JAVA中，闭包可以通过“接口+内部类”来实现。
 * 例如：一个接口程序员和一个基类作家都有一个相同的方法work，相同的方法名，但是其含义完全不同，这时候就需要闭包。
 * <p>
 * 内部类的作用
 * <p>
 * 内部类可以很好的实现隐藏。
 * 一般的非内部类，是不允许有 private 与protected权限的，但内部类可以
 * 内部类拥有外围类的所有元素的访问权限
 * 可是实现多重继承
 * 可以避免修改接口而实现同一个类中两种同名方法的调用。
 * <p>
 * <p>
 * 闭包(closure)是一个可调用的对象，它记录了一些信息，这些信息来自于创建它的作用域。通过这个定义，
 * 可以看出内部类是面向对象的闭包，因为它不仅包含外围类对象(创建内部类的作用域)的信息，
 * 还自动拥有一个指向此外围类对象的引用，在此作用城内，内部类有权操作所有的成员，包括private成员。
 * 通过这种内部类可以很轻松的实现回调功能，回调就是说某个方法一旦获得了内部类对象引用和，就可以在合适的时候调用外部类实例的方法。
 *
 * 内部类可以大大弥补Java在多重继承上的不足。另外匿名内部类可以方便的实现闭包。静态内部类可以带来更好的代码聚合，提高代码可维护性。
 */
public class WriterProgrammer extends Writer {
    @Override
    public void work() {
        //写作
    }

    public void code() {
        //写代码
    }

    class ProgrammerInner implements programmer {
        @Override
        public void work() {
            code();
        }
    }

}