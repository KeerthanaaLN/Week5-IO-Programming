package jsonexample;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;

public class MergeJSONFiles {
    public static void main(String[] args) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node1 = mapper.readTree(new File("data.json"));
            JsonNode node2 = mapper.readTree(new File("users.json"));

            
            ObjectNode mergedNode = (ObjectNode) node1;
            mergedNode.set("users", node2);

            System.out.println(mergedNode.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
