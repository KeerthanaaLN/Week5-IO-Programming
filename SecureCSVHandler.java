package javacsv;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.util.Base64;

public class SecureCSVHandler {

    private static final String ALGO = "AES";
    private static final String KEY = "1234567890123456"; 

    public static String encrypt(String data) throws Exception {
        SecretKeySpec key = new SecretKeySpec(KEY.getBytes(), ALGO);
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encVal);
    }

    public static String decrypt(String encryptedData) throws Exception {
        SecretKeySpec key = new SecretKeySpec(KEY.getBytes(), ALGO);
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = Base64.getDecoder().decode(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        return new String(decValue);
    }

    public static void writeEncryptedCSV(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("ID,Name,Email,Salary\n");

            String[][] data = {
                {"1", "John Doe", "john@example.com", "75000"},
                {"2", "Jane Smith", "jane@example.com", "82000"},
                {"3", "Alice Brown", "alice@example.com", "88000"}
            };

            for (String[] row : data) {
                String encEmail = encrypt(row[2]);
                String encSalary = encrypt(row[3]);
                writer.write(row[0] + "," + row[1] + "," + encEmail + "," + encSalary + "\n");
            }

            System.out.println("Encrypted CSV written: " + filePath);

        } catch (Exception e) {
            System.out.println("Write error: " + e.getMessage());
        }
    }

    public static void readDecryptedCSV(String filePath) {
        System.out.println("\nDecrypted Records:");
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine(); 
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1);
                String decEmail = decrypt(parts[2]);
                String decSalary = decrypt(parts[3]);
                System.out.println("ID: " + parts[0] + ", Name: " + parts[1] +
                        ", Email: " + decEmail + ", Salary: " + decSalary);
            }
        } catch (Exception e) {
            System.out.println("Read error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String csvFile = "employees_secure.csv";
        writeEncryptedCSV(csvFile);
        readDecryptedCSV(csvFile);
    }
}

