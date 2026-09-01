import java.util.Scanner;

public class TestAccount {

    static void printAccount(Accounts acc) {
        System.out.println("Account #" + acc.getAccountNumber() + " | " + acc.getName()
                + " (" + acc.getAge() + " yrs) | " + acc.getAccountType()
                + " | ₹" + acc.getBalance() + " | " + acc.getStatus());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==================================================");
        System.out.println("GLOBAL DIGITAL BANK - ACCOUNT TEST");
        System.out.println("==================================================");

        System.out.println(">>> 1. Creating Account");
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter account holder name: ");
        String accountHolderName = scanner.nextLine();

        System.out.print("Enter account holder age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter initial balance: ");
        double initialBalance = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter account type: ");
        String accountType = scanner.nextLine();

        Accounts account;
        try {
            account = new Accounts(accountNumber, accountHolderName, age, initialBalance, accountType);
            account.setPin(1234);
        } catch (IllegalArgumentException e) {
            System.out.println("Account creation FAILED: " + e.getMessage());
            scanner.close();
            return;
        }
        System.out.println("Account created!");
        printAccount(account);

        System.out.println(">>> 2. Deposit Money");
        try {
            account.deposit(500.0);
            System.out.println("Depositing ₹500.0: SUCCESS");
        } catch (AccountException e) {
            System.out.println("Depositing ₹500.0: FAILED - " + e.getMessage());
        }
        System.out.println("New balance: ₹" + account.getBalance());
        try {
            account.deposit(-100.0);
            System.out.println("Depositing ₹-100.0: SUCCESS");
        } catch (AccountException e) {
            System.out.println("Depositing ₹-100.0: FAILED - " + e.getMessage());
        }

        System.out.println(">>> 3. Withdraw Money");
        try {
            account.withdraw(200.0, 1234);
            System.out.println("Withdrawing ₹200.0: SUCCESS");
        } catch (AccountException e) {
            System.out.println("Withdrawing ₹200.0: FAILED - " + e.getMessage());
        }
        System.out.println("New balance: ₹" + account.getBalance());
        try {
            account.withdraw(2000.0, 1234);
            System.out.println("Withdrawing ₹2000.0: SUCCESS");
        } catch (AccountException e) {
            System.out.println("Withdrawing ₹2000.0: FAILED - " + e.getMessage());
        }
        System.out.println("Current balance: ₹" + account.getBalance());

        System.out.println(">>> 4. Creating Another Account");
        System.out.print("Enter account number: ");
        int accountNumber2 = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter account holder name: ");
        String accountHolderName2 = scanner.nextLine();

        System.out.print("Enter account holder age: ");
        int age2 = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter initial balance: ");
        double initialBalance2 = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter account type: ");
        String accountType2 = scanner.nextLine();

        Accounts acc2 = null;
        try {
            acc2 = new Accounts(accountNumber2, accountHolderName2, age2, initialBalance2, accountType2);
            printAccount(acc2);
        } catch (IllegalArgumentException e) {
            System.out.println("Account creation FAILED: " + e.getMessage());
        }

        System.out.println(">>> 5. All Accounts");
        printAccount(account);
        if (acc2 != null) {
            printAccount(acc2);
        }

        System.out.println("==================================================");
        System.out.println("TEST COMPLETED!");
        System.out.println("==================================================");

        scanner.close();
    }
}