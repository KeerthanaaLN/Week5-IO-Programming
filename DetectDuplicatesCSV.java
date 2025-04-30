package javacsv;

import java.io.*;
import java.util.*;

public class DetectDuplicatesCSV {
    public static void main(String[] args) {
        String filePath = "large_file.csv";
        Set<String> ids = new HashSet<>();
        List<String> duplicates = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] data = line.split(",");
                String id = data[0];

                if (!ids.add(id)) {
                    duplicates.add(line);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        if (duplicates.isEmpty()) {
            System.out.println("No duplicate records found.");
        } else {
            System.out.println("Duplicate Records:");
            for (String record : duplicates) {
                System.out.println(record);
            }
        }
    }
}

