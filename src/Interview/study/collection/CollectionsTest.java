package Interview.study.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author yajie.fu
 * @create 2019-06-29 16:59
 */
    /*
    排序操作
        reverse(List)：反正List中元素的顺序
        shuffle(List)：对List集合元素进行随机排序
        sort(List)：根据元素的自然顺序对指定List元素集合按升序排序
        sort(List,Comparator)：根据指定的Comparator产生的顺序对List集合元素进行排序
        swap(List, int ,int)：将指定List集合中的i处和j处元素进行交换
     查找、替换
        Object max(Collection):根据元素的自然顺序，返回给定集合中的最大元素
        Object max(Collection,Comparator):根据Comparator指定的顺序，返回给定集合中的最大元素。
        Object min(Collection): 根据元素的自然顺序，返回给定集合中的最小元素
        Object min(Collection,Comparator): 根据Comparator指定的顺序，返回给定集合中的最小元素。
        Int frequency(Collection,Object)：返回指定集合中指定元素的出现次数
        Void copy(List dest, List src)：将src中的内容复制到dest中
        Boolean replaceAll(List list , Object oldVal, Object newVal)：使用新值替换List对象的所有旧值。


     */
public class CollectionsTest {
    //Collections.copy(List dest, List src)
    @Test
    public void test1() {
        List list = new ArrayList();
        list.add(123);
        list.add(12);
        list.add(23);
        list.add(-12);
        list.add(217);
        //报异常：IndexOutOfBoundsException
//        List dest = new ArrayList();
        //依旧报异常：IndexOutOfBoundsException
//        List dest = new ArrayList(list.size());//元素的个数

        //正确写法
        List dest = Arrays.asList(new Object[list.size()]);
        Collections.copy(dest,list);
        System.out.println(dest);
    }

    @Test
    public void test() {
        List list = new ArrayList();
        list.add(123);
        list.add(12);
        list.add(23);
        list.add(23);
        list.add(23);
        list.add(-12);
        list.add(217);
        System.out.println(list);
//        Collections.reverse(list);
//        Collections.shuffle(list);
//        Collections.sort(list);
        Collections.swap(list, 6, 5);
        System.out.println(list);
        int frequency = Collections.frequency(list, 23);
        System.out.println(frequency);
    }
}
