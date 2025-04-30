package javacsv;

import java.io.*;
import java.util.*;

public class ReadLargeCSVFile {
    public static void main(String[] args) {
        String filePath = "large_file.csv";
        int chunkSize = 100;
        int totalProcessed = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            List<String[]> chunk = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                chunk.add(line.split(","));

                if (chunk.size() == chunkSize) {
                    processChunk(chunk);
                    totalProcessed += chunk.size();
                    System.out.println("Processed " + totalProcessed + " records so far.");
                    chunk.clear();
                }
            }

            if (!chunk.isEmpty()) {
                processChunk(chunk);
                totalProcessed += chunk.size();
                System.out.println("Processed " + totalProcessed + " records in total.");
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private static void processChunk(List<String[]> chunk) {
        for (String[] record : chunk) {
            System.out.println(Arrays.toString(record));
        }
    }
}

