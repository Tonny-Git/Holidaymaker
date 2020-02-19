package com.company;

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

    private void hotelAttributes() {


        System.out.println("[1] Pool"); // attribut
        System.out.println("[2] "); // attribut
        System.out.println("[3] "); // attribut
        System.out.println("[4] "); // attribut
        System.out.println("[5] "); // Välj alla atribut
        System.out.println("[6] "); // Sök
        System.out.println("[0] "); // ångra valet
    }
}
