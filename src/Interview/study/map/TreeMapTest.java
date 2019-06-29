package Interview.study.map;

import Interview.study.collection.User;
import org.junit.Test;

import java.util.*;

/**
 *
 * 向TreeMap中添加key-value，要求必须是同一个类创建的对象
 * 因为要按照key进行排序：自然排序  定制排序
 * @author yajie.fu
 * @create 2019-06-29 16:03
 */
public class TreeMapTest {
    //自然排序
    @Test
    public void test1(){
        TreeMap map = new TreeMap();
        User u1 = new User("Tom",23);
        User u2 = new User("Mary",21);
        User u3 = new User("June",22);
        User u4 = new User("Amy",20);
        map.put(u1,89);
        map.put(u2,78);
        map.put(u3,78);
        map.put(u4,23);

        Set set1 = map.entrySet();
        Iterator iterator2 = set1.iterator();
        while (iterator2.hasNext()){
            Object obj = iterator2.next();
            Map.Entry entry = (Map.Entry) obj;
            System.out.println(entry.getKey() + "---->" +entry.getValue());
        }
    }

    //定制排序：按照年龄排序,从小到大
    @Test
    public void test2(){
        TreeMap map = new TreeMap(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof User && o2 instanceof User){
                    User u1 = (User) o1;
                    User u2 = (User) o2;
                    return Integer.compare(u1.getAge(), u2.getAge());
                }
                throw  new RuntimeException("输入的类型不匹配");
            }
        });
        User u1 = new User("Tom",23);
        User u2 = new User("Mary",21);
        User u3 = new User("June",22);
        User u4 = new User("Amy",20);
        map.put(u1,89);
        map.put(u2,78);
        map.put(u3,78);
        map.put(u4,23);

        Set set1 = map.entrySet();
        Iterator iterator2 = set1.iterator();
        while (iterator2.hasNext()){
            Object obj = iterator2.next();
            Map.Entry entry = (Map.Entry) obj;
            System.out.println(entry.getKey() + "---->" +entry.getValue());
        }



    }
}
