

import java.util.Scanner;

public class QuickChat {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Registration
        System.out.println("===== QuickChat Registration =====");

        System.out.print("Enter your username: ");
        String registeredUsername = scanner.nextLine();

        System.out.print("Enter your password: ");
        String registeredPassword = scanner.nextLine();

        System.out.print("Enter your South African cell phone number: ");
        String cellPhone = scanner.nextLine();

        String registrationMessage = Login.registeredUser(
                registeredUsername,
                registeredPassword,
                cellPhone
        );

        System.out.println(registrationMessage);

        if (!registrationMessage.equals(
                "User has been successfully registered.")) {
            System.out.println(
                    "Please restart QuickChat and register again."
            );
            scanner.close();
            return;
        }

        // Login
        System.out.println();
        System.out.println("===== QuickChat Login =====");

        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        boolean loginSuccessful = Login.loginUser(
                registeredUsername,
                registeredPassword,
                username,
                password
        );

        System.out.println(
                Login.returnLoginStatus(loginSuccessful, username)
        );

        if (!loginSuccessful) {
            scanner.close();
            return;
        }

        System.out.println();
        System.out.println("Welcome to QuickChat.");

        // Ask for the message limit
        int messageLimit = 0;

        while (messageLimit <= 0) {
            System.out.print(
                    "How many messages would you like to enter? "
            );

            if (scanner.hasNextInt()) {
                messageLimit = scanner.nextInt();
                scanner.nextLine();

                if (messageLimit <= 0) {
                    System.out.println(
                            "Please enter a number greater than zero."
                    );
                }
            } else {
                System.out.println(
                        "Please enter a valid whole number."
                );
                scanner.nextLine();
            }
        }

        int messageNumber = 0;
        int choice = 0;

        // Main menu
        while (choice != 3) {

            System.out.println();
            System.out.println("===== QuickChat Menu =====");
            System.out.println("1) Send messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Choose an option: ");

            if (!scanner.hasNextInt()) {
                System.out.println(
                        "Invalid input. Please enter 1, 2, or 3."
                );
                scanner.nextLine();
                continue;
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:

                    if (messageNumber >= messageLimit) {
                        System.out.println(
                                "You have reached your message limit."
                        );
                        break;
                    }

                    boolean continueSending = true;

                    while (continueSending
                            && messageNumber < messageLimit) {

                        System.out.println();
                        System.out.println("===== Create Message =====");

                        // Validate recipient
                        String recipient;

                        while (true) {
                            System.out.print(
                                    "Enter recipient number "
                                            + "(e.g. +27821234567): "
                            );

                            recipient = scanner.nextLine().trim();

                            if (recipient.matches("^\\+27[0-9]{9}$")) {
                                break;
                            }

                            System.out.println(
                                    "Invalid recipient. Use +27 followed "
                                            + "by nine digits."
                            );
                        }

                        // Validate message
                        String message;

                        while (true) {
                            System.out.print("Enter your message: ");
                            message = scanner.nextLine().trim();

                            if (message.isEmpty()) {
                                System.out.println(
                                        "Please enter a message."
                                );
                            } else if (message.length() >= 250) {
                                System.out.println(
                                        "Please enter a message of less "
                                                + "than 250 characters."
                                );
                            } else {
                                break;
                            }
                        }

                        // Create message
                        Message newMessage = new Message(
                                messageNumber + 1,
                                recipient,
                                message
                        );

                        System.out.println();
                        System.out.println(
                                "Message ID: " + newMessage.getMessageID()
                        );
                        System.out.println(
                                "Message Hash: " + newMessage.getMessageHash()
                        );
                        System.out.println(
                                "Recipient: " + newMessage.getRecipient()
                        );
                        System.out.println(
                                "Message: " + newMessage.getMessage()
                        );

                        // Message action menu
                        System.out.println();
                        System.out.println(
                                "What would you like to do?"
                        );
                        System.out.println("1) Send message");
                        System.out.println("2) Disregard message");
                        System.out.println(
                                "3) Store message to send later"
                        );
                        System.out.print("Choose an option: ");

                        String messageAction =
                                scanner.nextLine().trim();

                        String actionResult =
                                newMessage.sentMessage(messageAction);

                        System.out.println(actionResult);

                        // Count only messages successfully sent.
                        if (messageAction.equals("1")
                                && actionResult.equals(
                                "Message successfully sent.")) {
                            messageNumber++;
                        }

                        if (messageNumber >= messageLimit) {
                            System.out.println(
                                    "You have reached your message limit."
                            );
                            continueSending = false;
                        } else {
                            System.out.print(
                                    "Would you like to create another "
                                            + "message? (yes/no): "
                            );

                            String answer =
                                    scanner.nextLine().trim();

                            if (!answer.equalsIgnoreCase("yes")) {
                                continueSending = false;
                            }
                        }
                    }

                    break;

                case 2:

                    System.out.println();
                    System.out.println(
                            "===== Recently Sent Messages ====="
                    );
                    System.out.println(Message.printMessages());
                    System.out.println(
                            "Total messages sent this session: "
                                    + Message.returnTotalMessages()
                    );

                    break;

                case 3:

                    System.out.println(
                            "Thank you for using QuickChat."
                    );
                    break;

                default:

                    System.out.println(
                            "Invalid option. Please choose 1, 2, or 3."
                    );
            }
        }

        System.out.println();
        System.out.println(
                "Total messages sent: "
                        + Message.returnTotalMessages()
        );

        scanner.close();
    }
}








