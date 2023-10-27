import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OnlineBankingSystem {

    private Map<String, Double> accounts = new HashMap<>();
    private Map<String, StringBuilder> transactionHistory = new HashMap<>();

    public static void main(String[] args) {
        OnlineBankingSystem bankingSystem = new OnlineBankingSystem();
        bankingSystem.run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Online Banking System Menu:");
            System.out.println("1. Create Account");
            System.out.println("2. View Balance");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Transfer Money");
            System.out.println("6. Transaction History");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    createAccount(scanner);
                    break;
                case 2:
                    viewBalance(scanner);
                    break;
                case 3:
                    deposit(scanner);
                    break;
                case 4:
                    withdraw(scanner);
                    break;
                case 5:
                    transferMoney(scanner);
                    break;
                case 6:
                    viewTransactionHistory(scanner);
                    break;
                case 7:
                    System.out.println("Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void createAccount(Scanner scanner) {
        System.out.print("Enter your account number: ");
        String accountNumber = scanner.nextLine();

        if (!accounts.containsKey(accountNumber)) {
            accounts.put(accountNumber, 0.0);
            transactionHistory.put(accountNumber, new StringBuilder());
            System.out.println("Account created successfully.");
        } else {
            System.out.println("Account already exists.");
        }
    }

    private void viewBalance(Scanner scanner) {
        System.out.print("Enter your account number: ");
        String accountNumber = scanner.nextLine();

        if (accounts.containsKey(accountNumber)) {
            double balance = accounts.get(accountNumber);
            System.out.println("Your balance: Rs." + balance);
        } else {
            System.out.println("Account not found.");
        }
    }

    private void deposit(Scanner scanner) {
        System.out.print("Enter your account number: ");
        String accountNumber = scanner.nextLine();

        if (accounts.containsKey(accountNumber)) {
            System.out.print("Enter the amount to deposit: Rs.");
            double amount = scanner.nextDouble();
            accounts.put(accountNumber, accounts.get(accountNumber) + amount);
            transactionHistory.get(accountNumber).append("Deposited Rs." + amount + "\n");
            System.out.println("Deposit successful.");
        } else {
            System.out.println("Account not found.");
        }
    }

    private void withdraw(Scanner scanner) {
        System.out.print("Enter your account number: ");
        String accountNumber = scanner.nextLine();

        if (accounts.containsKey(accountNumber)) {
            System.out.print("Enter the amount to withdraw: Rs.");
            double amount = scanner.nextDouble();
            if (amount <= accounts.get(accountNumber)) {
                accounts.put(accountNumber, accounts.get(accountNumber) - amount);
                transactionHistory.get(accountNumber).append("Withdrawn Rs." + amount + "\n");
                System.out.println("Withdrawal successful.");
            } else {
                System.out.println("Insufficient funds.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    private void transferMoney(Scanner scanner) {
        System.out.print("Enter your account number: ");
        String sourceAccountNumber = scanner.nextLine();

        System.out.print("Enter the recipient's account number: ");
        String recipientAccountNumber = scanner.nextLine();

        if (accounts.containsKey(sourceAccountNumber) && accounts.containsKey(recipientAccountNumber)) {
            System.out.print("Enter the amount to transfer: Rs.");
            double amount = scanner.nextDouble();
            if (amount <= accounts.get(sourceAccountNumber)) {
                accounts.put(sourceAccountNumber, accounts.get(sourceAccountNumber) - amount);
                accounts.put(recipientAccountNumber, accounts.get(recipientAccountNumber) + amount);
                transactionHistory.get(sourceAccountNumber).append("Transferred Rs." + amount + " to " + recipientAccountNumber + "\n");
                transactionHistory.get(recipientAccountNumber).append("Received Rs." + amount + " from " + sourceAccountNumber + "\n");
                System.out.println("Transfer successful.");
            } else {
                System.out.println("Insufficient funds.");
            }
        } else {
            System.out.println("One or both accounts not found.");
        }
    }

    private void viewTransactionHistory(Scanner scanner) {
        System.out.print("Enter your account number: ");
        String accountNumber = scanner.nextLine();

        if (transactionHistory.containsKey(accountNumber)) {
            System.out.println("Transaction History:");
            System.out.println(transactionHistory.get(accountNumber).toString());
        } else {
            System.out.println("Account not found.");
        }
    }
}







