public class Person extends Customer {
    public Person(String name, String surname, String email, Account account) {
        super(name, surname, email, CustomerType.PERSON, account);
    }

    @Override
    void updateAccountMoney(double sum) {
        getAccount().updateAccountMoney(sum, 1);
    }
}
