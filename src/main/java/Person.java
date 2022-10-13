public class Person extends Customer {
    public Person(String name, String surname, String email, Account account) {
        super(name, surname, email, CustomerType.PERSON, account);
    }

    @Override
    void updateAccountMoney(double sum) {
        if (isOverdraft()) {
            getAccount().setMoney(getRemainingMoney(sum) - getOverdraftAmount(sum));
        } else {
            getAccount().setMoney(getRemainingMoney(sum));
        }
    }
}
