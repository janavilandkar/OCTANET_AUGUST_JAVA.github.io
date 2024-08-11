import java.util.ArrayList;
import java.util.Scanner;

class ATMMachine {
    private double balance;
    private String pin;
    private ArrayList<String> transactionHistory;

    public ATMMachine(String pin) {
        this.balance = 1000.0; // Default balance
        this.pin = pin;
        this.transactionHistory = new ArrayList<>();
    }

    private boolean checkPin(String enteredPin) {
        return enteredPin.equals(this.pin);
    }

    public void balanceInquiry() {
        System.out.println("Your current balance is: $" + this.balance);
        this.transactionHistory.add("Balance inquiry");
    }

    public void cashWithdrawal(double amount) {
        if (amount > this.balance) {
            System.out.println("Insufficient balance!");
        } else {
            this.balance -= amount;
            System.out.println("$" + amount + " has been withdrawn. Your new balance is $" + this.balance);
            this.transactionHistory.add("Withdrew $" + amount);
        }
    }

    public void cashDeposit(double amount) {
        this.balance += amount;
        System.out.println("$" + amount + " has been deposited. Your new balance is $" + this.balance);
        this.transactionHistory.add("Deposited $" + amount);
    }

    public void changePin(String oldPin, String newPin) {
        if (checkPin(oldPin)) {
            this.pin = newPin;
            System.out.println("PIN successfully changed!");
            this.transactionHistory.add("PIN changed");
        } else {
            System.out.println("Incorrect old PIN!");
        }
    }

    public void viewTransactionHistory() {
        System.out.println("Transaction History:");
        if (this.transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (String transaction : this.transactionHistory) {
                System.out.println(transaction);
            }
        }
    }
}

public class ATM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Set your ATM PIN: ");
        String pin = scanner.nextLine();

        ATMMachine atm = new ATMMachine(pin);

        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Balance Inquiry");
            System.out.println("2. Cash Withdrawal");
            System.out.println("3. Cash Deposit");
            System.out.println("4. Change PIN");
            System.out.println("5. View Transaction History");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine()); // Use nextLine() to avoid issues

            switch (choice) {
                case 1:
                    atm.balanceInquiry();
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawalAmount = Double.parseDouble(scanner.nextLine());
                    atm.cashWithdrawal(withdrawalAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = Double.parseDouble(scanner.nextLine());
                    atm.cashDeposit(depositAmount);
                    break;
                case 4:
                    System.out.print("Enter your old PIN: ");
                    String oldPin = scanner.nextLine();
                    System.out.print("Enter your new PIN: ");
                    String newPin = scanner.nextLine();
                    atm.changePin(oldPin, newPin);
                    break;
                case 5:
                    atm.viewTransactionHistory();
                    break;
                case 6:
                    System.out.println("Thank you for using the ATM!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}