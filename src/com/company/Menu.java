package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);

    public void startMenu() {
        while(true) {
            System.out.println("Please enter an option!");
            System.out.println("[1] Register customer");
            System.out.println("[2] Search after available rooms"); // Ska kunna ta emot flera sök kriterier
            System.out.println("[3] Cancel reservation");
            System.out.println("[4] Change reservation"); // VG fråga
            System.out.println("[5] Additional services");
            System.out.println("[0] Exit program");
            String answer = scanner.nextLine();

            switch (answer) {
                case "1":
                    //
                    break;
                case "2":
                    reservationMenu();
                    break;
                case "0":
                    //
                    return;
                default:
                    System.out.println("Wrong input! Try again!");
                    System.out.println("Press enter to continue. . .");
                    scanner.nextLine();
                    break;
            }
        }
    }

    // Ska lista upp olika sök alternativ
    private void reservationMenu() {
        while(true) {
            System.out.println("Please enter an option!");
            System.out.println("[1]");
            System.out.println("[0] Go back to main Menu");
            String answer = scanner.nextLine();

            switch (answer) {
                case "1":
                    //
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Wrong input! Try again!");
                    System.out.println("Press enter to continue. . .");
                    scanner.nextLine();
                    break;
            }
        }
    }

    private ArrayList<String> hotelAttributeChoice(ArrayList<String> hotelChoices) {
        System.out.println("[1] Pool");
        System.out.println("[2] Evening entertainment");
        System.out.println("[3] Kids club");
        System.out.println("[4] Restaurant");
        System.out.println("[5] Choose all");
        //System.out.println("[6] Search"); // Sök
        System.out.println("[0] Undo Choice"); // ångra valet
        String answer = scanner.nextLine();

        switch (answer) {
            case "1":
                if (!hotelChoices.contains("Pool"))
                    hotelChoices.add("Pool");
                break;
            case "2":
                if (!hotelChoices.contains("Evening entertainment"))
                    hotelChoices.add("Evening entertainment");
                break;
            case "3":
                if (!hotelChoices.contains("Kids club"))
                    hotelChoices.add("Kids club");
                break;
            case "4":
                if (!hotelChoices.contains("Restaurant"))
                    hotelChoices.add("Restaurant");
                break;
            case "5":
                //
                break;
            case "0":
                //
                break;
            default:
                System.out.println("Wrong input.");
                break;
        }

        return hotelChoices;
    }
}
