package Interview.study.exer;
import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * 练习对集合中的元素进行排序(还未使用泛型)
 *
 * 题目：定义一个Employee类：name age birthday.其中birthday为MyDate类的对象。
 *       MyDate类：year month day
 *
 *       创建该类的5个对象，并把这些对象放入TreeSet集合中，分别按照以下两种方式对集合中的元素进行排序，并遍历输出：
 *       1.使Employee实现Comparable接口，并按name排序。
 *       2.创建TreeSet时传入Comparator对象，按生日日期的先后排序。
 *
 * @author yajie.fu
 * @create 2019-06-28 21:58
 */
public class EmployeeTest {

    //问题2：按生日日期的先后排序,从大到小.即数字从小到大，升序
    //则b1.getYear()->b2.getYear()返回正数
    @Test
    public void test2(){

        TreeSet set = new TreeSet(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Employee && o2 instanceof Employee){
                    Employee e1 = (Employee) o1;
                    Employee e2 = (Employee) o2;


                    MyDate b1 = e1.getBirthday();
                    MyDate b2 = e2.getBirthday();

                    //方式2
                    return b1.compareTo(b2);
                    //方式1
                    /*
//                    int minusYear = Integer.compare(b1.getYear(),b2.getYear());
                    int minusYear = b1.getYear()- b2.getYear();

                    if (minusYear != 0){
//                        System.out.println(b1.getYear() + "\t" +b2.getYear() +"\t"+minusYear);
                        return minusYear;
                    }
//                    int minusMonth = Integer.compare(b1.getMonth(),b2.getMonth());
                    int minusMonth =b1.getMonth() - b2.getMonth();

                    if (minusMonth != 0){
//                        System.out.println(b1.getMonth() + "\t" +b2.getMonth() +"\t"+minusMonth);
                        return minusMonth;

                    }
//                    System.out.println(b1.getDay() + "\t" +b2.getDay() +"\t"+(b1.getDay()-b2.getDay()));
                    return b1.getDay()-b2.getDay();
                    */
                }
                throw new RuntimeException("输入类型不匹配");
            }
        });
        Employee e1 = new Employee("Amy", 25, new MyDate(1994, 3, 24));
        Employee e2 = new Employee("Mary", 29, new MyDate(1994, 4, 22));
        Employee e3 = new Employee("Tom", 12, new MyDate(2007, 1, 19));
        Employee e4 = new Employee("Eric", 23, new MyDate(1996, 11, 9));


        set.add(e1);
        set.add(e2);
        set.add(e3);
        set.add(e4);

        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    //自然排序
    @Test
    public void test1(){


        TreeSet set = new TreeSet();
        Employee e1 = new Employee("Amy", 25, new MyDate(1994, 3, 24));
        Employee e2 = new Employee("Mary", 29, new MyDate(1990, 4, 22));
        Employee e3 = new Employee("Tom", 12, new MyDate(2007, 1, 19));
        Employee e4 = new Employee("Eric", 12, new MyDate(1994, 11, 9));


        set.add(e1);
        set.add(e2);
        set.add(e3);
        set.add(e4);

        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }


    }
}
