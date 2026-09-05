public class CurrentAccount extends Accounts {
    private double overdraftLimit;

    public CurrentAccount(int accountNumber, String accountHolderName, double initialBalance) {
        this(accountNumber, accountHolderName, 18, initialBalance, 25000.0);
    }

    public CurrentAccount(int accountNumber, String accountHolderName, int age, double initialBalance,
            double overdraftLimit) {
        super(accountNumber, accountHolderName, age, initialBalance, "CURRENT");
        this.overdraftLimit = overdraftLimit;
    }

    public CurrentAccount(int accountNumber, String accountHolderName, double initialBalance,
            double overdraftLimit) {
        this(accountNumber, accountHolderName, 18, initialBalance, overdraftLimit);
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }
}