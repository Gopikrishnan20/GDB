import java.util.ArrayList;

public class TestAccountEnhanced {
    static ArrayList<Accounts> allAccounts = new ArrayList<>();

    static void printAccount(Accounts account) {
        String pinStatus = account.hasPin() ? "Yes" : "No";
        System.out.println("Account #" + account.getAccountNumber() + " | " + account.getName()
                + " (" + account.getAge() + " yrs) | " + account.getAccountType()
                + " | Rs. " + account.getBalance() + " | " + account.getStatus()
                + " | PIN: " + pinStatus);
    }

    static Accounts createAccount(int accountNumber, String name, int age, double balance, String accountType) {
        try {
            Accounts account = new Accounts(accountNumber, name, age, balance, accountType);
            allAccounts.add(account);
            return account;
        } catch (IllegalArgumentException exception) {
            System.out.println("EXCEPTION: " + exception.getMessage());
            return null;
        }
    }

    static void printException(Exception exception) {
        System.out.println("EXCEPTION: " + exception.getMessage());
    }

    static void testValidAccountCreation() {
        System.out.println(">>> Test 1: Valid Account Creation");
        Accounts account = createAccount(1001, "John Doe", 25, 1000.0, "Savings");
        if (account != null) {
            System.out.print("SUCCESS: ");
            printAccount(account);
        }
    }

    static void testInvalidAge() {
        System.out.println(">>> Test 2: Invalid Age (under 18)");
        createAccount(1002, "Young Customer", 16, 1000.0, "Savings");
    }

    static void testInvalidAccountType() {
        System.out.println(">>> Test 3: Invalid Account Type");
        createAccount(1003, "Invalid Type", 25, 1000.0, "Invalid");
    }

    static void testMinimumBalanceOnCreation() {
        System.out.println(">>> Test 4: Minimum Balance on Creation");
        System.out.println("\nCreating Savings account with Rs. 300");
        createAccount(1004, "Low Balance", 25, 300.0, "Savings");
    }

    static void testValidDepositAndWithdrawal() {
        System.out.println(">>> Test 5: Valid Deposit and Withdrawal");
        Accounts account = createAccount(1005, "Alice Brown", 30, 1000.0, "Current");
        if (account == null) {
            return;
        }
        printAccount(account);
        try {
            account.setPin(1234);
            System.out.println("Setting PIN 1234: SUCCESS");
            account.deposit(500.0);
            System.out.println("Depositing Rs. 500.0: SUCCESS");
            System.out.println("Balance after deposit: Rs. " + account.getBalance());
            account.withdraw(200.0, 1234);
            System.out.println("Withdrawing Rs. 200.0: SUCCESS");
            System.out.println("Balance after withdrawal: Rs. " + account.getBalance());
            printAccount(account);
        } catch (IllegalArgumentException | InvalidAmountException | InsufficientBalanceException
                | MinimumBalanceViolationException | InactiveAccountException | InvalidPinException exception) {
            printException(exception);
        }
    }

    static void testInvalidDeposit() {
        System.out.println(">>> Test 6: Invalid Deposit (Negative Amount)");
        Accounts account = allAccounts.get(1);
        System.out.println("Attempting to deposit Rs. -100.0");
        try {
            account.deposit(-100.0);
        } catch (InvalidAmountException | InactiveAccountException exception) {
            printException(exception);
        }
    }

    static void testInsufficientBalance() {
        System.out.println(">>> Test 7: Insufficient Balance");
        Accounts account = createAccount(1006, "Charlie Green", 35, 500.0, "Savings");
        account.setPin(1234);
        printAccount(account);
        System.out.println("Attempting to withdraw Rs. 1000.0");
        try {
            account.withdraw(1000.0, 1234);
        } catch (InvalidAmountException | InsufficientBalanceException | MinimumBalanceViolationException
                | InactiveAccountException | InvalidPinException exception) {
            printException(exception);
        }
    }

    static void testMinimumBalanceViolation() {
        System.out.println(">>> Test 8: Minimum Balance Violation");
        Accounts account = createAccount(1007, "Diana Prince", 28, 1000.0, "Savings");
        account.setPin(1234);
        printAccount(account);
        System.out.println("Attempting to withdraw Rs. 600.0");
        try {
            account.withdraw(600.0, 1234);
        } catch (InvalidAmountException | InsufficientBalanceException | MinimumBalanceViolationException
                | InactiveAccountException | InvalidPinException exception) {
            printException(exception);
        }
    }

    static void testInactiveAccountOperations() {
        System.out.println(">>> Test 9: Inactive Account Operations");
        Accounts account = createAccount(1008, "Eve Wilson", 32, 2000.0, "Current");
        printAccount(account);
        try {
            account.closeAccount();
            System.out.println("Closing account: SUCCESS");
            System.out.println("Attempting to deposit Rs. 100.0 on closed account");
            account.deposit(100.0);
        } catch (InvalidAmountException | InactiveAccountException exception) {
            printException(exception);
        }
        try {
            account.reopenAccount();
            System.out.println("Reopening account: SUCCESS");
            account.deposit(100.0);
            System.out.println("Depositing Rs. 100.0 after reopen: SUCCESS");
            System.out.println("Balance after deposit: Rs. " + account.getBalance());
        } catch (IllegalStateException | InvalidAmountException | InactiveAccountException exception) {
            printException(exception);
        }
    }

    static void testPinVerification() {
        System.out.println(">>> Test 10: PIN Verification");
        Accounts account = createAccount(1009, "Frank Miller", 40, 1500.0, "Savings");
        printAccount(account);
        System.out.println("Attempting to withdraw Rs. 100.0 without PIN set");
        try {
            account.withdraw(100.0, 1234);
        } catch (InvalidAmountException | InsufficientBalanceException | MinimumBalanceViolationException
                | InactiveAccountException | InvalidPinException exception) {
            printException(exception);
        }
        try {
            account.setPin(1234);
            System.out.println("Setting PIN 1234: SUCCESS");
            account.withdraw(200.0, 1234);
            System.out.println("Withdrawing Rs. 200.0 with correct PIN: SUCCESS");
            System.out.println("Balance: Rs. " + account.getBalance());
            System.out.println("Attempting to withdraw Rs. 100.0 with incorrect PIN (9999)");
            account.withdraw(100.0, 9999);
        } catch (IllegalArgumentException | InvalidAmountException | InsufficientBalanceException
                | MinimumBalanceViolationException | InactiveAccountException | InvalidPinException exception) {
            printException(exception);
        }
    }

    static void printAllAccounts() {
        System.out.println(">>> Test 11: All Accounts Summary");
        for (Accounts account : allAccounts) {
            printAccount(account);
        }
    }

    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("ACCOUNT TEST WITH EXCEPTIONS");
        System.out.println("============================================================\n");

        testValidAccountCreation();
        testInvalidAge();
        testInvalidAccountType();
        testMinimumBalanceOnCreation();
        testValidDepositAndWithdrawal();
        testInvalidDeposit();
        testInsufficientBalance();
        testMinimumBalanceViolation();
        testInactiveAccountOperations();
        testPinVerification();
        printAllAccounts();

        System.out.println("============================================================");
        System.out.println("TEST COMPLETED!");
        System.out.println("============================================================");
    }
}
