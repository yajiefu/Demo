package Interview.study.collection;

import org.junit.Test;

import java.util.*;

/**
 *
 *  1.List的框架结构
 *  |-------Collection接口：单列数据，定义了存取一组对象的方法的集合
 *        |-------List:元素有序，可重复的集合   –>“动态”数组
 *                  |-----ArrayList:作为List的主要实现类；线程不安全，效率高；底层使用Object[] elementData
 *                                  基于动态数组实现，支持随机访问。
 *                  |-----LinkedList:对于频繁的插入删除等操作，使用此类效率比ArrayList高；
 *                                   底层使用双向链表存储，不能随机访问，只能顺序访问
 *                  |-----Vector：作为List接口的古老实现类；线程安全，效率低；底层使用Object[] elementData存储
 *
 *   2.ArrayList源码分析：
 *      2.1jdk 7情况下
 *      //底层创建了长度为10的Object[]数组elementData
 *      ArrayList list = new ArrayList();
 *      list.add(123);//elementData[0]=new Integer(123)
 *      ...
 *      list.add(11)
 *      //如果此次的添加导致底层elementData数组容量不足，则扩容
 *      //默认情况下，扩容为原来容量的1.5倍，同时需要将原有数组中的数据复制到新的数组中。
 *
 *      结论：建议开发中国使用带参数的构造器：ArrayList list = new ArrayList(int capacity);
 *
 *      2.2jdk 8中ArrayList的变化
 *        //底层Object[]elementData初始化为{},并没有创建长度为10的数组
 *        ArrayList list = new ArrayList();
 *        list.add(123);//第一次调用add()时，底层才创建了长度10的数组，并将数组123添加到elementData中
 *        后续的添加和扩容操作与jdk7无异
 *
 *     2.3 小结
 *        jdk 7中的ArrayList的对象的创建类似于单例的饿汉式，
 *        而jdk 8中的创建类似于单例的懒汉式，延迟了数组的创建，节省内存。
 *
 *
 *   3.LinkedList源码分析
 *      LinkedList list = new LinkedList();//内部声明了Node类型的first和last属性，默认值为null
 *      list.add(123);//将123封装到Node中，创建了Node对象
 *
 *      其中，Node定义为：提醒了LinkedList的双向链表的说法
 *      private static class Node<E> {
 *         E item;
 *         Node<E> next;
 *         Node<E> prev;
 *
 *         Node(Node<E> prev, E element, Node<E> next) {
 *             this.item = element;
 *             this.next = next;
 *             this.prev = prev;
 *         }
 *     }
 *
 *
 *
 *  4.Vector源码分析:jdk7 jdk8中的Vector()构造器创建对象时，底层都创建了长度为100的数组；在扩容方面默认扩容为原来数组长度的2倍。
 *
 *  5.List接口中的常用方法
 *     增：add(Object obj)
 *     删:remove(int index)/remove(Object obj)
 *     改:set(int index, Object ele)
 *     查；get(int index)
 *     插: add(int index, Object ele)
 *     长度:size()
 *     遍历: 1.Iterator 2. for 3.foreach
 *
 *     另外： int indexOf(Object obj):返回obj在集合中首次出现的位置
 *            List subList(int fromIndex, int toIndex):返回子集合，左闭右开
 *
 * @author yajie.fu
 * @create 2019-06-28 11:24
 */
public class ListTest {
    //常用方法没有写
    /**
     * 区分list中remove(int index)和remove(Object obj
     */
    @Test
    public void testListRemove(){
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
//        updateList(list);
        System.out.println(list);//[1, 2]  [1, 3]

        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        ListIterator listIterator = list.listIterator();

        System.out.println(listIterator.previousIndex());
    }
    public void updateList(List list){
//        list.remove(2);
        list.remove(new Integer(2));

    }







}
