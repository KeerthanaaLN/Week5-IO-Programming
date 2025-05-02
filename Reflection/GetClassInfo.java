package javaReflection;

import java.lang.reflect.*;

class GetClassInfo {
    public static void main(String[] args) {
        try {
            String className = "java.util.ArrayList";  // You can change this to any class name
            Class<?> clazz = Class.forName(className);

            System.out.println("Methods:");
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                System.out.println(method.getName());
            }

            System.out.println("\nFields:");
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                System.out.println(field.getName());
            }

            System.out.println("\nConstructors:");
            Constructor<?>[] constructors = clazz.getDeclaredConstructors();
            for (Constructor<?> constructor : constructors) {
                System.out.println(constructor.getName());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
