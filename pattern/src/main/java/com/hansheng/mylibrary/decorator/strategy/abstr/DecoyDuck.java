package com.hansheng.mylibrary.decorator.strategy.abstr;

import com.hansheng.mylibrary.decorator.strategy.impl.FlyNoWay;
import com.hansheng.mylibrary.decorator.strategy.impl.MuteQuack;

public class DecoyDuck extends Duck {
	public DecoyDuck() {
		setFlyBehavior(new FlyNoWay());
		setQuackBehavior(new MuteQuack());
	}
	public void display() {
		System.out.println("I'm a duck Decoy");
	}
}
