import java.time.LocalDateTime;

public class Deposit extends Account {
    private LocalDateTime lastRefill;

    Deposit() {
    }


    @Override
    public void addMoney(double amount) {
        super.addMoney(amount);
        lastRefill = LocalDateTime.now();
    }

    @Override
    public void withdrawMoney(double amount) {
        if (lastRefill.plusMonths(1).isBefore(LocalDateTime.now())) {
            super.withdrawMoney(amount);

        } else {
            System.out.println("Снять можно только через месяц после пополнения.");
        }
    }
}
