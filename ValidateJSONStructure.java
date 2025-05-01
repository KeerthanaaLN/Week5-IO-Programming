package jsonexample;
import java.io.File;
import java.io.IOException;


import com.fasterxml.jackson.databind.*;

public class ValidateJSONStructure {
    public static void main(String[] args) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(new File("data.json"));
            System.out.println("Valid JSON");
        } catch (IOException e) {
            System.out.println("Invalid JSON");
        }
    }
}
