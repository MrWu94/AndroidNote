package com.hansheng.mylibrary.decorator.strategy.impl;

public class MuteQuack implements QuackBehavior {
	public void quack() {
		System.out.println("<< Silence >>");
	}
}
