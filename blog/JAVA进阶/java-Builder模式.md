静态工厂和构建器有个共同的局限性;他们都不能很好地扩展到大量的可选参数。
用Builder模式，不直接生成想要的对象，而是让客户端利用所有必要的参数调用构造器
```java
public class Nutrition{
      private final int servingSize;
      private final int  servings;
      private final int  calories;
      private final int fat;
      private final int sodium;
      private final int carbohydrate;
      public static class Bulider{
            //必须参数
            private final int servingSize;
            private final int servings;
            //可选参数
            private int calories=0;
            privatre int fas=0;
            private int carbohydrate=0;
            private int sodium=0;
            public Builder(int servingSize,int servings){
                this.servingSize=servingSize;
                this.servings=servings;
             }
            public Builder calories(int val){
                  calories=val;
                  return this;
            }
            public Builder fat(int val){
                   fat=val;
                  return this;
            }
            public Builder carbohydrate(int val){
                  carbohydrate=val;
                  return this;
            }
            public Builder sodium(int val){
                sodium=val;
                return this;
          }
          public Nutrition build(){
                return new Nutrition(this);
          }
      }
      private Nutrition(Builder builder){
            servingSize=builder.servingSize;
            servings=builder.servings;
            calories=builder.calories;  
            fat=builder.fat;
            sodium=builder.sodium;
            carbohydrate=builder.carbohydrate;
      }
}
```
怎么使用呢？
```java
Nutrition nutr=new Nutrition.Builder(240,8).calories(100).sodium(35).carbohyrate(27).build();
```

