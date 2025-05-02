package javaReflection;

import java.lang.reflect.Constructor;

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public void display() {
        System.out.println("Student Name: " + name);
    }
}

class DynamicallyCreateObject {
    public static void main(String[] args) {
        try {
            Class<?> clazz = Class.forName("javaReflection.Student");  // Fully qualified name
            Constructor<?> constructor = clazz.getConstructor(String.class);

            Object studentObject = constructor.newInstance("John Doe");
            Student student = (Student) studentObject;
            student.display();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
