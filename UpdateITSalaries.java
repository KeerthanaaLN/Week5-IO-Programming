package javacsv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UpdateITSalaries {
    public static void main(String[] args) {
        String inputFile = "employees.csv";
        String outputFile = "updated_employees.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             FileWriter writer = new FileWriter(outputFile)) {

            String line = br.readLine();
            writer.write(line + "\n"); 

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String dept = data[2];
                double salary = Double.parseDouble(data[3]);

                if (dept.equalsIgnoreCase("IT")) {
                    salary *= 1.10;
                }

                writer.write(data[0] + "," + data[1] + "," + dept + "," + String.format("%.2f", salary) + "\n");
            }

            System.out.println("Updated records written to " + outputFile);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

