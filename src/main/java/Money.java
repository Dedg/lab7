public class Money {
    private double value;
    private String currency;

    public Money() {
    }

    public void setAmount(double amount) {
        this.value = amount;
    }

    public double getAmount() {
        return this.value;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return this.currency;
    }
}