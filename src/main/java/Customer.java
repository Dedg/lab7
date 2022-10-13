public class Customer {

    private String name;
    private String surname;
    private String email;
    private CustomerType customerType;
    private Account account;
    private double companyOverdraftDiscount = 1;

    public Customer(String name, String surname, String email, CustomerType customerType, Account account) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.customerType = customerType;
        this.account = account;
    }

    // use only to create companies
    public Customer(String name, String email, Account account, double companyOverdraftDiscount) {
        this.name = name;
        this.email = email;
        this.customerType = CustomerType.COMPANY;
        this.account = account;
        this.companyOverdraftDiscount = companyOverdraftDiscount;
    }

    public void withdraw(double sum, String currency) {
        if (!account.getCurrency().equals(currency)) {
            throw new RuntimeException("Can't extract withdraw " + currency);
        }
        updateAccountMoney(sum);
    }

    private void updateAccountMoney(double sum) {
        if (account.getType().isPremium()) {
            switch (customerType) {
                case COMPANY:
                    // we are in overdraft
                    if (account.getMoney() < 0) {
                        // 50 percent discount for overdraft for premium account
                        account.setMoney(getRemainingMoney(sum) - getOverdraftAmount(sum) * companyOverdraftDiscount / 2);
                    } else {
                        account.setMoney(getRemainingMoney(sum));
                    }
                    break;
                case PERSON:
                    // we are in overdraft
                    if (account.getMoney() < 0) {
                        account.setMoney(getRemainingMoney(sum) - getOverdraftAmount(sum));
                    } else {
                        account.setMoney(getRemainingMoney(sum));
                    }
                    break;
            }
        } else {
            switch (customerType) {
                case COMPANY:
                    // we are in overdraft
                    if (account.getMoney() < 0) {
                        // no discount for overdraft for not premium account
                        account.setMoney(getRemainingMoney(sum) - getOverdraftAmount(sum) * companyOverdraftDiscount);
                    } else {
                        account.setMoney(getRemainingMoney(sum));
                    }
                    break;
                case PERSON:
                    // we are in overdraft
                    if (account.getMoney() < 0) {
                        account.setMoney(getRemainingMoney(sum) - getOverdraftAmount(sum));
                    } else {
                        account.setMoney(getRemainingMoney(sum));
                    }
                    break;
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public Account getAccount() {
        return account;
    }

    public String getCustomerFullName() {
        return name + " " + surname + " ";
    }

    private double getRemainingMoney(double sum) {
        return account.getMoney() - sum;
    }

    private double getOverdraftAmount(double sum) {
        return sum * account.overdraftFee();
    }
}
