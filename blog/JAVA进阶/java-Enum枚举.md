1、用enum代替int常量
枚举类型是指一组固定的常量组成合法值的类型。

怎么定义？
```java
public enum Apple{FUJI,PIPPIN,GRANNY_SMITH}
public enum Orange{NAVEL,TEMPLE,BLOOD}
```
它们就是通过公有的静态final域为每个枚举常量导出实例的类。
**使用构造器描述枚举类型**
```java
public enum Planet{
      MERCURY(3,5),
      MARS(4,6),
      EARTH(5,7);
      private final double mass;
      private final double radius;
      private final double surface;
      Planet(double mass,double radius){
              this.mass=mass;
              this.radius=radius;
              surface=G*mass/(radius*radius);
      }
}
public class Test{
      public static void main(String arg0){
              for(Planet:Planet.values()){
                    System.out.println(p);//返回MERCURY  ，MARS ，EARTH
            }
      }
}
```
注意Planet就像所有的枚举一样，它有一个静态的values方法，按照声明顺序返回它的值数组。

+ values()
静态方法，返回一个包含全部枚举值的数组
```java
Colors[] colors=Colors.values();
for(Color c:colors){
          System.out.println(c+",");
 }
```
+ toString()返回枚举常量的名称
```
Colors c=Color.RED;
System.out.println(c);//返回结果：RED
```


###Switch
```java
enum Signal{
      GREEN,YELLOW,RED
}
public class TrassicLight{
      Signal color=Signal.RED;
      public void change(){
                switch(color){
                      case RED:
                          color=Signal.GREEN;
                          break;
                      case YELLOW:
                          color=Signal.RED;
                          break;
                      case GREEN:
                          color=Signal.YELLOW;
                          break;
              }
      }
}
```
向枚举中添加新方法
如果打算自定义自己的方法，那么必须在enum实例系列中的最后添加一个分号。
```java
public enum Color{
        RED("红色",1),GREEN("绿色",2),BLANK("白色",3),YELLOW("黄色"，4);
        //成员变量
        private String name;
        private int index;
       //构造方法
      private Color(String name,int index){
            this.name=name;
            this.index=index;  
      }
      //普通方法
      pubilc static String getName(int index){
                for(Color c:Color.values()){
                          if(c.getIndex()==index){
                                  return c.name;
                          }
                }
      }
      //get set方法
      public String getName(){
                return name;
      }
      public void setName(String name){
              this.name=name;
      }
      public int getIndex(){
              return index;
      }
      public void setIndex(int index){
                  this.index=index;
       }
}
```
覆盖枚举方法
```java
public class Test{
        public enum Color{
                RED("红色",1),GREEN("绿色"，2),BLANK("白色",3),YELLO("黄色",4);
                //成员方法
                private String name;
                private int index;
                
                //构造方法
                private Color(String name,int index){
                          this.name=name;
                          this.index=index;
                }
                @Override
                public String toString(){
                        return this.index+"_"+this.name;
                }
         }
         public static void main(String[] args){
                  System.out.println(Color.RED.toString());  
        }
 }
```
实现接口
```java
public interface Behaviour{
	        void print();  
	        String getInfo();      
	}
	public enum Color implements Behaviour{
	        RED("红色",1),GREEN("绿色",2),BLANK("白色",3),YELLO("黄色",4);
	        //成员变量
	        private String name;
	        private int index;
	        //构造方法
	        private Color(String name,int index){
	              this.name=name;
	              this.index=index;
	        }
	        public String getInfo(){
	                return this.name;
	        }
	        public void print(){
	                  System.out.print(this.index+":"+this.name);
	         }
	}
	    
	
```
使用接口组织枚举
 ```java
public interface Food{
      enum Coffee implements Food{
              BLANK_COFFEE,DECAF_COFFEE,LATTE,CAPPUCCINO
      }
     enum Dessert implements Food{
              FRUIT,CAKE,CELATO
      }
}
```
