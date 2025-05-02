package javaReflection;

import java.lang.reflect.Field;

public class ObjectToJson {

    public static String toJson(Object obj) {
        StringBuilder json = new StringBuilder("{");
        Field[] fields = obj.getClass().getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);

            try {
                String fieldName = field.getName();
                Object fieldValue = field.get(obj);
                json.append("\"")
                    .append(fieldName)
                    .append("\": \"")
                    .append(fieldValue)
                    .append("\"");

                if (i < fields.length - 1) {
                    json.append(", ");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        json.append("}");
        return json.toString();
    }

    public static void main(String[] args) {
        User user = new User("Alice", 30);
        String jsonString = toJson(user);
        System.out.println(jsonString);
    }

    public static class User {
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
