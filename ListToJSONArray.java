package jsonexample;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;

class Person {
    public String name;
    public int age;
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

public class ListToJSONArray {
    public static void main(String[] args) throws Exception {
        List<Person> list = new ArrayList<>();
        list.add(new Person("Alice", 30));
        list.add(new Person("Bob", 20));
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(list);
        System.out.println(json);
    }
}