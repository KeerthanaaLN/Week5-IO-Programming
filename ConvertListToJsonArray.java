package jsonexample;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConvertListToJsonArray {
    public static void main(String[] args) {
        try {
            List<CT> cars = new ArrayList<>();
            cars.add(new CT("Toyota", "Corolla"));
            cars.add(new CT("Honda", "Civic"));

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(cars);
            System.out.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class CT {
    private String make;
    private String model;

    public CT(String make, String model) {
        this.make = make;
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }
}

