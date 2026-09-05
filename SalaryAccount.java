public class SalaryAccount extends Accounts {
    private String employerName;
    private int inactiveMonths;

    public SalaryAccount(int accountNumber, String accountHolderName, double initialBalance,
            String employerName) {
        this(accountNumber, accountHolderName, 18, initialBalance, employerName, 0);
    }

    public SalaryAccount(int accountNumber, String accountHolderName, int age, double initialBalance,
            String employerName, int inactiveMonths) {
        super(accountNumber, accountHolderName, age, initialBalance, "SALARY");
        this.employerName = employerName;
        this.inactiveMonths = inactiveMonths;
    }

    public SalaryAccount(int accountNumber, String accountHolderName, double initialBalance,
            String employerName, int inactiveMonths) {
        this(accountNumber, accountHolderName, 18, initialBalance, employerName, inactiveMonths);
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public int getInactiveMonths() {
        return inactiveMonths;
    }

    public void setInactiveMonths(int inactiveMonths) {
        this.inactiveMonths = inactiveMonths;
    }
}