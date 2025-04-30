package javacsv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class ValidateCSVData {
    public static void main(String[] args) {
        String filePath = "contacts.csv";
        Pattern emailPattern = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");
        Pattern phonePattern = Pattern.compile("^\\d{10}$");

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] data = line.split(",", -1);
                String id = data[0];
                String name = data[1];
                String email = data[2];
                String phone = data[3];

                boolean validEmail = emailPattern.matcher(email).matches();
                boolean validPhone = phonePattern.matcher(phone).matches();

                if (!validEmail || !validPhone) {
                    System.out.println("Invalid Record:");
                    System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email + ", Phone: " + phone);
                    if (!validEmail) {
                        System.out.println("Invalid email format.");
                    }
                    if (!validPhone) {
                        System.out.println("Phone number must be exactly 10 digits.");
                    }
                    System.out.println();
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
