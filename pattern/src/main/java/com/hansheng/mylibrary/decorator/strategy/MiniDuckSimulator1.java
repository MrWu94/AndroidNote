package com.hansheng.mylibrary.decorator.strategy;

import com.hansheng.mylibrary.decorator.strategy.abstr.Duck;
import com.hansheng.mylibrary.decorator.strategy.abstr.MallardDuck;
import com.hansheng.mylibrary.decorator.strategy.abstr.ModelDuck;
import com.hansheng.mylibrary.decorator.strategy.impl.FlyRocketPowered;

public class MiniDuckSimulator1 {
 
	public static void main(String[] args) {
 
		Duck mallard = new MallardDuck();
		mallard.performQuack();
		mallard.performFly();
   
		Duck model = new ModelDuck();
		model.performFly();
		model.setFlyBehavior(new FlyRocketPowered());
		model.performFly();

	}
}
