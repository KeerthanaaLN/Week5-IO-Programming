package javaReflection;

import java.lang.reflect.Field;

class Configuration {
    private static String API_KEY = "initialApiKey";

    public static void printApiKey() {
        System.out.println("API_KEY: " + API_KEY);
    }
}

class AccessModifyStaticFields {
    public static void main(String[] args) {
        try {
            Class<?> clazz = Configuration.class;
            Field apiKeyField = clazz.getDeclaredField("API_KEY");
            apiKeyField.setAccessible(true);
            apiKeyField.set(null, "newApiKey123");
            Configuration.printApiKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

