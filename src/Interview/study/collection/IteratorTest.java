package Interview.study.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 集合元素的遍历操作，使用迭代器Iterator接口
 * 1.内部方法：hasNext()和next()
 * 2.集合对象每次调用iterator方法都会得到一个全新的迭代器对象，默认游标都在集合的第一个元素之前
 *
 *
 * Iterator中的remove()方法
 * 1.Iterator可以删除集合的元素，但是时遍历过程中通过迭代器对象的remove方法，
 *   不是集合对象的remove方法
 * 2.如果还未调用next()或者在上一次第哦啊用next()方法之后已经调用了remove方法，
 *   再调用remove都会报IllegalStateException
 *
 *
 *   集合遍历的3种方式
 *   1.普通的for循环
 *   2.foreach循环
 *   3.Iterator
 * @author yajie.fu
 * @create 2019-06-28 10:39
 */
public class IteratorTest {
    @Test
    public void test1() {
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(3455);
        coll.add(false);
        coll.add("hello");

        Iterator iterator = coll.iterator();
        //方式1：
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        //报异常NoSuchElementException
//        System.out.println(iterator.next());
        //方式2：不推荐
//        for(int i = 0; i < coll.size(); i++){
//            System.out.println(iterator.next());
//        }

        //方式3：推荐
        //在调用it.next()方法之前必须要调用it.hasNext()进行检测。
        //若不调用，且下一条记录无效，直接调用it.next()方法会抛出NoSuchElementException异常
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    //测试Iterator中的remove()方法
    @Test
    public void test2() {
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(3455);
        coll.add(false);
        coll.add("hello");

        Iterator iterator = coll.iterator();
        //删除"hello"
        while (iterator.hasNext()){
//            iterator.remove();//报错IllegalStateException
            Object obj = iterator.next();
            if ("hello".equals(obj)){
                iterator.remove();
            }
        }

        //遍历
        Iterator iter = coll.iterator();
        while (iter.hasNext()){
            System.out.println(iter.next());
        }
        /*
        123
        3455
        false
         */

    }
}
