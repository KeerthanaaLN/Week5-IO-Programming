package javacsv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CountCSVRows {
    public static void main(String[] args) {
        String filePath = "students.csv"; 
        int recordCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {
            	if (isHeader) {
                    isHeader = false;
                    continue;
                }
                recordCount++;
            }

            System.out.println("Total number of student records: " + recordCount);

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
