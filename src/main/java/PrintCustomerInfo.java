public class PrintCustomerInfo {
    private final Customer customer;

    public PrintCustomerInfo(Customer customer) {
        this.customer = customer;
    }

    public String daysOverdrawn() {
        String accountDescription = "Account: IBAN: " + customer.getAccount().getIban() + ", Days Overdrawn: " + customer.getAccount().getDaysOverdrawn();
        return customer.getCustomerFullName() + accountDescription;
    }

    public String money() {
        String accountDescription = "";
        accountDescription += "Account: IBAN: " + customer.getAccount().getIban() + ", Money: " + customer.getAccount().getMoney();
        return customer.getCustomerFullName() + accountDescription;
    }
}