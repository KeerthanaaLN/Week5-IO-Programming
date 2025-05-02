package javaReflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MethodExecutionTimer {

    public interface Task {
        void performTask();
    }

    public static class TaskImpl implements Task {
        public void performTask() {
            try {
                Thread.sleep(1000);  
                System.out.println("Task performed!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class TimingInvocationHandler implements InvocationHandler {
        private final Object target;

        public TimingInvocationHandler(Object target) {
            this.target = target;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            long startTime = System.nanoTime();
            Object result = method.invoke(target, args);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            System.out.println("Method " + method.getName() + " executed in " + duration + " nanoseconds.");
            return result;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T createProxy(Class<T> interfaceClass, T target) {
        return (T) Proxy.newProxyInstance(
                interfaceClass.getClassLoader(),
                new Class<?>[]{interfaceClass},
                new TimingInvocationHandler(target)
        );
    }

    public static void main(String[] args) {
        Task task = new TaskImpl();
        Task proxyTask = createProxy(Task.class, task);
        proxyTask.performTask();
    }
}

