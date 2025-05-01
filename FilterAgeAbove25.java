package jsonexample;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;
import java.io.*;

public class FilterAgeAbove25 {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode array = (ArrayNode) mapper.readTree(new File("users.json"));
        for (JsonNode node : array) {
            if (node.get("age").asInt() > 25) {
                System.out.println(node);
            }
        }
    }
}
