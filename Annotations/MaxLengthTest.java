package Annotations_Reflection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;

@Retention(RetentionPolicy.RUNTIME)
@interface MaxLength {
    int value();
}

class User {
    @MaxLength(10)
    private String username;

    public User(String username) {
        if (username.length() > getMaxLength("username")) {
            throw new IllegalArgumentException("Username exceeds maximum length");
        }
        this.username = username;
    }

    private int getMaxLength(String fieldName) {
        try {
            Field field = User.class.getDeclaredField(fieldName);
            MaxLength annotation = field.getAnnotation(MaxLength.class);
            return annotation != null ? annotation.value() : Integer.MAX_VALUE;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return Integer.MAX_VALUE;
        }
    }

    public String getUsername() {
        return username;
    }
}

public class MaxLengthTest {
    public static void main(String[] args) {
        try {
            User user = new User("JohnDoe12345"); // This will throw an exception
            System.out.println("User created with username: " + user.getUsername());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            User validUser = new User("JohnDoe"); // Valid username
            System.out.println("User created with username: " + validUser.getUsername());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}

