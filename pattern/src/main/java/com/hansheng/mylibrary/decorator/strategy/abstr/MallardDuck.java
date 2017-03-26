package com.hansheng.mylibrary.decorator.strategy.abstr;

import com.hansheng.mylibrary.decorator.strategy.impl.Quack;
import com.hansheng.mylibrary.decorator.strategy.impl.FlyWithWings;

public class MallardDuck extends Duck {

	public MallardDuck() {

		quackBehavior = new Quack();
		flyBehavior = new FlyWithWings();

	}

	public void display() {
		System.out.println("I'm a real Mallard duck");
	}
}
