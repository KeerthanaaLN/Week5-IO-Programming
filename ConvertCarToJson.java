package jsonexample;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertCarToJson {
    public static void main(String[] args) throws Exception {
        Car car = new Car("Toyota", "Camry", 2020);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(car);
        System.out.println(json);
    }
}

class Car {
    public String make;
    public String model;
    public int year;

    public Car() {}
    public Car(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }
}

