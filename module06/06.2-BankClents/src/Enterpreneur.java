public class Enterpreneur extends Client {

    private static final double DEPOSIT_RATE1 = .005;
    private static final double DEPOSIT_RATE2 = .01;
    private static final double THRESHOLD = 1000.;

    public Enterpreneur(String name) {
        super(name);
    }

    @Override
    public void showAccount() {
        System.out.println(getName() + " - предприниматель");
        System.out.println("На счёте: " + getAccount());
    }

    @Override
    public void deposit(double amount) {
        if (amount < THRESHOLD) {
            amount = amount * (1 + DEPOSIT_RATE1);
        } else {
            amount = amount * (1 + DEPOSIT_RATE2);
        }

        super.deposit(amount);
    }

    @Override
    public void withdraw(double amount) {
        super.withdraw(amount);
    }
}
