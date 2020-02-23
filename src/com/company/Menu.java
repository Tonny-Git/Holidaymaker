package com.company;

import java.util.ArrayList;

public class Menu {
    SqlConsole sqlConsole = new SqlConsole();

    public void startMenu() {
        while(true) {
            System.out.println("Please enter an option!");
            System.out.println("[1] Register / find customer");
            System.out.println("[2] Search after available rooms");
            System.out.println("[3] Cancel reservation"); // Jobbar på denna
            System.out.println("[0] Exit program");
            String answer = MethodUtilities.scanner.nextLine();

            switch (answer) {
                case "1":
                    ArrayList<String> answers = MethodUtilities.enterAndReturnQuestions("first name", "last name", "email");
                    sqlConsole.addCustomerToDataBase(answers.get(0), answers.get(1), answers.get(2));
                    break;
                case "2":
                    reservationMenu();
                    break;
                case "3":
                    sqlConsole.cancelReservation();
                    break;
                case "0":
                    return;
                default:
                    MethodUtilities.waitForPressEnter();
                    break;
            }
        }
    }

    // Ska lista upp olika sök alternativ
    private void reservationMenu() {
        ArrayList<String> hotelInformation = new ArrayList<>();
        hotelInformation.add("");
        hotelInformation.add("");
        hotelInformation.add("");
        hotelInformation.add("");
        while(true) {
            System.out.println("Please enter an option to specify search and then press search!");
            System.out.println("[1] Search");
            System.out.println("[2] Hotel information");
            System.out.println("[0] Go back to main Menu");
            String answer = MethodUtilities.scanner.nextLine();

            switch (answer) {
                case "1":
                    sqlConsole.searchHotels(hotelInformation);
                    ArrayList<String> answers = MethodUtilities.enterAndReturnQuestions("start Date (example: 2020-06-02)", "end Date (example: 2020-06-20)", "amount of travelers");
                    sqlConsole.searchAfterRooms(answers.get(0), answers.get(1), answers.get(2));
                    sqlConsole.printRoomSearchResult();
                    sqlConsole.rentRoom();
                    return;
                case "2":
                    hotelInformation = hotelAttributeChoice(hotelInformation);
                    break;
                case "0":
                    return;
                default:
                    MethodUtilities.waitForPressEnter();
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
        System.out.println("[0] Undo Choice");
        String answer = MethodUtilities.scanner.nextLine();

        switch (answer) {
            case "1":
                hotelChoices.set(0, "Pool");
                break;
            case "2":
                hotelChoices.set(1, "Evening entertainment");
                break;
            case "3":
                hotelChoices.set(2, "Kids club");
                break;
            case "4":
                hotelChoices.set(3, "Restaurant");
                break;
            case "5":
                hotelChoices.set(0, "Pool");
                hotelChoices.set(1, "Evening entertainment");
                hotelChoices.set(2, "Kids club");
                hotelChoices.set(3, "Restaurant");
                break;
            case "0":
                hotelChoices.set(0, "");
                hotelChoices.set(1, "");
                hotelChoices.set(2, "");
                hotelChoices.set(3, "");
                break;
            default:
                System.out.println("Wrong input.");
                break;
        }

        return hotelChoices;
    }


    // TA bort???
    private void chooseHotel() {
        System.out.println("Please choose an hotel");
        ArrayList<String> hotelID = sqlConsole.printHotelSearchResult();
        String answer = MethodUtilities.scanner.nextLine();
        try {

        } catch (Exception e) {

        }
    }
}
