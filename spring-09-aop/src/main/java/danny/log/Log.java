package danny.log;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class Log implements MethodBeforeAdvice {

    //method: 要執行的目標對象的方法
    //args: 參數
    //target : 目標

    @Override
    public void before(Method method, Object[] objects, Object target) throws Throwable {
        System.out.println(target.getClass().getName()+" 的 "+method.getName()+" 被執行了");
    }
}
