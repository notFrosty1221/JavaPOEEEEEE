import java.util.Scanner;

public class Login {

    // Question 1(a): Check username
    public static boolean checkUserName(String username) {

        if (username.contains("_") && username.length() <= 5) {
            System.out.println("Username successfully captured.");
            return true;
        } else {
            System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length");
            return false;
        }

    }

    // Question 1(a): Check password complexity
    public static boolean checkPasswordComplexity(String password) {

        boolean hasCapital = password.matches(".*[A-Z].*");
        boolean hasNumber = password.matches(".*[0-9].*");
        boolean hasSpecial = password.matches(".*[^a-zA-Z0-9].*");

        if (password.length() >= 8 && hasCapital && hasNumber && hasSpecial) {
            System.out.println("Password successfully captured.");
            return true;
        } else {
            System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
            return false;
        }
    }

    // Question 1(b): Check South African cell phone number
    public static boolean checkCellPhoneNumber(String cellPhone) {

        // The expression checks for the South African International phone number
        // country code (+27) followed by nine digits
        String regex = "^\\+27[0-9]{9}$";

        if (cellPhone.matches(regex)) {
            System.out.println("Cell phone number successfully added.");
            return true;
        } else {
            System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
            return false;
        }
    }

    // Question 1: Registration messages
    public static String registeredUser(String username, String password, String cellPhone) {

        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber(cellPhone)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }

        return "User has been successfully registered.";
    }

    // Question 2: Check login details
    public static boolean loginUser(String registeredUsername, String registeredPassword,
                                    String username, String password) {

        if (registeredUsername.equals(username) && registeredPassword.equals(password)) {
            System.out.println("Welcome " + username + ", it is great to see you again.");
            return true;
        } else {
            System.out.println("Username or password incorrect, please try again.");
            return false;
        }

    }

    // Question 2: Login status message
    public static String returnLoginStatus(boolean loginSuccessful, String username) {

        if (loginSuccessful) {
            return "Welcome " + username + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    // Main program
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your username: ");
        String registeredUsername = scanner.nextLine();

        checkUserName(registeredUsername);

        System.out.print("Enter your password: ");
        String registeredPassword = scanner.nextLine();

        checkPasswordComplexity(registeredPassword);

        System.out.print("Enter your South African cell phone number: ");
        String cellPhone = scanner.nextLine();

        checkCellPhoneNumber(cellPhone);

        // Login

        System.out.println();
        System.out.println("===== Login =====");

        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        loginUser(registeredUsername, registeredPassword, username, password);
    }







}

