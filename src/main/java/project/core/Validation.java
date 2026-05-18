package project.core;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Validation {
    
    public static int GetIntInput(Scanner sc) {
        int choice;
        while (true) {
            try {
                choice = sc.nextInt();
                sc.nextLine();
                return choice;
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                sc.nextLine();
            }
        }
    }
    
    public static double GetDoubleInput(Scanner sc) {
        double choice;
        while (true) {
            try {
                choice = sc.nextDouble();
                sc.nextLine();
                return choice;
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a double.");
                sc.nextLine();
            }
        }
    }

    public static String GetEmail(Scanner sc) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        String email;

        while (true) {
            email = sc.nextLine();

            if (!email.matches(emailRegex)) {
                System.out.println("Invalid input. Please enter an email.");

            } else {
                return email;
            }
        }
    }
}
