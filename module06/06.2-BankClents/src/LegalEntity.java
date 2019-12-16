public class LegalEntity extends Client {

    private static final double WITHDRAW_RATE = .01;

    public LegalEntity(String name) {
        super(name);
    }

    @Override
    public void showAccount() {
        System.out.println(getName() + " - юридическое лицо");
        System.out.println("На счёте: " + getAccount());
    }

    @Override
    public void deposit(double amount) {
        super.deposit(amount);
        showAccount();
    }

    @Override
    public void withdraw(double amount) {
        amount = amount * (1 + WITHDRAW_RATE);
        super.withdraw(amount);
        showAccount();
    }

}
