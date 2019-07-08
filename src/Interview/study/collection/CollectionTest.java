package Interview.study.collection;

import org.junit.Test;

import java.util.*;

/** 一、集合框架的概述
 * 1.集合、数组都是对多个数组进行存储操作的结构，简称java容器
 *      说明：此时的存储，主要指的是内存层面的存储，不涉及到持久化的存储(.text,jpg,avi,数据库中的存储)
 *
 * 2.1 数组在存储多个数据方面的特点：
 *      (1)数组初始化以后，长度就确定了
 *      (2)数组声明的类型，就决定了进行元素初始化时的类型。如String[] arr;int[] arr1;Object[] arr2
 *
 * 2.2数组在存储多个数据方面的弊端
 *      (1)一旦初始化以后，其长度就不可修改
 *      (2)数组中提供的属性和方法少，不便于添加，删除，插入等操作，非常不方便，且效率不高。
 *      (3)获取数组中实际元素的个数，数组没有现成的属性或方法可用
 *      (4)数组存储的数组是有序的，可以重复的。对于无序、不可重复的需求，不能满足
 *
 *
 * 二、集合框架
 * |-------Collection接口：单列数据，定义了存取一组对象的方法的集合
 *        |-------List:元素有序，可重复的集合   –>“动态”数组
 *                  |-----ArrayList   LinkedList  Vector
 *        |-------Set：元素无序，不可重复的集合  -> 高中讲的“集合
 *                 |------HashSet  LinkedHashSet   TreeSet
 * |-------Map接口：双列数据，保存具有映射关系“key-value对”的集合 ->高中的函数
 *             |------HashMap  LinkedHashMap  TreeMap  HashTable  Properities
 *
 *
 * 三、Collection接口中的方法的使用
 *
 * @author yajie.fu
 * @create 2019-06-28 9:29
 */
public class CollectionTest {
    /*
    Collection接口中的方法的使用:大部分简单没写
     */
    @Test
    public void test1(){
        Collection coll = new ArrayList();
        coll.add("aaa");
        coll.add(123);
        coll.add('c');
        //集合---->数组
        Object[] array = coll.toArray();
        for(int i = 0; i <array.length;i++){
            System.out.println(array[i]);
        }

        //数组---->集合:调用Arraysd 类的静态方法asList()
        List<String> list = Arrays.asList(new String[]{"aa","bb","CC"});
        System.out.println(list);//[aa, bb, CC]

        List<int[]> arr1 = Arrays.asList(new int[]{123,456});
        System.out.println(arr1);//[[I@32a1bec0]

        List arr2 = Arrays.asList(123,456);
        System.out.println(arr2);//[123, 456]

        List arr3 = Arrays.asList(new Integer[]{123,456});
        System.out.println(arr3);//[123, 456]






    }
}
