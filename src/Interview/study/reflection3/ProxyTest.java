package Interview.study.reflection3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理的举例
 * @author yajie.fu
 * @create 2019-06-27 14:32
 */

interface Human{
    String getBelief();

    void eat(String food);
}

// 被代理类
class SuperMan implements Human{

    @Override
    public String getBelief() {
        return "I believe I can fly!";
    }

    @Override
    public void eat(String food) {
        System.out.println("我喜欢吃"+food);
    }
}
/*
要向实现动态代理，需要解决的问题？
问题1：如何根据加载到内存总的被代理类，动态的创建一个代理类机器对象
问题2：当通过代理类的对象调用方法时，如果动态的去调用被代理类中的同名方法。
 */

class ProxyFactory{
    //调用此方法，返回一个代理类的对象。解决问题1
    public static Object getProxyInstance(Object obj){
        MyInvocationHandler handler = new MyInvocationHandler();

        handler.bind(obj);
        return  Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),handler);
    }
}


 class MyInvocationHandler implements InvocationHandler{

    private Object obj ;//需要使用被代理类的对象进行赋值
     public void bind(Object obj){
         this.obj = obj;
     }
    //当我们通过代理类的对象，调用方法o，就会自动的调用如下的方法：invoke()
   //将被代理类要执行的方法o的功能就声明在invoke()中
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
         //method:即为代理类对象调用的对象，此方法也就座位了被代理类对象要调用的方法
        //obj：被代理类的对象
        Object returnValue = method.invoke(obj,args);
        //上述方法的返回值就作为当前类中的invoke()的返回值
        return returnValue;
    }
}

public class ProxyTest {

    public static void main(String[] args) {
        SuperMan superMan = new SuperMan();
        //proxyInstance:代理类的对象
        Human proxyInstance = (Human) ProxyFactory.getProxyInstance(superMan);
        String belief = proxyInstance.getBelief();
        System.out.println(belief);
        proxyInstance.eat("四川麻辣烫");

        System.out.println("**************************");
        NikeClothFactoty nikeClothFactoty = new NikeClothFactoty();
        ClothFactory proxyClothFactory = (ClothFactory) ProxyFactory.getProxyInstance(nikeClothFactoty);

        proxyClothFactory.produceCloth();
    }
}