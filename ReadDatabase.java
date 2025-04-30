package javacsv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.FileWriter;
import java.util.Scanner;
public class ReadDatabase {
   public static void main(String[] args)
   {
       Scanner scanner=new Scanner(System.in);
       System.out.println("Enter the path of the file to store the CSV file:");
       String csvFilePath = scanner.nextLine();
       try
       {
           Class.forName("com.mysql.cj.jdbc.Driver");
           Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Company", "root", "Password@123");
           String sql = "SELECT EmployeeID, Name, Department, Salary FROM employees";
           PreparedStatement statement = connection.prepareStatement(sql);
           ResultSet result = statement.executeQuery();
           FileWriter csvWriter = new FileWriter(csvFilePath);
           csvWriter.append("Employee ID,Name,Department,Salary\n");
           while (result.next())
           {
               int id = result.getInt("EmployeeID");
               String name = result.getString("Name");
               String department = result.getString("Department");
               int salary = result.getInt("Salary");
               csvWriter.append(id + "," + name + "," + department + "," + salary + "\n");
           }
           csvWriter.flush();
           csvWriter.close();
           result.close();
           statement.close();
           connection.close();
           System.out.println("CSV file created successfully: " + csvFilePath);
       }
       catch (Exception e)
       {
           System.out.println("An error occurred:");
           e.printStackTrace();
       }
   }
}
