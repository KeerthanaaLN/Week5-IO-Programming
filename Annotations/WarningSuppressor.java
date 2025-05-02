package Annotations_Reflection;

import java.util.ArrayList;
import java.util.List;

public class WarningSuppressor {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        List rawList = new ArrayList(); 
        rawList.add("Hello");
        rawList.add(123);

        List<String> stringList = rawList; 

        for (String item : stringList) {
            System.out.println(item);
        }
    }
}
