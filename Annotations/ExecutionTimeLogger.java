package Annotations_Reflection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@interface LogExecutionTime {
}

class PerformanceTester {
    @LogExecutionTime
    public void taskOne() throws InterruptedException {
        Thread.sleep(100); 
    }

    @LogExecutionTime
    public void taskTwo() throws InterruptedException {
        Thread.sleep(200); 
    }

    public void taskWithoutLogging() throws InterruptedException {
        Thread.sleep(50);
    }
}

public class ExecutionTimeLogger {
    public static void main(String[] args) throws Exception {
        PerformanceTester tester = new PerformanceTester();
        Method[] methods = PerformanceTester.class.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(LogExecutionTime.class)) {
                long start = System.nanoTime();
                method.invoke(tester);
                long end = System.nanoTime();
                long duration = end - start;
                System.out.println("Method: " + method.getName() + " executed in " + duration / 1_000_000.0 + " ms");
            }
        }
    }
}

