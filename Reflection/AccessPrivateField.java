package javaReflection;

import java.lang.reflect.Field;

class Person {
    private int age;

    public Person(int age) {
        this.age = age;
    }
}

class AccessPrivateField {
    public static void main(String[] args) {
        try {
            Person person = new Person(25);
            Field field = Person.class.getDeclaredField("age");

            field.setAccessible(true);
            System.out.println("Age before modification: " + field.get(person));

            field.set(person, 30);
            System.out.println("Age after modification: " + field.get(person));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

