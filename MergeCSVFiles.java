package javacsv;

import java.io.*;
import java.util.*;

public class MergeCSVFiles {
    public static void main(String[] args) {
        String students1File = "students1.csv";
        String students2File = "students2.csv";
        String outputFile = "merged_students.csv";
        
        Map<Integer, String[]> students1Data = new HashMap<>();
        Map<Integer, String[]> students2Data = new HashMap<>();

        try (BufferedReader br1 = new BufferedReader(new FileReader(students1File))) {
            String line;
            boolean isHeader1 = true;

            while ((line = br1.readLine()) != null) {
                if (isHeader1) {
                    isHeader1 = false;
                    continue;
                }

                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                students1Data.put(id, new String[]{data[1], data[2]});
            }

        } catch (IOException e) {
            System.out.println("Error reading students1.csv: " + e.getMessage());
        }

        try (BufferedReader br2 = new BufferedReader(new FileReader(students2File))) {
            String line;
            boolean isHeader2 = true;

            while ((line = br2.readLine()) != null) {
                if (isHeader2) {
                    isHeader2 = false;
                    continue;
                }

                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                students2Data.put(id, new String[]{data[1], data[2]});
            }

        } catch (IOException e) {
            System.out.println("Error reading students2.csv: " + e.getMessage());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write("ID,Name,Age,Marks,Grade\n");

            for (Map.Entry<Integer, String[]> entry : students1Data.entrySet()) {
                int id = entry.getKey();
                String[] student1Info = entry.getValue();
                String[] student2Info = students2Data.get(id);

                if (student2Info != null) {
                    writer.write(id + "," + student1Info[0] + "," + student1Info[1] + "," +
                                 student2Info[0] + "," + student2Info[1] + "\n");
                }
            }

            System.out.println("Merged CSV file created: " + outputFile);

        } catch (IOException e) {
            System.out.println("Error writing to merged_students.csv: " + e.getMessage());
        }
    }
}

