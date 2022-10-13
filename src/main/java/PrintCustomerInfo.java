public class PrintCustomerInfo {
    private final Customer customer;

    public PrintCustomerInfo(Customer customer) {
        this.customer = customer;
    }

    public String money() {
        String accountDescription = "";
        accountDescription += "Account: IBAN: " + customer.getAccount().getIban() + ", Money: " + customer.getAccount().getMoney();
        return customer.getCustomerFullName() + accountDescription;
    }

    public String info() {
        return customer.getName() + " " + customer.getEmail();
    }
}