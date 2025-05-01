package jsonexample;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.MappingIterator;

import java.io.File;
import java.util.List;
import java.util.Map;

public class ConvertCsvToJson {
    public static void main(String[] args) {
        try {
            File csvFile = new File("data.csv");

            CsvMapper csvMapper = new CsvMapper();
            CsvSchema schema = CsvSchema.emptySchema().withHeader();

            MappingIterator<Map<String, String>> it = csvMapper
                    .readerFor(Map.class)
                    .with(schema)
                    .readValues(csvFile);

            List<Map<String, String>> csvData = it.readAll();

            ObjectMapper jsonMapper = new ObjectMapper();
            String json = jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(csvData);

            System.out.println(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
