对于静态变量、静态初始化块、变量、初始化块、构造器，它们的初始化顺序以此是 （静态变量、静态初始化块）>（变量、初始化块）>构造器。
```
public class InitialOrderTest {               
    // 静态变量          
    public static String staticField = "静态变量";          
    // 变量          
    public String field = "变量";                  
    // 静态初始化块        
    static {          
        System.out.println(staticField);  
        System.out.println("静态初始化块");  
    }                  
    // 初始化块              
    {          
        System.out.println(field);  
        System.out.println("初始化块");        
    }                  
    // 构造器          
    public InitialOrderTest() {  
        System.out.println("构造器");     
    }                  
    public static void main(String[] args) {  
        new InitialOrderTest();              
    }          
}   
静态变量  
静态初始化块 
变量  
初始化块 
构造器 
class Parent {              
    // 静态变量          
    public static String p_StaticField = "父类--静态变量";              
    // 变量          
    public String p_Field = "父类--变量";                  
    // 静态初始化块              
    static {          
        System.out.println(p_StaticField);                  
        System.out.println("父类--静态初始化块");              
    }                  
    // 初始化块              
    {          
        System.out.println(p_Field);          
        System.out.println("父类--初始化块");              
    }                  
    // 构造器          
    public Parent() {          
        System.out.println("父类--构造器");              
    }         
}                  
public class SubClass extends Parent {              
    // 静态变量          
    public static String s_StaticField = "子类--静态变量";              
    // 变量          
    public String s_Field = "子类--变量";              
    // 静态初始化块              
    static {          
        System.out.println(s_StaticField);                  
        System.out.println("子类--静态初始化块");          
    }          
    // 初始化块  http://ini.iteye.com/  
    {          
        System.out.println(s_Field);          
        System.out.println("子类--初始化块");              
    }                  
    // 构造器          
    public SubClass() {          
        System.out.println("子类--构造器");              
    }                  
    // 程序入口          
    public static void main(String[] args) {                  
        new SubClass();             
    }          
}  
父类--静态变量  
父类--静态初始化块  
子类--静态变量  
子类--静态初始化块  
父类--变量  
父类--初始化块  
父类--构造器  
子类--变量  
子类--初始化块  
子类--构造器      
```
面试题：
```
public class Test1 {  
  
    public static int k = 0;  
    public static Test1 t1 = new Test1("t1");  
    public static Test1 t2 = new Test1("t2");  
    public static int i = print("i");  
    public static int n = 99;  
    public int j = print("j");  
    {  
        print("构造块");  
    }  
      
    static{  
        print("静态块");  
    }  
  
    public Test1(String str){  
        System.out.println((++k)+":"+str+"    i="+i+"    n="+n);  
        ++i;++n;  
    }  
      
    public static int print(String str){  
        System.out.println((++k)+":"+str+"    i="+i+"    n="+n);  
        ++n;  
        return ++i;  
    }  
      
    public static void main(String[] args) {  
        // TODO Auto-generated method stub  
        Test1 t = new Test1("init");  
    }  
  
}  运行结果：

1:j    i=0    n=0
2:构造块    i=1    n=1
3:t1    i=2    n=2
4:j    i=3    n=3
5:构造块    i=4    n=4
6:t2    i=5    n=5
7:i    i=6    n=6
8:静态块    i=7    n=99
9:j    i=8    n=100
10:构造块    i=9    n=101
11:init    i=10    n=102
```
