public class Account {
    private final Money money = new Money();
    private String iban;

    private AccountType type;

    private int daysOverdrawn;

    private Customer customer;

    public Account(AccountType type, int daysOverdrawn) {
        super();
        this.type = type;
        this.daysOverdrawn = daysOverdrawn;
    }

    public double bankcharge() {
        double result = 4.5;

        result += overdraftCharge();

        return result;
    }

    private double overdraftCharge() {
        if (type.isPremium()) {
            double result = 10;
            if (getDaysOverdrawn() > 7)
                result += (getDaysOverdrawn() - 7) * 1.0;
            return result;
        } else
            return getDaysOverdrawn() * 1.75;
    }

    public double overdraftFee() {
        if (type.isPremium()) {
            return 0.10;
        } else {
            return 0.20;
        }
    }

    public int getDaysOverdrawn() {
        return daysOverdrawn;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public void setMoney(double money) {
        this.money.setAmount(money);
    }

    public double getMoney() {
        return money.getAmount();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public AccountType getType() {
        return type;
    }

    public String getCurrency() {
        return money.getCurrency();
    }

    public void setCurrency(String currency) {
        money.setCurrency(currency);
    }

    public double getRemainingMoney(double sum) {
        return getMoney() - sum;
    }

    public double getOverdraftAmount(double sum) {
        return sum * overdraftFee();
    }

    public boolean isOverdraft() {
        return getMoney() < 0;
    }

    void updateAccountMoney(double sum, double discountFactor) {
        if (isOverdraft()) {
            setMoney(getRemainingMoney(sum) - getOverdraftAmount(sum) * discountFactor);
        } else {
            setMoney(getRemainingMoney(sum));
        }
    }

    public String print() {
        return "Account: IBAN: " + getIban() + ", Money: " + getMoney() + ", Account type: " + getType();
    }

    public String daysOverdrawn() {
        return customer.getCustomerFullName() + "Account: IBAN: " + getIban() + ", Days Overdrawn: " + getDaysOverdrawn();
    }

    public static class AccountType {
        private boolean premium;

        AccountType(boolean premium) {
            this.premium = premium;
        }

        public boolean isPremium() {
            return premium;
        }

        @Override
        public String toString() {
            return premium ? "premium" : "normal";
        }
    }
}
