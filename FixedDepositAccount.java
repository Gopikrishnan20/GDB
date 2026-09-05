public class FixedDepositAccount extends Accounts {
    private int tenureMonths;
    private double interestRate;

    public FixedDepositAccount(int accountNumber, String accountHolderName, double initialBalance) {
        this(accountNumber, accountHolderName, 18, initialBalance, 12, 6.5);
    }

    public FixedDepositAccount(int accountNumber, String accountHolderName, int age, double initialBalance,
            int tenureMonths, double interestRate) {
        super(accountNumber, accountHolderName, age, initialBalance, "FIXED_DEPOSIT");
        this.tenureMonths = tenureMonths;
        this.interestRate = interestRate;
    }

    public FixedDepositAccount(int accountNumber, String accountHolderName, double initialBalance,
            int tenureMonths, double interestRate) {
        this(accountNumber, accountHolderName, 18, initialBalance, tenureMonths, interestRate);
    }

    public double calculateMaturityAmount() {
        double monthlyRate = interestRate / 100.0 / 12.0;
        return getBalance() * Math.pow(1 + monthlyRate, tenureMonths);
    }

    public int getTenureMonths() {
        return tenureMonths;
    }

    public void setTenureMonths(int tenureMonths) {
        this.tenureMonths = tenureMonths;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}