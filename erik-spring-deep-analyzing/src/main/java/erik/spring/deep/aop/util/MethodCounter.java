package erik.spring.deep.aop.util;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MethodCounter implements Serializable {

    private Map<String, Integer> methodNameToCounterMap =
            new ConcurrentHashMap<>();

    private AtomicInteger allCounter;

    public void count(String methodName) {

        Integer count = methodNameToCounterMap.get(methodName);

        methodNameToCounterMap.computeIfPresent(methodName, (key, oldValue) -> oldValue++);

        allCounter.getAndIncrement();
    }

    public void count(Method method){
        count(method.getName());
    }

    public int getCalls(String methodName){

        Integer count = methodNameToCounterMap.get(methodName);
        return count==null?0:count;

    }



}
