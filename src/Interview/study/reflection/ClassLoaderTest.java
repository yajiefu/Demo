package Interview.study.reflection;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/** 了解类的加载器
 * @author yajie.fu
 * @create 2019-06-26 15:22
 */
public class ClassLoaderTest {
    @Test
    public void test1(){
        // 对于自定义类，使用系统类加载器AppClassLoader进行加载
        ClassLoader classLoader1 = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader1);//sun.misc.Launcher$AppClassLoader@18b4aac2

        //调用AppClassLoader的getParent()：获取扩展类加载器ExtClassLoader
        ClassLoader classLoader2 = classLoader1.getParent();
        System.out.println(classLoader2);//sun.misc.Launcher$ExtClassLoader@30f39991

        //调用ExtClassLoader的getParent()：无法获取引导类加载器Bootstrap Loader
        //Bootstrap Loader主要负责加载java的核心类库，无法加载自定义类。
        ClassLoader classLoader3 = classLoader2.getParent();
        System.out.println(classLoader3);//null

        ClassLoader classLoader4 = String.class.getClassLoader();
        System.out.println(classLoader4);//null
    }


    /*
    Properties:用来读取配置文件
     */
    @Test
    public void test2() throws Exception{

        Properties pros = new Properties();
        //读取配置文件方式1：
        //此时的文件默认在当前的module下
//        FileInputStream fis = new FileInputStream("jdbc.properties");
        FileInputStream fis = new FileInputStream("src\\jdbc1.properties");

        pros.load(fis);

        //读取配置文件方式2：使用类加载器
        //此时的文件默认在当前的module的src下
//        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
//        InputStream is = classLoader.getResourceAsStream("jdbc1.properties");
//        pros.load(is);

        String user = pros.getProperty("user");
        String password = pros.getProperty("password");

        System.out.println("user="+user+"\npassword="+password);

    }
}
