public class TestAccountSubclasses {
    public static void main(String[] args) {
        System.out.println("=== Activity 7: Account Subclasses Test ===");

        SavingsAccount savingsAccount = new SavingsAccount(2001, "Rahul Sharma", 10000.0);
        System.out.println("Savings Account Created: Balance Rs " + savingsAccount.getBalance()
                + " | Min Balance: Rs " + savingsAccount.getMinBalance());

        CurrentAccount currentAccount = new CurrentAccount(2002, "Priya Singh", 5000.0);
        System.out.println("Current Account Created: Overdraft Limit Rs "
                + currentAccount.getOverdraftLimit());

        FixedDepositAccount fixedDepositAccount = new FixedDepositAccount(2003, "Amit Kumar", 10000.0);
        System.out.println("Fixed Deposit Created: Tenure " + fixedDepositAccount.getTenureMonths()
                + " months | Interest: " + fixedDepositAccount.getInterestRate() + "%");

        SalaryAccount salaryAccount = new SalaryAccount(2004, "Neha Verma", 50000.0, "Infosys");
        System.out.println("Salary Account Created: Employer " + salaryAccount.getEmployerName());

        System.out.println("All subclasses instantiated successfully!");
    }
}
