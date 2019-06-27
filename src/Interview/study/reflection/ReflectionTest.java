package Interview.study.reflection;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author yajie.fu
 * @create 2019-06-26 10:39
 */
public class ReflectionTest {
    // 反射之前，对于Person的操作
    @Test
    public void test1() {
        //1.创建Person类对象
        Person p1 = new Person("Tom", 12);

        //2.通过对象，调用其内部的属性、方法
        p1.age = 10;
        System.out.println(p1.toString());
        p1.show();

        //在Person类外部，不可以通过Person类的对象调用其内部私有结构
        // 比如：name,showNation()以及私有的构造器
    }


    // 反射之后，对于Person的操作
    @Test
    public void test2() throws Exception {
        Class clazz = Person.class;
        //1.通过反射，创建Person类对象
        Constructor cons = clazz.getConstructor(String.class, int.class);
        Object obj = cons.newInstance("Tom", 12);

        Person p = (Person) obj;

        System.out.println(p.toString());

        //2.通过对象，调用对象指定的属性、方法
        // 2.1调用属性
        Field age = clazz.getDeclaredField("age");
        age.set(p, 10);
        System.out.println(p.toString());
        // 2.2调用方法
        Method show = clazz.getDeclaredMethod("show");
        show.invoke(p);
        System.out.println("*************************************************************");
        // 3.通过反射，可以调用person的私有结构的。比如私有的构造器、方法和属性
        // 3.1调用私有的构造器
        Constructor cons1 = clazz.getDeclaredConstructor(String.class);
        cons1.setAccessible(true);
        Person p1 = (Person) cons1.newInstance("Jerry");
        System.out.println(p1);

        // 3.2调用私有的属性
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        name.set(p1, "HanMeimei");
        System.out.println(p1);

        // 3.3调用私有的方法
        Method showNation = clazz.getDeclaredMethod("showNation", String.class);

        showNation.setAccessible(true);
        String nation = (String) showNation.invoke(p1, "中国");//想到于p1.showNation("中国")
        System.out.println(nation);

        /*
         疑问1：通过直接new的方式或者反射的方式都可以调用公共的接口，开发中到底用哪个
                   建议：直接new的方式
               什么时候用反射的方式：反射的特征：动态性
                      编译的时候确定不下来用哪个对象。举例：登录或者注册，动态的。

        疑问2：反射机制与面向对象中的封装性是不是矛盾的？如何看待两个技术。
         答：不矛盾。封装性的意思是建议你调什么东西，反射是能不能
         */

        /*
        关于java.lang.Class类的理解
        1.	类的加载过程
            程序经过javac.exe命令以后，会生成一个或多个字节码文件(.class结尾)

            接着我们使用java.exe命令对某个字节码文件进行解释运行。相当于把某个字节码文件加载到内存中，此过程就成为类的加载，加载到内存中的类，我们就称为运行时类，此运行时类，就作为Class的一个实例。

        2.	换句话说，Class的实例就对应着一个运行时类。
        3.  加载到内存中的运行时类，会缓存一定时间。在此时间之内，我们可以通过不同的方式来获取此运行时类。
         */
    }

    // 获取Class的实例的方式（前三种方式需要掌握）
    @Test
    public void test3() throws ClassNotFoundException {
        // 方式1：调用运行时类的属性：.class
        Class clazz1 = Person.class;
        System.out.println(clazz1);

        // 方式2：通过运行时类的对象，调用getClass()
        Person p1 = new Person();
        Class clazz2 = p1.getClass();
        System.out.println(clazz2);

        // 方式3：调用Class的静态方法：forName(String classPath)
        Class clazz3 = Class.forName("Interview.study.reflection.Person");
        System.out.println(clazz3);

        // 方式4：(了解)通过类的加载器
        ClassLoader classLoader = this.getClass().getClassLoader();
        Class clazz4 = classLoader.loadClass("Interview.study.reflection.Person");
        System.out.println(clazz4);


        System.out.println(clazz1 == clazz2);//true
        System.out.println(clazz1 == clazz3);//true
        System.out.println(clazz1 == clazz4);//true
    }
}
