package danny.log;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class AfterLog implements AfterReturningAdvice {

    //returnValue:返回值
    //method
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("執行了" + method.getName()+" 方法,返回結果為:"+returnValue);
    }
}
