package javacsv;

import java.io.FileWriter;
import java.io.IOException;

public class WriteCSV {
    public static void main(String[] args) {
        String filePath = "employees.csv";

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.append("ID,Name,Department,Salary\n");

            writer.append("201,John Smith,Engineering,75000\n");
            writer.append("202,Susan Taylor,Marketing,68000\n");
            writer.append("203,David Wilson,Finance,72000\n");
            writer.append("204,Linda Johnson,HR,65000\n");
            writer.append("205,Michael Brown,Sales,70000\n");

            System.out.println("CSV file written successfully.");

        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}

