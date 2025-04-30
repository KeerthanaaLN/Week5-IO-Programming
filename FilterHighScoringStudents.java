package javacsv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FilterHighScoringStudents {
    public static void main(String[] args) {
        String filePath = "students.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] data = line.split(",");
                int marks = Integer.parseInt(data[3]);

                if (marks > 80) {
                    System.out.println("ID: " + data[0] + ", Name: " + data[1] +
                                       ", Age: " + data[2] + ", Marks: " + data[3]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

