package javaReflection;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

class Calculator {
    private int multiply(int a, int b) {
        return a * b;
    }
}

class InvokePrivateMethod {
    public static void main(String[] args) {
        try {
            Calculator calculator = new Calculator();
            Method method = Calculator.class.getDeclaredMethod("multiply", int.class, int.class);

            method.setAccessible(true);
            int result = (int) method.invoke(calculator, 5, 4);

            System.out.println("Result of multiply(5, 4): " + result);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
