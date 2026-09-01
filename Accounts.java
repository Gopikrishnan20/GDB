public class Accounts {
    private int accountNumber;
    private String name;
    private int age;
    private double balance;
    private String accountType;
    private String status;
    private Integer pin;//we use Integer instead of int to allow null value for pin when it is not set

    public static final int MIN_AGE = 18;
    public static final double MIN_BALANCE_SAVINGS = 500.0;
    public static final double MIN_BALANCE_CURRENT = 1000.0;

    Accounts(int accountNumber, String name, int age, double initialBalance, String accountType) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (age < MIN_AGE) {
            throw new IllegalArgumentException("Age must be at least " + MIN_AGE + " (given: " + age + ")");
        }
        if (accountType == null
                || !(accountType.equalsIgnoreCase("Savings") || accountType.equalsIgnoreCase("Current"))) {
            throw new IllegalArgumentException("Account type must be 'Savings' or 'Current' (given: " + accountType + ")");
        }
        double minBalance = accountType.equalsIgnoreCase("Savings") ? MIN_BALANCE_SAVINGS : MIN_BALANCE_CURRENT;
        if (initialBalance < minBalance) {
            throw new IllegalArgumentException("Initial balance for " + accountType + " must be at least Rs. "
                    + minBalance + " (given: Rs. " + initialBalance + ")");
        }

        this.accountNumber = accountNumber;
        this.name = name;
        this.age = age;
        this.balance = initialBalance;
        this.accountType = accountType;
        this.status = "Active";
    }

    private double getMinimumBalance() {
        return accountType.equalsIgnoreCase("Savings") ? MIN_BALANCE_SAVINGS : MIN_BALANCE_CURRENT;
    }

    void deposit(double amount) throws InvalidAmountException, InactiveAccountException {
        if (!status.equals("Active")) {
            throw new InactiveAccountException("Account #" + accountNumber + " is inactive");
        }
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive (given: Rs. " + amount + ")");
        }
        balance += amount;
    }

    void withdraw(double amount, int pin) throws InvalidAmountException, InsufficientBalanceException,
            MinimumBalanceViolationException, InactiveAccountException, InvalidPinException {
        if (!status.equals("Active")) {
            throw new InactiveAccountException("Account #" + accountNumber + " is inactive");
        }
        if (!hasPin()) {
            throw new InvalidPinException("No PIN set for account #" + accountNumber);
        }
        if (!verifyPin(pin)) {
            throw new InvalidPinException("Incorrect PIN");
        }
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be positive (given: Rs. " + amount + ")");
        }
        if (amount > balance) {
            throw new InsufficientBalanceException("Insufficient balance: available Rs. " + balance
                    + ", requested Rs. " + amount);
        }
        double minBalance = getMinimumBalance();
        if (balance - amount < minBalance) {
            throw new MinimumBalanceViolationException("Withdrawal would leave Rs. " + (balance - amount)
                    + ", below the minimum of Rs. " + minBalance + " for " + accountType);
        }
        balance -= amount;
    }

    void setPin(int pin) {
        if (pin < 1000 || pin > 9999) {
            throw new IllegalArgumentException("PIN must be exactly 4 digits (given: " + pin + ")");
        }
        this.pin = pin;
    }

    boolean verifyPin(int pin) {
        return this.pin != null && this.pin == pin;
    }

    boolean hasPin() {
        return pin != null;
    }

    void closeAccount() {
        if (!status.equals("Active")) {
            throw new IllegalStateException("Account #" + accountNumber + " is already inactive");
        }
        status = "Inactive";
    }

    void reopenAccount() {
        if (!status.equals("Inactive")) {
            throw new IllegalStateException("Account #" + accountNumber + " is already active");
        }
        status = "Active";
    }

    int getAccountNumber() {
        return accountNumber;
    }
    String getName() {
        return name;
    }
    int getAge() {
        return age;
    }
    double getBalance() {
        return balance;
    }
    String getAccountType() {
        return accountType;
    }
    String getStatus() {
        return status;
    }

    void setName(String name) {
        this.name = name;
    }
    void setAge(int age) {
        this.age = age;
    }
}
