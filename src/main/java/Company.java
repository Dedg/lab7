public class Company extends Customer {
    public Company(String name, String email, Account account, double companyOverdraftDiscount) {
        super(name, email, account, companyOverdraftDiscount);
    }

    @Override
    void updateAccountMoney(double sum) {
        if (isOverdraft()) {
            getAccount().setMoney(getRemainingMoney(sum) - getOverdraftAmount(sum) * getCompanyOverdraftPercentDiscount());
        } else {
            getAccount().setMoney(getRemainingMoney(sum));
        }
    }
}
