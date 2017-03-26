package com.hansheng.mylibrary.decorator.strategy.impl;

public class FakeQuack implements QuackBehavior {
	public void quack() {
		System.out.println("Qwak");
	}
}
