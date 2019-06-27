package Interview.study.reflection2;

import Interview.study.reflection1.Person;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 调用运行时类中指定的结构：属性、方法、构造器
 * @author yajie.fu
 * @create 2019-06-26 22:00
 */
public class ReflectionTest {
    @Test
    public void testField() throws Exception{
        Class clazz = Person.class;

        //创建运行时类的对象
        Person p = (Person) clazz.newInstance();

        //获取指定的属性:要求运行类中属性声明为public
        Field id = clazz.getField("id");

        //设置当前属性的值
        //set()：参数1：指明设置哪个对象的属性；参数2：将此属性设置为多少
        id.set(p, 1001);

        //获取当前属性的值
        //get()：参数：获取哪个对象的当前属性
        int  pId = (int) id.get(p);
        System.out.println(pId);
    }

    /*
    如何操作运行时类中指定的属性——需要掌握
     */
    @Test
    public void testField1() throws Exception{
        Class clazz = Person.class;

        //创建运行时类的对象
        Person p = (Person) clazz.newInstance();

        //1.getDeclaredField(String fieldName):获取运行时类中指定变量名的属性
        Field name = clazz.getDeclaredField("name");

        //2.保证当前属性是可访问的
        name.setAccessible(true);

        //3.获取、设置指定对象的此属性的值
        name.set(p,"Tom");
        String pName = (String) name.get(p);
        System.out.println(pName);
    }

    /*
   如何操作运行时类中指定的方法——需要掌握
    */
    @Test
    public void testMethod() throws Exception{
        Class clazz = Person.class;

        //创建运行时类的对象
        Person p = (Person) clazz.newInstance();

        //1.获取指定的某个方法
        //getDeclaredMethod():参数1：指明获取方法的名称 参数2：指明获取的方法的形参列表
        Method show = clazz.getDeclaredMethod("show", String.class);

        //2.保证当前方法是可访问的
        show.setAccessible(true);
        //3.调用方法的invoke()：参数1：方法的调用者  参数2：给方法形参赋值的实参
        //invoke()的返回值即为对应类中调用方法的返回值

         Object returnValue =  show.invoke(p,"CHN");
        System.out.println(returnValue);

        System.out.println("**************************如何调用静态方法***********************8");

        //  private static void showDesc()
        Method showDesc = clazz.getDeclaredMethod("showDesc");
        showDesc.setAccessible(true);
        //如果调用的运行时类中的方法没有返回值，则此invoke()返回null
//        Object returnVal = showDesc.invoke(Person.class);
        Object returnVal = showDesc.invoke(null);
        System.out.println(returnVal);//null
    }

    /*
 如何操作运行时类中指定的构造器
  */
    @Test
    public void testConstructor() throws Exception{
        Class clazz = Person.class;


        //创建运行时类的对象
        Person p = (Person) clazz.newInstance();

        //1.获取指定的构造器
        // private Person(String name)
        Constructor constructor = clazz.getDeclaredConstructor(String.class);
        //2.保证此构造器是可访问的
        constructor.setAccessible(true);
        //3.调用此构造器创建运行时类的对象
        Person per = (Person) constructor.newInstance("Tom");
        System.out.println(per.toString());
    }
}
