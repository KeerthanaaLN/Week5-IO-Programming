package jsonexample;

import com.networknt.schema.JsonSchema;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import com.networknt.schema.JsonSchemaFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Set;

public class ValidateEmailUsingSchema {
    public static void main(String[] args) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode schemaNode = mapper.readTree(new File("schema.json"));
            JsonNode jsonData = mapper.readTree(new File("data.json"));

            JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);
            JsonSchema schema = factory.getSchema(schemaNode);

            Set<ValidationMessage> validationResult = schema.validate(jsonData);

            if (validationResult.isEmpty()) {
                System.out.println("Valid JSON");
            } else {
                validationResult.forEach(message -> System.out.println(message.getMessage()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
