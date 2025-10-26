/**
 * Bank class that simulates basic banking operations
 * Accounts are 1-indexed (account numbers start from 1)
 */
class Bank {
    // Array to store account balances
    private long[] accountBalances;
    // Total number of accounts in the bank
    private int numberOfAccounts;

    /**
     * Constructor to initialize the bank with initial account balances
     * @param balance Array of initial balances for each account
     */
    public Bank(long[] balance) {
        this.accountBalances = balance;
        this.numberOfAccounts = balance.length;
    }

    /**
     * Transfer money from one account to another
     * @param account1 Source account number (1-indexed)
     * @param account2 Destination account number (1-indexed)
     * @param money Amount of money to transfer
     * @return true if transfer is successful, false otherwise
     */
    public boolean transfer(int account1, int account2, long money) {
        // Validate both account numbers exist and source account has sufficient balance
        if (account1 > numberOfAccounts || account2 > numberOfAccounts || 
            accountBalances[account1 - 1] < money) {
            return false;
        }
      
        // Deduct money from source account
        accountBalances[account1 - 1] -= money;
        // Add money to destination account
        accountBalances[account2 - 1] += money;
      
        return true;
    }

    /**
     * Deposit money into an account
     * @param account Account number (1-indexed)
     * @param money Amount of money to deposit
     * @return true if deposit is successful, false otherwise
     */
    public boolean deposit(int account, long money) {
        // Validate account number exists
        if (account > numberOfAccounts) {
            return false;
        }
      
        // Add money to the account
        accountBalances[account - 1] += money;
      
        return true;
    }

    /**
     * Withdraw money from an account
     * @param account Account number (1-indexed)
     * @param money Amount of money to withdraw
     * @return true if withdrawal is successful, false otherwise
     */
    public boolean withdraw(int account, long money) {
        // Validate account number exists and has sufficient balance
        if (account > numberOfAccounts || accountBalances[account - 1] < money) {
            return false;
        }
      
        // Deduct money from the account
        accountBalances[account - 1] -= money;
      
        return true;
    }
}

/**
 * Your Bank object will be instantiated and called as such:
 * Bank obj = new Bank(balance);
 * boolean param_1 = obj.transfer(account1,account2,money);
 * boolean param_2 = obj.deposit(account,money);
 * boolean param_3 = obj.withdraw(account,money);
 */
