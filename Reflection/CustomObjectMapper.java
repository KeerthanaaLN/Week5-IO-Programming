package javaReflection;

import java.lang.reflect.Field;
import java.util.Map;

public class CustomObjectMapper {

    public static <T> T toObject(Class<T> clazz, Map<String, Object> properties) {
        try {
            T instance = clazz.getDeclaredConstructor().newInstance();
            for (Map.Entry<String, Object> entry : properties.entrySet()) {
                String fieldName = entry.getKey();
                Object fieldValue = entry.getValue();
                try {
                    Field field = clazz.getDeclaredField(fieldName);
                    field.setAccessible(true);
                    field.set(instance, fieldValue);
                } catch (NoSuchFieldException e) {
                    Field field = findFieldInSuperclass(clazz, fieldName);
                    if (field != null) {
                        field.setAccessible(true);
                        field.set(instance, fieldValue);
                    }
                }
            }
            return instance;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Field findFieldInSuperclass(Class<?> clazz, String fieldName) {
        Class<?> superclass = clazz.getSuperclass();
        while (superclass != null) {
            try {
                return superclass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                superclass = superclass.getSuperclass();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Map<String, Object> data = Map.of("name", "John", "age", 30);
        Person person = toObject(Person.class, data);
        System.out.println(person.name + " - " + person.age);
    }

    public static class Person {
        public String name;
        public int age;

        public Person() {
        }
    }
}


