public class Card extends Account {
    private double withRate;

    Card() {
        super();
    }

    public void withdrawMoney(double amount) {
        withRate = amount + getRate(amount);
        if ((withRate) <= this.amount) {
            this.amount -= withRate;
            System.out.println("Сумма снятия с процентом :" + withRate);
        } else {
            System.out.println("Сумма снятия с учётом процента (" + withRate + ") больше, чем на счету.");
        }
        showAmount();
    }

    private double getRate(double amount) {
        return amount * 0.01;
    }

}
