package com.hansheng.mylibrary.decorator.decorator.starbuzz.abstr;


/**
 * 抽象组件
 */

public abstract class Beverage {
	String description = "Unknown Beverage";
  
	public String getDescription() {
		return description;
	}
 
	public abstract double cost();
}
