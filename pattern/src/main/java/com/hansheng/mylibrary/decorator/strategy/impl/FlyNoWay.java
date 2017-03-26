package com.hansheng.mylibrary.decorator.strategy.impl;

public class FlyNoWay implements FlyBehavior {
	public void fly() {
		System.out.println("I can't fly");
	}
}
