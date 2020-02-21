package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class MethodUtilities {
    public static Scanner scanner = new Scanner(System.in);

    private MethodUtilities() {
    }

    public static void waitForPressEnter() {
        System.out.println("Wrong input! Try again!");
        System.out.println("Press enter to continue. . .\n");
        scanner.nextLine();
    }

    public static ArrayList<String> enterAndReturnQuestions(String... questions) {
        ArrayList<String> answers = new ArrayList<>();
        for (String question : questions) {
            System.out.printf("Please enter %s.\n", question);
            answers.add(scanner.nextLine());
        }
        return answers;
    }

}
