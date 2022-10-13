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
        switch (customerType) {
            case COMPANY:
                if (isOverdraft()) {
                    account.setMoney(getRemainingMoney(sum) - getOverdraftAmount(sum) * getCompanyOverdraftPercentDiscount());
                } else {
                    account.setMoney(getRemainingMoney(sum));
                }
                break;
            case PERSON:
                if (isOverdraft()) {
                    account.setMoney(getRemainingMoney(sum) - getOverdraftAmount(sum));
                } else {
                    account.setMoney(getRemainingMoney(sum));
                }
                break;
        }
    }

    private double getCompanyOverdraftPercentDiscount() {
        return account.getType().isPremium() ? companyOverdraftDiscount / 2 : companyOverdraftDiscount;
    }

    private boolean isOverdraft() {
        return account.getMoney() < 0;
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
