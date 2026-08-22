public class Accounts {
    private int accountNumber;
    private String name;
    private int age;
    private double balance;
    private String accountType;
    private String status;
    private int pin;

    public static final int MIN_AGE = 18;
    public static final double MIN_BALANCE_SAVINGS = 500.0;
    public static final double MIN_BALANCE_CURRENT = 1000.0;
    

    Accounts(int accountNumber, String name, int age, double initialBalance, String accountType) {        
        this.accountNumber = accountNumber;
        this.name = name;
        this.age = (age >= MIN_AGE) ? age : MIN_AGE;
        this.balance = accountType.equalsIgnoreCase("Savings") ? (initialBalance >= MIN_BALANCE_SAVINGS ? initialBalance : MIN_BALANCE_SAVINGS)
                : (initialBalance >= MIN_BALANCE_CURRENT ? initialBalance : MIN_BALANCE_CURRENT);
        this.accountType = (accountType.equalsIgnoreCase("Savings") || accountType.equalsIgnoreCase("Current")) ? accountType : "Savings";
        this.status = "Active";
    }
    boolean deposit(double amount) {
        if (status.equals("Active") && amount > 0) {
            balance += amount;
            return true;
        } else {
            return false;
        }
    }
    boolean withdraw(double amount, int pin) {
        if (status.equals("Active") && verifyPin(pin) && amount > 0 && amount <= balance && (balance - amount >= (accountType.equalsIgnoreCase("Savings") ? MIN_BALANCE_SAVINGS : MIN_BALANCE_CURRENT))) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }
    boolean setPin(int pin) {
        if (pin >= 1000 && pin <= 9999) {
            this.pin = pin;
            return true;
        }
        return false;
    }

    boolean verifyPin(int pin) {
        return this.pin != 0 && this.pin == pin;
    }

    boolean hasPin() {
        return pin != 0;
    }

    boolean closeAccount() {
        if (status.equals("Active")) {
            status = "Inactive";
            return true;
        }
        return false;
    }
    boolean reopenAccount() {
        if (status.equals("Inactive")) {
            status = "Active";
            return true;
        }
        return false;
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
