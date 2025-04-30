package javacsv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SearchEmployeeByName {
    public static void main(String[] args) {
        String filePath = "employees.csv";
        String searchName = "Susan Taylor"; 

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean found = false;
            br.readLine(); 

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String name = data[1];

                if (name.equalsIgnoreCase(searchName)) {
                    System.out.println("Department: " + data[2]);
                    System.out.println("Salary: $" + data[3]);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Employee not found.");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
