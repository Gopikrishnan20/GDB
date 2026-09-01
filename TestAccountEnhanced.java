import java.util.Scanner;
import java.util.ArrayList;

public class TestAccountEnhanced {
    static ArrayList<Accounts> allAccounts = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    // Print account details
    static void printAccount(Accounts acc) {
        String pinStatus = acc.hasPin() ? "Yes" : "No";
        System.out.println("Account #" + acc.getAccountNumber() + " | " + acc.getName()
                + " (" + acc.getAge() + " yrs) | " + acc.getAccountType()
                + " | Rs. " + acc.getBalance() + " | " + acc.getStatus() + " | PIN: " + pinStatus);
    }

    // INPUT FUNCTION: Create Account from User Input
    static Accounts createAccountFromInput() {
        try {
            System.out.print("Enter account number: ");
            int accountNumber = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter account holder name: ");
            String name = scanner.nextLine();

            System.out.print("Enter account holder age: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter initial balance: ");
            double balance = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Enter account type (Savings/Current): ");
            String accountType = scanner.nextLine();

            Accounts acc = new Accounts(accountNumber, name, age, balance, accountType);
            allAccounts.add(acc);
            return acc;
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: " + e.getMessage());
            return null;
        }
    }

    // INPUT FUNCTION: Deposit from User Input
    static void depositFromInput(Accounts acc) {
        try {
            System.out.print("Enter deposit amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();

            acc.deposit(amount);
            System.out.println("Depositing Rs. " + amount + ": SUCCESS");
            System.out.println("New balance: Rs. " + acc.getBalance());
        } catch (InvalidAmountException | InactiveAccountException e) {
            System.out.println("Deposit FAILED: " + e.getMessage());
        }
    }

    // INPUT FUNCTION: Withdraw from User Input
    static void withdrawFromInput(Accounts acc) {
        try {
            System.out.print("Enter withdrawal amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Enter PIN: ");
            int pin = scanner.nextInt();
            scanner.nextLine();

            acc.withdraw(amount, pin);
            System.out.println("Withdrawing Rs. " + amount + " with PIN (" + pin + "): SUCCESS");
            System.out.println("New balance: Rs. " + acc.getBalance());
        } catch (InvalidAmountException | InsufficientBalanceException | MinimumBalanceViolationException
                | InactiveAccountException | InvalidPinException e) {
            System.out.println("Withdrawal FAILED: " + e.getMessage());
        }
    }

    // INPUT FUNCTION: Set PIN from User Input
    static void setPinFromInput(Accounts acc) {
        try {
            System.out.print("Enter 4-digit PIN: ");
            int pin = scanner.nextInt();
            scanner.nextLine();

            acc.setPin(pin);
            System.out.println("Setting PIN " + pin + ": SUCCESS");
        } catch (IllegalArgumentException e) {
            System.out.println("Setting PIN FAILED: " + e.getMessage());
        }
    }

    // INPUT FUNCTION: Close Account from User Input
    static void closeAccountFromInput(Accounts acc) {
        try {
            acc.closeAccount();
            System.out.println("Closing account: SUCCESS");
        } catch (IllegalStateException e) {
            System.out.println("Closing account FAILED: " + e.getMessage());
        }
    }

    // INPUT FUNCTION: Reopen Account from User Input
    static void reopenAccountFromInput(Accounts acc) {
        try {
            acc.reopenAccount();
            System.out.println("Reopening account: SUCCESS");
        } catch (IllegalStateException e) {
            System.out.println("Reopening account FAILED: " + e.getMessage());
        }
    }

    // Print all accounts
    static void printAllAccounts() {
        System.out.println(">>> Test 8: All Accounts Summary");
        if (allAccounts.isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            for (Accounts acc : allAccounts) {
                printAccount(acc);
            }
        }
    }

    // INPUT FUNCTION: Test 2 - Invalid Age from User Input
    static void testInvalidAge() {
        System.out.println("\n>>> Test 2: Invalid Age (under 18)");
        try {
            System.out.print("Enter account number: ");
            int accountNumber = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter account holder name: ");
            String name = scanner.nextLine();

            System.out.print("Enter account holder age (should be < 18 to test): ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter initial balance: ");
            double balance = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Enter account type: ");
            String accountType = scanner.nextLine();

            Accounts acc = new Accounts(accountNumber, name, age, balance, accountType);
            allAccounts.add(acc);
            printAccount(acc);
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR (Expected): " + e.getMessage());
        }
    }

    // INPUT FUNCTION: Test 3 - Invalid Account Type from User Input
    static void testInvalidAccountType() {
        System.out.println("\n>>> Test 3: Invalid Account Type");
        try {
            System.out.print("Enter account number: ");
            int accountNumber = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter account holder name: ");
            String name = scanner.nextLine();

            System.out.print("Enter account holder age: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter initial balance: ");
            double balance = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Enter account type (try 'Invalid' to test): ");
            String accountType = scanner.nextLine();

            Accounts acc = new Accounts(accountNumber, name, age, balance, accountType);
            allAccounts.add(acc);
            printAccount(acc);
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR (Expected): " + e.getMessage());
        }
    }

    // INPUT FUNCTION: Test 4 - Minimum Balance Enforcement from User Input
    static void testMinimumBalance() {
        System.out.println("\n>>> Test 4: Minimum Balance Enforcement on Creation");
        try {
            System.out.print("Enter account number: ");
            int accountNumber = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter account holder name: ");
            String name = scanner.nextLine();

            System.out.print("Enter account holder age: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter initial balance (try below minimum 500): ");
            double balance = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Enter account type (Savings/Current): ");
            String accountType = scanner.nextLine();

            Accounts acc = new Accounts(accountNumber, name, age, balance, accountType);
            allAccounts.add(acc);
            printAccount(acc);
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR (Expected): " + e.getMessage());
        }
    }

    // INPUT FUNCTION: Test 5 - Withdrawal with Minimum Balance from User Input
    static void testWithdrawalMinimumBalance() {
        System.out.println("\n>>> Test 5: Withdrawal with Minimum Balance");
        try {
            System.out.print("Enter account number: ");
            int accountNumber = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter account holder name: ");
            String name = scanner.nextLine();

            System.out.print("Enter account holder age: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter initial balance: ");
            double balance = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Enter account type: ");
            String accountType = scanner.nextLine();

            Accounts acc = new Accounts(accountNumber, name, age, balance, accountType);
            allAccounts.add(acc);

            System.out.print("Enter PIN for this account: ");
            int pin = scanner.nextInt();
            scanner.nextLine();
            
            try {
                acc.setPin(pin);
            } catch (IllegalArgumentException e) {
                System.out.println("PIN setting failed: " + e.getMessage());
                return;
            }

            System.out.println("Initial: ");
            printAccount(acc);

            System.out.print("Enter withdrawal amount (1st attempt): ");
            double amount1 = scanner.nextDouble();
            scanner.nextLine();

            try {
                acc.withdraw(amount1, pin);
                System.out.println("Withdrawing Rs. " + amount1 + ": SUCCESS");
                System.out.println("New balance: Rs. " + acc.getBalance());
            } catch (InvalidAmountException | InsufficientBalanceException | MinimumBalanceViolationException
                    | InactiveAccountException | InvalidPinException e) {
                System.out.println("Withdrawing Rs. " + amount1 + ": FAILED - " + e.getMessage());
            }

            System.out.println("After withdrawal: ");
            printAccount(acc);

            System.out.print("Enter withdrawal amount (2nd attempt - test minimum balance): ");
            double amount2 = scanner.nextDouble();
            scanner.nextLine();

            try {
                acc.withdraw(amount2, pin);
                System.out.println("Withdrawing Rs. " + amount2 + ": SUCCESS");
            } catch (InvalidAmountException | InsufficientBalanceException | MinimumBalanceViolationException
                    | InactiveAccountException | InvalidPinException e) {
                System.out.println("Withdrawing Rs. " + amount2 + ": FAILED - " + e.getMessage());
            }

            System.out.println("Current balance: Rs. " + acc.getBalance());
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    // INPUT FUNCTION: Test 6 - Account Status Management from User Input
    static void testAccountStatusManagement() {
        System.out.println("\n>>> Test 6: Account Status Management");
        try {
            System.out.print("Enter account number: ");
            int accountNumber = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter account holder name: ");
            String name = scanner.nextLine();

            System.out.print("Enter account holder age: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter initial balance: ");
            double balance = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Enter account type: ");
            String accountType = scanner.nextLine();

            Accounts acc = new Accounts(accountNumber, name, age, balance, accountType);
            allAccounts.add(acc);

            System.out.println("Initial: ");
            printAccount(acc);

            try {
                acc.closeAccount();
                System.out.println("Closing account: SUCCESS");
            } catch (IllegalStateException e) {
                System.out.println("Closing account FAILED: " + e.getMessage());
            }

            System.out.println("After close: ");
            printAccount(acc);

            System.out.print("Enter deposit amount to closed account: ");
            double depositAmount = scanner.nextDouble();
            scanner.nextLine();

            try {
                acc.deposit(depositAmount);
                System.out.println("Depositing Rs. " + depositAmount + " to closed account: SUCCESS");
            } catch (InvalidAmountException | InactiveAccountException e) {
                System.out.println("Depositing Rs. " + depositAmount + " to closed account: FAILED - " + e.getMessage());
            }

            try {
                acc.reopenAccount();
                System.out.println("Reopening account: SUCCESS");
            } catch (IllegalStateException e) {
                System.out.println("Reopening account FAILED: " + e.getMessage());
            }

            System.out.println("After reopen: ");
            printAccount(acc);
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    // INPUT FUNCTION: Test 7 - PIN Protection from User Input
    static void testPINProtection() {
        System.out.println("\n>>> Test 7: PIN Protection");
        try {
            System.out.print("Enter account number: ");
            int accountNumber = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter account holder name: ");
            String name = scanner.nextLine();

            System.out.print("Enter account holder age: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter initial balance: ");
            double balance = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Enter account type: ");
            String accountType = scanner.nextLine();

            Accounts acc = new Accounts(accountNumber, name, age, balance, accountType);
            allAccounts.add(acc);

            System.out.print("Enter 4-digit PIN to set: ");
            int pin = scanner.nextInt();
            scanner.nextLine();

            try {
                acc.setPin(pin);
                System.out.println("Setting PIN " + pin + ": SUCCESS");
            } catch (IllegalArgumentException e) {
                System.out.println("Setting PIN FAILED: " + e.getMessage());
                return;
            }

            System.out.print("Enter withdrawal amount: ");
            double amount1 = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Enter correct PIN for withdrawal: ");
            int correctPin = scanner.nextInt();
            scanner.nextLine();

            try {
                acc.withdraw(amount1, correctPin);
                System.out.println("Withdrawing Rs. " + amount1 + " with correct PIN (" + correctPin + "): SUCCESS");
                System.out.println("New balance: Rs. " + acc.getBalance());
            } catch (InvalidAmountException | InsufficientBalanceException | MinimumBalanceViolationException
                    | InactiveAccountException | InvalidPinException e) {
                System.out.println("Withdrawing Rs. " + amount1 + ": FAILED - " + e.getMessage());
            }

            System.out.print("Enter withdrawal amount (2nd attempt): ");
            double amount2 = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Enter incorrect PIN: ");
            int incorrectPin = scanner.nextInt();
            scanner.nextLine();

            try {
                acc.withdraw(amount2, incorrectPin);
                System.out.println("Withdrawing Rs. " + amount2 + ": SUCCESS");
            } catch (InvalidAmountException | InsufficientBalanceException | MinimumBalanceViolationException
                    | InactiveAccountException | InvalidPinException e) {
                System.out.println("Withdrawing Rs. " + amount2 + " with incorrect PIN (" + incorrectPin + "): FAILED - " + e.getMessage());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("ENHANCED ACCOUNT TEST (EXCEPTION-BASED)");
        System.out.println("============================================================");

        // Test 1: Valid Account Creation
        System.out.println("\n>>> Test 1: Valid Account Creation");
        Accounts account1 = createAccountFromInput();
        if (account1 != null) {
            setPinFromInput(account1);
            System.out.println("Account created!");
            printAccount(account1);
        }

        // Test 2: Invalid Age
        testInvalidAge();

        // Test 3: Invalid Account Type
        testInvalidAccountType();

        // Test 4: Minimum Balance Enforcement
        testMinimumBalance();

        // Test 5: Withdrawal with Minimum Balance
        testWithdrawalMinimumBalance();

        // Test 6: Account Status Management
        testAccountStatusManagement();

        // Test 7: PIN Protection
        testPINProtection();

        // Test 8: All Accounts Summary
        printAllAccounts();

        System.out.println("\n============================================================");
        System.out.println("ENHANCED TEST COMPLETED!");
        System.out.println("============================================================");

        scanner.close();
    }
}
