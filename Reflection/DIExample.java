package javaReflection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Retention(RetentionPolicy.RUNTIME)
@interface Inject {
}

class DIContainer {
    private final Map<Class<?>, Object> instances = new HashMap<>();

    public <T> T getInstance(Class<T> clazz) {
        try {
            if (instances.containsKey(clazz)) {
                return clazz.cast(instances.get(clazz));
            }

            T instance = clazz.getDeclaredConstructor().newInstance();
            instances.put(clazz, instance);

            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Inject.class)) {
                    Class<?> fieldType = field.getType();
                    Object dependency = getInstance(fieldType);
                    field.setAccessible(true);
                    field.set(instance, dependency);
                }
            }

            return instance;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

class Service {
    public void serve() {
        System.out.println("Service is serving...");
    }
}

class Client {
    @Inject
    private Service service;

    public void doWork() {
        service.serve();
    }
}

public class DIExample {
    public static void main(String[] args) {
        DIContainer container = new DIContainer();
        Client client = container.getInstance(Client.class);
        client.doWork();
    }
}

