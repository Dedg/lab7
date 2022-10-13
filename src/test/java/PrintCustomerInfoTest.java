import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PrintCustomerInfoTest {
    @Test
    public void testMoney() throws Exception {
        Account.AccountType accountType = new Account.AccountType(false);
        Account account = new Account(accountType, 9);
        account.setIban("RO023INGB434321431241");
        account.setMoney(34.0);
        account.setCurrency("EUR");
        Customer customer = new Person("danix", "dan", "dan@mail.com", account);
        PrintCustomerInfo printCustomerInfo = new PrintCustomerInfo(customer);
        assertThat(printCustomerInfo.money(), is("danix dan Account: IBAN: RO023INGB434321431241, Money: 34.0"));
    }

    @Test
    public void testInfo() {
        Account.AccountType premium = new Account.AccountType(false);
        Account account =  new Account(premium, 9);
        Customer customer = new Person("xxx", "xxx", "xxx@mail.com", account);
        PrintCustomerInfo printCustomerInfo = new PrintCustomerInfo(customer);
        assertThat(printCustomerInfo.info(), is("xxx xxx@mail.com"));
    }
}