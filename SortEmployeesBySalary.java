package javacsv;

import java.util.*;
import java.io.*;

class Employee {
    String id, name, dept;
    double salary;

    Employee(String id, String name, String dept, double salary) {
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.salary = salary;
    }
}

public class SortEmployeesBySalary {
    public static void main(String[] args) {
        String filePath = "employees.csv";
        List<Employee> employees = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); 

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                employees.add(new Employee(data[0], data[1], data[2], Double.parseDouble(data[3])));
            }

            employees.sort((a, b) -> Double.compare(b.salary, a.salary));

            System.out.println("Top 5 Highest Paid Employees:");
            for (int i = 0; i < Math.min(5, employees.size()); i++) {
                Employee emp = employees.get(i);
                System.out.println(emp.name + " - " + emp.dept + " - $" + emp.salary);
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

