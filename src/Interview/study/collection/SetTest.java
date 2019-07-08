package Interview.study.collection;

import org.junit.Test;
import sun.net.idn.Punycode;

import java.util.*;

/**
 *
 *  * |-------Collection接口：单列数据，定义了存取一组对象的方法的集合
 *  *        |-------Set：元素无序，不可重复的集合  -> 高中讲的“集合
 *  *                 |------HashSet:作为Set接口的主要实现类；线程不安全；可以存储null值；
 *                           基于哈希表实现，支持快速查找，但不支持有序性操作。
 *                           并且失去了元素的插入顺序信息，也就是说使用 Iterator 遍历 HashSet 得到的结果是不确定的。
 *                         |------LinkedHashSet:作为HashSet的子类；遍历其内部数据时，可以按照添加的顺序遍历。
 *                                      具有 HashSet 的查找效率，且内部使用双向链表维护元素的插入顺序
 *                                      对于频繁的遍历操作，LinkedHashSet效率高于HashSet
 *                    |------TreeSet:可以按照添加对象的指定属性，进行排序。基于红黑树实现，支持有序性操作，例如根据一个范围查找元素的操作。
 *                                    但是查找效率不如 HashSet，HashSet 查找的时间复杂度为 O(1)，TreeSet 则为 O(logN)。
 *
 * 注意：1.Set接口中没有额外定义新的方法，使用的都是Collection中声明过的方法
 *       2.要求：向Set中添加的数据，其所在的类一定要重新hashCode()和equals()
 *         要求：重写的hashCode()和equals()尽可能保持一致性：相等的对象必须具有相等的散列值。
 *         重写两个方法的小技巧：对象中用作equals()方法的比较的Field，都应该用来计算hashcode值
 *
 *
 * @author yajie.fu
 * @create 2019-06-28 18:41
 */


public class SetTest {
    /*
    一、Set:存储无序的，不可重复的数据
     --以HashSet为例说明
     1.	无序性：不等于随机性。存储的数据在底层数组中并非按照数组索引的顺序添加，而是根据数据的哈希值。
     2.	不可重复性：保证添加的元素按照equals()，判断是，不能返回true,即：相同的元素只能添加一个。


     二、添加元素的过程：以HashSet为例
     我们向HashSet添加元素a，
     首先调用元素a所在类的hashCode()方法，计算元素a的哈希值，此哈希值接着通过某种算法计算出在HashSet底层数组中的存放位置（即为：索引地址）,判断数组此位置是否已经有元素。
     1.	如果此位置上没有其他元素，则元素a添加成功  情况1
     2.	如果此位置上有其他元素，或以链表形式存在多个元素，则比较元素a与元素b的hash值。
     3.	2.1 如果hash值不相同，则元素a添加成功。情况2
     2.2 如果hash值相同，进而调用元素a所在类的equals()方法：
     Equals()返回true，元素a添加失败
     Equals()返回false，元素a添加成功    情况3


     对于添加成功的情况2和3而言：元素a与已经存在指定索引位置上数据以链表的方式存储。
     Jdk 7:元素a放到数组中，指向原来的元素
     Jdk 8:原来的元素在数组中，指向元素a
     总结：七上八下

     HashSet:数组+链表
     */
    @Test
    public void test1(){
        Set set = new HashSet<>();
        set.add(123);
        set.add(456);
        set.add("AA");
        set.add("CC");
        set.add(124);
        set.add(new User("Tom",12));
        set.add(new User("Tom",12));
        set.add(123);
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    //LinkedHashSet作为HashSet的子类，再添加数据的同时，每个数据还维护了两个引用，记录此数据前一个数据和后一个数据。
    //优点：对于频繁的遍历操作，LinkedHashSet效率高于HashSet

    @Test
    public void test2(){
        Set set = new LinkedHashSet();
        set.add(123);
        set.add(456);
        set.add("AA");
        set.add("CC");
        set.add(124);
        set.add(new User("Tom",12));
        set.add(new User("Tom",12));
        set.add(123);
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }




    /**
     * TreeSet
     * 1.	向TreeSet中添加的数据，要求是相同类的对象
     * 2.	两种排序方式：自然排序(实现comparable接口)和定制排序
     * 3.	自然排序中，比较两个对象是否相同的标准为：compareTo()返回0，不再是equals()
     * 4.	定制排序中，比较两个对象是否相同的标准为：compare()返回0，不再是equals()
     */
    @Test
    public void test3(){
        Set set = new TreeSet();
        //举例1:
        //失败：不能添加不同类的对象
//        set.add(123);
//        set.add(345);
//        set.add("AA");

        //举例2
//        set.add(23);
//        set.add(12);
//        set.add(-32);
//        set.add(8);
//        Iterator iterator = set.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//        }
        //结果:排序了
        //-32
        //8
        //12
        //23

        //举例3
        set.add(new User("Tom",12));
        set.add(new User("Amy",29));
        set.add(new User("Mary",21));
        set.add(new User("Mary",33));

        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }


    @Test
    public void test4(){
        Comparator com = new Comparator() {
            //按照年龄从小到大排序
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof User && o2 instanceof User){
                    User u1 = (User) o1;
                    User u2 = (User) o2;
                    return Integer.compare(u1.getAge(),u2.getAge());
                }
                else {
                    throw  new RuntimeException("输入的数据类型不匹配");
                }
            }
        };
        TreeSet set = new TreeSet(com);

        set.add(new User("Tom",12));
        set.add(new User("Amy",29));
        set.add(new User("Mary",21));
        set.add(new User("Mary",33));

        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }



    }


}
