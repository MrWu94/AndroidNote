package com.hansheng.mylibrary.decorator.strategy;

import com.hansheng.mylibrary.decorator.strategy.abstr.DecoyDuck;
import com.hansheng.mylibrary.decorator.strategy.abstr.Duck;
import com.hansheng.mylibrary.decorator.strategy.abstr.MallardDuck;
import com.hansheng.mylibrary.decorator.strategy.abstr.ModelDuck;
import com.hansheng.mylibrary.decorator.strategy.abstr.RubberDuck;
import com.hansheng.mylibrary.decorator.strategy.impl.FlyRocketPowered;


/**
 *
 * 多用组合，少用继承
 * 使用组合建立系统具有很大的弹性，不仅可奖算法族封装成类，更可以在"运行时动态地改变行为"，只要将组合的行为对象复合正确的接口标准
 * 针对接口编程，而不是针对实现编程，
 * 分开变化和不会变化的部分
 * 把会变化的部分取出并封装起来，好让其他部分不会受到影响
 * 找出应用中可能需要变化之处，把它们独立出来，不要和那些不需要变化的代码混在一起。
 */

public class MiniDuckSimulator {
 
	public static void main(String[] args) {
 
		MallardDuck mallard = new MallardDuck();
		RubberDuck rubberDuckie = new RubberDuck();
		DecoyDuck decoy = new DecoyDuck();
 
		Duck model = new ModelDuck();

		mallard.performQuack();
		rubberDuckie.performQuack();
		decoy.performQuack();
   
		model.performFly();	
		model.setFlyBehavior(new FlyRocketPowered());
		model.performFly();
	}
}
