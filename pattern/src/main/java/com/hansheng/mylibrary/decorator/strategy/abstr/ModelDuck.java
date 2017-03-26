package com.hansheng.mylibrary.decorator.strategy.abstr;


import com.hansheng.mylibrary.decorator.strategy.impl.FlyNoWay;
import com.hansheng.mylibrary.decorator.strategy.impl.Quack;

public class ModelDuck extends Duck {
	public ModelDuck() {
		flyBehavior = new FlyNoWay();
		quackBehavior = new Quack();
	}

	public void display() {
		System.out.println("I'm a model duck");
	}
}
