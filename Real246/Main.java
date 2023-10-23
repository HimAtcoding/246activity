import java.util.Scanner;
import java.util.Random;

public class Main {
    static Scanner in = new Scanner(System.in);
    static int currentBalance = 1000;

    public static void main(String[] args) {
        System.out.println("Hello there, you can create a stock account here");
        System.out.println("Type your username");
        String username = in.nextLine();
        System.out.println("Now type in your password");
        String password = in.nextLine();

        String maskedPassword = maskPassword(password);

        System.out.println("Username: " + username);
        System.out.println("Password: " + maskedPassword);
        System.out.println("");

        System.out.println("Current balance: $" + currentBalance);

        while (true) {
            System.out.println("Choose which of the following stocks you want, along with quantity, or type 'exit' to quit:");
            stockList();
            String response = in.nextLine();

            if (response.equalsIgnoreCase("exit")) {
                break;
            }

            String[] parts = response.split(" ");
            if (parts.length == 2) {
                String stockName = parts[0];
                int numShares = Integer.parseInt(parts[1]);
                int stockPrice = getStockPrice(stockName);

                if (stockPrice == -1) {
                    System.out.println("Invalid stock name.");
                } else if (numShares * stockPrice > currentBalance) {
                    System.out.println("Insufficient balance to purchase " + numShares + " shares of " + stockName);
                } else {
                    int deduction = numShares * stockPrice;
                    currentBalance -= deduction;
                    System.out.println("You bought: " + numShares + " stock of " + stockName + " for $" + deduction);
                    int stockReturn = randomizer();
                    currentBalance += stockReturn;
                    System.out.println("Stock's return: " + stockReturn);
                    System.out.println("Remaining balance: $" + currentBalance);
                }
            } else {
                System.out.println("Invalid input format. Please enter in the format 'stockName quantity'.");
            }
        }
    }

    public static int randomizer() {
        Random random = new Random();
        return random.nextInt(201) - 100; // Returns a random number between -100 and 100.
    }

    public static String maskPassword(String pass) {
        pass = pass.replaceAll("[a-zA-Z]", "*");
        return pass;
    }

    public static void stockList() {
        String[] companies = {"nvidia", "microsoft", "apple", "amd", "tesla"};
        int[] prices = {440, 331, 176, 105, 254};

        for (int i = 0; i < companies.length; i++) {
            System.out.println(companies[i] + " $" + prices[i]);
        }
    }

    public static int getStockPrice(String stockName) {
        String[] companies = {"nvidia", "microsoft", "apple", "amd", "tesla"};
        int[] prices = {440, 331, 176, 105, 254};

        for (int i = 0; i < companies.length; i++) {
            if (stockName.equalsIgnoreCase(companies[i])) {
                return prices[i];
            }
        }

        return -1; // Return -1 for an invalid stock name.
    }
}