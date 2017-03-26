package com.hansheng.mylibrary.decorator.strategy.abstr;

import com.hansheng.mylibrary.decorator.strategy.impl.Quack;
import com.hansheng.mylibrary.decorator.strategy.impl.FlyWithWings;

public class RedHeadDuck extends Duck {
 
	public RedHeadDuck() {
		flyBehavior = new FlyWithWings();
		quackBehavior = new Quack();
	}
 
	public void display() {
		System.out.println("I'm a real Red Headed duck");
	}
}
