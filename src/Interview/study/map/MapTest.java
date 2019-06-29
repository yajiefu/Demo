package Interview.study.map;

import org.junit.Test;

import java.util.*;

/**
 * 一、Map的实现类的结构
 *   |------Map:双列数据，存储key-value对的数据   ---类似高中的函数：y=f(x)
 *             |-------HashMap:作为Map的主要实现类；线程不安全，效率高；存储null的key和value
 *                        |-----LinkedHashMap：保证在遍历map时，可以按照添加的顺序实现遍历
 *                                              原因：在原有的HashMap底层结构基础上，添加了一对指针，指向前一个和后一个元素
 *                                                    对于频繁的遍历操作，此类执行效率高于HashMap
 *             |-------TreeMap:保证按照添加的key-value对进行排序，实现排序遍历。此时考虑key的自然排序或定制排序
 *                             底层使用红黑树
 *             |-------HashTable:作为古老的实现类；线程安全，效率低，不能存储null的key和value
 *                    |-----Properties:常用来处理配置文件。key和value都是String类型
 *
 *  HashMap的底层：数组+链表（jdk7及之前）
 *                  数组+链表+红黑树（jdk 8）
 *
 *  面试题：
 *  1.HashMap的底层实现原理
 *  2.HashMap和HashTable的异同
 *  3.CurrentHashMap与HashMap的异同
 *
 * 二、Map结构的理解
 *    Map中的key：无序的、不可重复的，使用Set存储所有的key
 *    Map中的value：无序的，可重复的，使用 Collection存储所有的value
 *    一个键值对：key-value构成一个Entry对象
 *    Map中的entry：无序的、不可重复的，使用Set存储所有的entry
 *
 *
 * 三、HashMap的底层实现原理
 * 3.1 以jdk 7为例：
 *     HashMap map = new HashMap();
 *     在实例化以后，底层创建了长度为16的一维数组Entry[] table
 *     …可能已经执行多次put
 *     map.put(key1,value1);
 *     首先，调用key1所在类的hashcode()方法计算key1的哈希值，此哈希值经过某种算法计算以后，得到Entry数组中的存放位置。
 *        如果此位置上的数据为空，此时的key1-value1添加成功  ---情况1
 *        如果此位置上的数据不为空，（意味着此位置上存在一个或多个数据（以链表形式存在）），比较key1和已经存在的一个或多个数据的哈希值：
 *           如果key1的哈希值与已经存在的数据的哈希值都不相同，此时key1-value1添加成功；---情况2
 *           如果key1的哈希值和已经存在的某一个数据（key2-value2）的哈希值相同，继续比较：调用key1所在类的equals方法，比较：
 *               如果equals()返回false，此时key1-value1添加成功；---情况3
 *               如果equals()返回true，此时value1替换value2。
 *
 *
 *      补充：关于情况2和情况3：此时的key1-value1和原来的数据以链表的方式存储。
 * 在不断的添加过程中，会涉及到扩容问题，默认的扩容方式：扩容为原来容量的2倍，并将原有的数据复制过来
 *
 *
 * Jdk8 相较于jdk7在底层实现方面的不同
 * 1.	HashMap map = new HashMap();底层没有创建一个长度为16的数组
 * 2.	Jdk8 底层的数组是Node()，而非Entry[]
 * 3.	首次调用put()方法时，底层创建长度为16的数据
 * 4.	Jdk 7底层结构只有：：数组+链表。
 *      Jdk 8底层结构为：数组+链表+红黑树
 *   4.1形成链表时，七上八下（jdk7:新的元素指向旧的元素；jdk8:旧的元素指向新的元素）
 *   4.2当数组的某一个索引位置上的元素以链表形式存在的个数 > 8 且当前数组的长度>64时，此时此索引位置上的所有数据改为使用红黑树存储
 *
 *
 * DEFAULT_INITIAL_CAPACITY：HashMap的默认容量，1 << 4，16
 * MAXIMUM_CAPACITY：HashMap的最大支持容量，1 << 30，2的30次幂
 * DEFAULT_LOAD_FACTOR：HashMap的默认加载因子：0.75f。
 * TREEIFY_THRESHOLD：Bucket中链表长度大于该默认值时，转化为红黑树。8
 * UNTREEIFY_THRESHOLD：Bucket中红黑树存储的Node小于该默认值，转化为链表。6
 * MIN_TREEIFY_CAPACITY：桶中的Node被树化时最小的hash表容量，默认值为64。
 * （当桶中Node的数量大到需要变红黑树时，若hash表容量小于MIN_TREEIFY_CAPACITY时，
 *   此时应执行resize扩容操作这个MIN_TREEIFY_CAPACITY的值至少是TREEIFY_THRESHOLD的4倍）
 *
 * table：存储元素的数据，总是2的n次幂
 * entrySet:存储具体元素的值
 * size：HashMap中存储的键值对的数量
 * modCount:HashMap扩容和结构改变的次数
 * threshold：扩容的临界值，=容量*填充因子  16*0.75>=12
 * loadFactor:填充因子
 *
 *
 * 四、LinkedHashMap的底层实现原理(了解)
 *
 * static class Entry<K,V> extends HashMap.Node<K,V> {
 *         Entry<K,V> before, after;// 能够记录添加元素的前后顺序
 *         Entry(int hash, K key, V value, Node<K,V> next) {
 *             super(hash, key, value, next);
 *         }
 *     }
 *
 *
 * 五、Map中定义的方法
 *  添加删除修改操作
 *     Object put(Object key,Object value)
 *     void putAll(Map m)
 *     Object remove(Object key):移除指定key的键值对，并返回value
 *     void clear()
 *
 * 元素查询的操作
 *     Object get(Object key)
 *     boolean containsKey(Object key)
 *     boolean containsValue(Object value)
 *     int size()
 *     boolean isEmpty()
 *     boolean equals(Object obj)
 *
 * 元视图操作的方法
 * Set keySet()：返回所有key构成的set集合
 * Collection values():返回所有value构成的Collection集合
 * Set entrySet()：返回所有key-value对构成的Set集合
 *
 * 总结：添加，删除，修改，查询，长度，遍历
 *
 * @author yajie.fu
 * @create 2019-06-28 8:44
 */
public class MapTest {

    /*
     * 元视图操作的方法
     * Set keySet()：返回所有key构成的set集合
     * Collection values():返回所有value构成的Collection集合
     * Set entrySet()：返回所有key-value对构成的Set集合
     */

    @Test
    public void test3(){
        Map map = new HashMap();
        map.put("AA",123);
        map.put(21,123);
        map.put("BB",33);

        //遍历所有的key集：keySet
        Set set = map.keySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("****************************");
        //遍历所有的value集：values()
        Collection values = map.values();
        Iterator iterator1 = values.iterator();
        while(iterator1.hasNext()){
            System.out.println(iterator1.next());
        }
        System.out.println("****************************");

        //遍历所有的key-value集
        // 方式1：entrySet()
        Set set1 = map.entrySet();
        Iterator iterator2 = set1.iterator();
        while (iterator2.hasNext()){
            Object obj = iterator2.next();
            Map.Entry entry = (Map.Entry) obj;
            System.out.println(entry.getKey() + "---->" +entry.getValue());
        }
        //方式2：
        Set keySet = map.keySet();
        Iterator iterator3 = keySet.iterator();
        while (iterator3.hasNext()){
            Object key = iterator3.next();
            Object value = map.get(key);
            System.out.println(key + "=====" + value);
        }

    }

    @Test
    public void test2(){
        Map map = new HashMap();
        //添加
        map.put("AA",123);
        map.put("BB",33);
        map.put(21,123);
        //修改
        map.put("AA",98);
        System.out.println(map);

        Map map1 = new HashMap();
        map1.put("CC",123);
        map1.put("DD",123);
        map.putAll(map1);
        System.out.println(map);

        //remove(Object key)
        Object value = map.remove("CC");
        System.out.println(value);
        System.out.println(map);
        //clear()
        map.clear();//与map=null不同
        System.out.println(map.size());//0
        System.out.println(map);//{}



    }
    @Test
    public void test1(){
        Map map = new HashMap();
        map = new LinkedHashMap();
        map.put(123,"aa");
        map.put(21,"ee");
        map.put(321,"cc");
        System.out.println(map);

    }
}
