package jsonexample;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class FilterAgeAbove {
    public static void main(String[] args) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(new File("users.json"));

            for (JsonNode user : rootNode) {
                int age = user.get("age").asInt();
                if (age > 25) {
                    System.out.println(user.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

