package javaReflection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.AnnotatedElement;

@Retention(RetentionPolicy.RUNTIME)
@interface Author {
    String name();
}

@Author(name = "John Doe")
class MyClass {
    // Some class code
}

class RetrieveAnnotations {
    public static void main(String[] args) {
        try {
            Class<?> clazz = MyClass.class;
            if (clazz.isAnnotationPresent(Author.class)) {
                Author author = clazz.getAnnotation(Author.class);
                System.out.println("Author: " + author.name());
            } else {
                System.out.println("No Author annotation found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
