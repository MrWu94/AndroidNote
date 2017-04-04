package com.hansheng.mylibrary.decorator.decorator.starbuzz.condiment;

import com.hansheng.mylibrary.decorator.decorator.starbuzz.abstr.Beverage;

/**
 * 抽象装饰者
 */
public abstract class CondimentDecorator extends Beverage {
	public abstract String getDescription();
}
