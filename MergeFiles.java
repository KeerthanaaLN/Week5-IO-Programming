package jsonexample;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;

public class MergeFiles {
    public static void main(String[] args) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode dataObject = (ObjectNode) mapper.readTree(new File("data.json"));
            ObjectNode usersObject = (ObjectNode) mapper.readTree(new File("users1.json"));
            dataObject.setAll(usersObject);
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dataObject));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
