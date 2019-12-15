
public class Account {
    private double amount;

    Account() {
        amount = 0.;
    }

    public void showAmount() {
        System.out.println("На счете: " + amount);
    }

    public void addMoney(double amount) {
        if (amount > 0.) {
            this.amount += amount;
            showAmount();
        } else {
            System.out.println("Сумма должна быть положительной.");
        }
    }

    public void withdrawMoney(double amount) {
        if (isEnoughMoney(amount)) {
            this.amount -= amount;

        } else {
            System.out.println("Сумма снятия больше, чем на счету.");
        }
        showAmount();
    }

    private boolean isEnoughMoney(double sum) {
        return (sum <= this.amount);
    }

}
