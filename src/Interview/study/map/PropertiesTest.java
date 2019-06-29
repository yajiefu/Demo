package Interview.study.map;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author yajie.fu
 * @create 2019-06-29 16:28
 */
public class PropertiesTest {
    //是HashTable的子类，常用来处理配置文件
    //由于配置文件里的key和value都是字符串类型，所以Properties里的key和value都是String类型。

    public static void main(String[] args) throws Exception {
        FileInputStream fis = null;
        try {
            Properties pros = new Properties();
            //加载流对应的文件
            fis = new FileInputStream("jdbc.properties");
            pros.load(fis);

            String name = pros.getProperty("user");
            String password = pros.getProperty("password");

            System.out.println("name="+name+"\tpassword="+password);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fis.close();
        }

    }
}
