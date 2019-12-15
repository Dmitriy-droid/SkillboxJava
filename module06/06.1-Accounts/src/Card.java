public class Card extends Account {
    public static final double WITHDRAW_PERCENT = 0.01;
    private double withRate;

    Card() {
    }

    @Override
    public void withdrawMoney(double amount) {
        withRate = amount + getRate(amount);
        System.out.println("Сумма снятия с процентом :" + withRate);
        super.withdrawMoney(withRate);
    }

    private double getRate(double amount) {
        return amount * WITHDRAW_PERCENT;
    }

}
