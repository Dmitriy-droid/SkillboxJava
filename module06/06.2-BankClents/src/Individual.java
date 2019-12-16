public class Individual extends Client {

    public Individual(String name) {
        super(name);
    }

    @Override
    public void showAccount() {
        System.out.println(getName() + " - Физическое лицо");
        System.out.println("На счёте: " + getAccount());
    }

    @Override
    public void deposit(double amount) {
        super.deposit(amount);
        showAccount();
    }

    @Override
    public void withdraw(double amount) {
        super.withdraw(amount);
        showAccount();
    }

}
