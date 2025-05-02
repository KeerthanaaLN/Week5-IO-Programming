package Annotations_Reflection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;

@Retention(RetentionPolicy.RUNTIME)
@interface JsonField {
    String name();
}

class Person {
    @JsonField(name = "user_name")
    private String username;
    
    @JsonField(name = "user_age")
    private int age;

    public Person(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public int getAge() {
        return age;
    }
}

public class JsonSerializer {
    public static String toJson(Object obj) throws IllegalAccessException {
        StringBuilder json = new StringBuilder("{");
        Field[] fields = obj.getClass().getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            JsonField annotation = field.getAnnotation(JsonField.class);
            if (annotation != null) {
                field.setAccessible(true);
                String jsonKey = annotation.name();
                Object value = field.get(obj);
                json.append("\"").append(jsonKey).append("\": \"").append(value).append("\"");

                if (i < fields.length - 1) {
                    json.append(", ");
                }
            }
        }
        json.append("}");
        return json.toString();
    }

    public static void main(String[] args) throws IllegalAccessException {
        Person person = new Person("JohnDoe", 25);
        String json = toJson(person);
        System.out.println(json);
    }
}
