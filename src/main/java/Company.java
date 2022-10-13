public class Company extends Customer {
    public Company(String name, String email, Account account, double companyOverdraftDiscount) {
        super(name, email, account, companyOverdraftDiscount);
    }

    @Override
    void updateAccountMoney(double sum) {
        getAccount().updateAccountMoney(sum, getCompanyOverdraftPercentDiscount());
    }
}
