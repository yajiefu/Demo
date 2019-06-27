package Interview.study.reflection1;

import java.io.Serializable;

/**
 * @author yajie.fu
 * @create 2019-06-26 19:54
 */
public class Creature<T> implements Serializable {
    private char gender;
    public double weight;

    private void breath(){
        System.out.println("生物呼吸");
    }

    public void eat(){
        System.out.println("生物吃东西");
    }

}
