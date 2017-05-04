一、序列化和反序列化的概念
+ 把对象转化为字节序列的过程称为序列化.
+ 把字节序列恢复为对象的过程称为对象的序列化

对象的序列化主要有两种用途
1) 把对象的字节序列永久的保存在硬盘上，通常存放在一个文件中（数据的持久化）
2)在网络上传送对象的字节序列（对象数据的远程通信）


**使用Java对象序列化，在保存对象时，会把其状态保存为一组字节，在未来，再将这些字节组装成对象。必须注意地是，对象序列化保存的是对象的"状态"，即它的成员变量。由此可知，对象序列化不会关注类中的静态变量。**

当某个字段被声明为transient后，默认序列化机制就会忽略该字段。

二、JDK类库中序列化API
　java.io.ObjectOutputStream代表对象输出流，它的writeObject(Object obj)方法可对参数指定的obj对象序列化，把得到的字节序列写到一个目标输出流中

 java.io.ObjectInputStream代表对象输入流，它的readObject()方法从一个源输入流中读取字节序列，在把他们反序列为一个对象，并将其返回。

　对象序列化包括如下步骤：　　
1） 创建一个对象输出流，它可以包装一个其他类型的目标输出流，如文件输出流；　
2） 通过对象输出流的writeObject()方法写对象。　　

对象反序列化的步骤如下：　　
1） 创建一个对象输入流，它可以包装一个其他类型的源输入流，如文件输入流；　　
2） 通过对象输入流的readObject()方法读取对象。

**对象序列化和反序列范例：**
**　　定义一个Person类，实现Serializable接口**

```
/**
 * <p>ClassName: Person<p>
 * <p>Description:测试对象序列化和反序列化<p>
 * @author hansheng
 * @version 1.0 V
 * @createTime 2016-6-9 下午02:33:25
 */
public class Person implements Serializable {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = -5809782578272943999L;
    private int age;
    private String name;
    private String sex;

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
```
**序列化和反序列化Person类对象**
```
/**
 * <p>ClassName: TestObjSerializeAndDeserialize<p>
 * <p>Description: 测试对象的序列化和反序列<p>
 * @author hansheng
 * @version 1.0 V
 * @createTime 2016-6-9 下午03:17:25
 */
public class TestObjSerializeAndDeserialize {

    public static void main(String[] args) throws Exception {
        SerializePerson();//序列化Person对象
        Person p = DeserializePerson();//反序列Perons对象
        System.out.println(MessageFormat.format("name={0},age={1},sex={2}",
                                                 p.getName(), p.getAge(), p.getSex()));
    }
    
    /**
     * MethodName: SerializePerson 
     * Description: 序列化Person对象
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static void SerializePerson() throws FileNotFoundException,
            IOException {
        Person person = new Person();
        person.setName("gacl");
        person.setAge(25);
        person.setSex("男");
        // ObjectOutputStream 对象输出流，将Person对象存储到E盘的Person.txt文件中，完成对Person对象的序列化操作
        ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(
                new File("E:/Person.txt")));
        oo.writeObject(person);
        System.out.println("Person对象序列化成功！");
        oo.close();
    }

    /**
     * MethodName: DeserializePerson 
     * Description: 反序列Perons对象
     * @author xudp
     * @return
     * @throws Exception
     * @throws IOException
     */
    private static Person DeserializePerson() throws Exception, IOException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                new File("E:/Person.txt")));
        Person person = (Person) ois.readObject();
        System.out.println("Person对象反序列化成功！");
        return person;
    }

}
```
三、serialVersionUID的作用
　s​e​r​i​a​l​V​e​r​s​i​o​n​U​I​D​:​ ​字​面​意​思​上​是​序​列​化​的​版​本​号​，凡是实现Serializable接口的类都有一个表示序列化版本标识符的静态变量.
+ 比如你修改了Person类，在反序列时，如果没有serialVersionUID,则会报错。

**Externalizable接口**
无论是使用transient关键字，还是使用writeObject()和readObject()方法，其实都是基于Serializable接口的序列化。JDK中提供了另一个序列化接口--Externalizable，使用该接口之后，之前基于Serializable接口的序列化机制就将失效。此时将Person类作如下修改，
```
public class Person implements Externalizable {  
 
    private String name = null;  
 
    transient private Integer age = null;  
 
    private Gender gender = null;  
 
    public Person() {  
        System.out.println("none-arg constructor");  
    }  
 
    public Person(String name, Integer age, Gender gender) {  
        System.out.println("arg constructor");  
        this.name = name;  
        this.age = age;  
        this.gender = gender;  
    }  
 
    private void writeObject(ObjectOutputStream out) throws IOException {  
        out.defaultWriteObject();  
        out.writeInt(age);  
    }  
 
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {  
        in.defaultReadObject();  
        age = in.readInt();  
    }  
 
    @Override 
    public void writeExternal(ObjectOutput out) throws IOException {  
 
    }  
 
    @Override 
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {  
 
    }  
    ...  
} 
//运行结果
arg constructor  
none-arg constructor  
[null, null, null] 
```
从该结果，一方面，可以看出Person对象中任何一个字段都没有被序列化。另一方面，如果细心的话，还可以发现这此次序列化过程调用了Person类的无参构造器。
Externalizable继承于Serializable，当使用该接口时，序列化的细节需要由程序员去完成。如上所示的代码，**由于writeExternal()与readExternal()方法未作任何处理，那么该序列化行为将不会保存/读取任何一个字段。**这也就是为什么输出结果中所有字段的值均为空。
另外，使用Externalizable进行序列化时，当读取对象时，会调用被序列化类的无参构造器去创建一个新的对象，然后再将被保存对象的字段的值分别填充到新对象中。这就是为什么在此次序列化过程中Person类的无参构造器会被调用。由于这个原因，**实现Externalizable接口的类必须要提供一个无参的构造器**，且它的访问权限为public。
```
public class Person implements Externalizable {  
 
    private String name = null;  
 
    transient private Integer age = null;  
 
    private Gender gender = null;  
     //提供无参构造函数
    public Person() {  
        System.out.println("none-arg constructor");  
    }  
 
    public Person(String name, Integer age, Gender gender) {  
        System.out.println("arg constructor");  
        this.name = name;  
        this.age = age;  
        this.gender = gender;  
    }  
 
    private void writeObject(ObjectOutputStream out) throws IOException {  
        out.defaultWriteObject();  
        out.writeInt(age);  
    }  
 
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {  
        in.defaultReadObject();  
        age = in.readInt();  
    }  
 
    @Override 
    public void writeExternal(ObjectOutput out) throws IOException {  
        out.writeObject(name);  
        out.writeInt(age);  
    }  
 
    @Override 
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {  
        name = (String) in.readObject();  
        age = in.readInt();  
    }  
    ...  
}
public class SimpleSerial {  
 
    public static void main(String[] args) throws Exception {  
        File file = new File("person.out");  
 
        ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(file));  
        Person person = new Person("John", 101, Gender.MALE);  
        oout.writeObject(person);  
        oout.close();  
 
        ObjectInputStream oin = new ObjectInputStream(new FileInputStream(file));  
        Object newPerson = oin.readObject(); // 没有强制转换到Person类型  
        oin.close();  
        System.out.println(newPerson);  
    }  
}  
```
