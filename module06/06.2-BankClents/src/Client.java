public abstract class Client {

    protected String name;
    private double account = .0;

    Client(String name) {
        this.name = name;
    }

    public abstract void showAccount();

    protected void deposit(double amount) {
        if (amount > .0) {
            this.account += amount;
        } else {
            System.out.println("Сумма должна быть больше 0");
        }
        showAccount();
    }

    protected void withdraw(double amount) {
        if (isEnoughMoney(amount)) {
            account -= amount;
        } else {
            System.out.println("Недостаточно средств");
        }
        showAccount();
    }


    protected String getName() {
        return name;
    }

    protected double getAccount() {
        return account;
    }


    protected boolean isEnoughMoney(double amount) {
        return account >= amount;
    }

}
