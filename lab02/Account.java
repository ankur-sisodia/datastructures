/**
 * This class represents a bank account whose current balance is a nonnegative
 * amount in US dollars.
 */
public class Account {

	private int balance;
    
	Account parentAccount;
    
	/** Initialize an account with the given BALANCE. */
	public Account(int balance) {
		this.balance = balance;
		parentAccount = null;
	}
	
	public Account(int balance, Account parentAccount){
		this.balance = balance;
		this.parentAccount = parentAccount;
	}

	/** Return the number of dollars in the account. */
	public int getBalance() {
		return this.balance;
	}

	/** Deposits AMOUNT into the current account. */
	public void deposit(int amount) {
		if (amount < 0) {
			System.out.println("Cannot deposit negative amount.");
		} else {
			this.balance = this.balance + amount;
		}
	}

	/** Subtract AMOUNT from the account if possible. If subtracting AMOUNT
	 *	would leave a negative balance, print an error message and leave the
	 *	balance unchanged.
	 */
	int currentBalance, leftBalance;
	public boolean withdraw(int amount) {
	
		if (amount < 0) {
			System.out.println("Cannot withdraw negative amount.");
			return false;
		} else if (this.balance < amount) {
			
			if (this.parentAccount != null){
				
				amount = amount - this.balance;
				System.out.println("Amount: " + amount);
				boolean success = parentAccount.withdraw(amount);
				if(success) {
					this.balance = 0;
				}
				System.out.println(success);
				return success;
			}
			else {return false;}
			
			/*amount = amount - this.balance;
			this.balance = 0;
			System.out.println("Insufficient funds");
			return false;*/
		} else {
			System.out.println("enough");
			this.balance = this.balance - amount;
			return true;
		}
		
	}

	/** Merge account OTHER into this account by removing all money from OTHER
	 *	and depositing it into this account.
     */
    public void merge(Account other) {
        // TODO Put your own code here
    	this.balance = this.balance + other.getBalance();
    	other = new Account(0);
    }
public static void main(String[] args)
{
	Account d = new Account(50);
	Account c = new Account(100,d);
	Account b = new Account(200,c);
	Account a = new Account(100,b);
	a.withdraw(200);
	System.out.println("a balance: " + a.getBalance());
	System.out.println("b balance: " + b.getBalance());
	System.out.println("c balance: " + c.getBalance());
	System.out.println("d balance: " + d.getBalance());
}

}

