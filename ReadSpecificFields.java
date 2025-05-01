package jsonexample;

import java.io.*;
import com.fasterxml.jackson.databind.*;

public class ReadSpecificFields {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(new File("data.json"));
        String name = node.get("name").asText();
        String email = node.get("email").asText();
        System.out.println(name + " " + email);
    }
}
