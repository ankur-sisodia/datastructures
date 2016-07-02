import static org.junit.Assert.*;

/**
 * Created by asisodia on 7/2/2016.
 */
public class AccountTest {
    @org.junit.Test
    public void testInit() {
        Account a = new Account(500);
        assertTrue(a.getBalance() == 500);
    }

    @org.junit.Test
    public void testInvalidArgs() {
        Account a = new Account(500);
        a.deposit(-500);
        assertTrue(a.getBalance() == 500);
        a.withdraw(-500);
        assertTrue(a.getBalance() == 500);
    }

    @org.junit.Test
    public void testOverdraft() {
        Account a = new Account(500);
        a.withdraw(1000);
        assertTrue(a.getBalance() == 500);
    }

    public void testDeposit() {
        Account a = new Account(500);
        a.deposit(1000);
        assertTrue(a.getBalance() == 1500);

    }

    public void testWithdraw() {
        Account a = new Account(500);
        a.withdraw(100);
        assertTrue(a.getBalance() == 400);
    }



}