package javaReflection;

import java.lang.reflect.Method;
import java.util.Scanner;

class MathOperations {
    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }
}

class DynamicMethodInvocation {
    public static void main(String[] args) {
        try {
            MathOperations mathOperations = new MathOperations();
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter operation (add, subtract, multiply): ");
            String operation = scanner.nextLine();

            System.out.println("Enter first number: ");
            int num1 = scanner.nextInt();

            System.out.println("Enter second number: ");
            int num2 = scanner.nextInt();

            Method method = MathOperations.class.getMethod(operation, int.class, int.class);
            int result = (int) method.invoke(mathOperations, num1, num2);

            System.out.println("Result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

