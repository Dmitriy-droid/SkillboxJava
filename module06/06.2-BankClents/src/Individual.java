public class Individual extends Client {

    public Individual(String name) {
        super(name);
    }

    @Override
    public void showAccount() {
        System.out.println(getName() + " - Физическое лицо");
        System.out.println("На счёте: " + getAccount());
    }

}
