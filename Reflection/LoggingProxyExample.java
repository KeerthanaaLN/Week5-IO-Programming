package javaReflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LoggingProxyExample {

    public interface Greeting {
        void sayHello(String name);
    }

    public static class GreetingImpl implements Greeting {
        public void sayHello(String name) {
            System.out.println("Hello, " + name);
        }
    }

    public static class LoggingInvocationHandler implements InvocationHandler {
        private final Object target;

        public LoggingInvocationHandler(Object target) {
            this.target = target;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("Invoking method: " + method.getName());
            return method.invoke(target, args);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T createProxy(Class<T> interfaceClass, T target) {
        return (T) Proxy.newProxyInstance(
                interfaceClass.getClassLoader(),
                new Class<?>[]{interfaceClass},
                new LoggingInvocationHandler(target)
        );
    }

    public static void main(String[] args) {
        Greeting realGreeting = new GreetingImpl();
        Greeting proxyGreeting = createProxy(Greeting.class, realGreeting);
        proxyGreeting.sayHello("Alice");
    }
}
