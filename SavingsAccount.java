public class SavingsAccount extends Accounts {
    private double minBalance;
    private double interestRate;

    public SavingsAccount(int accountNumber, String accountHolderName, double initialBalance) {
        this(accountNumber, accountHolderName, 18, initialBalance, 1000.0, 4.0);
    }

    public SavingsAccount(int accountNumber, String accountHolderName, int age, double initialBalance,
            double minBalance, double interestRate) {
        super(accountNumber, accountHolderName, age, initialBalance, "SAVINGS");
        this.minBalance = minBalance;
        this.interestRate = interestRate;
    }

    public SavingsAccount(int accountNumber, String accountHolderName, double initialBalance,
            double minBalance, double interestRate) {
        this(accountNumber, accountHolderName, 18, initialBalance, minBalance, interestRate);
    }

    public void applyInterest() {
        double interest = getBalance() * interestRate / 100.0;
        setBalance(getBalance() + interest);
    }

    public double getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(double minBalance) {
        this.minBalance = minBalance;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}