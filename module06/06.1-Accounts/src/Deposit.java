import java.time.LocalDateTime;

public class Deposit extends Account
{
    private LocalDateTime lastRefill;

    Deposit() {
        super();
    }


    public void addMoney(double amount) {
        if (amount > 0.) {
            this.amount += amount;
            lastRefill = LocalDateTime.now();
            showAmount();
        } else {
            System.out.println("Сумма должна быть положительной.");
        }
    }

    public void withdrawMoney(double amount) {
        if (amount <= this.amount) {
            if (lastRefill.plusMonths(1).isBefore(LocalDateTime.now())) {
                this.amount -= amount;
            } else {
                System.out.println("Снять можно только через месяц после пополнения.");
            }
        } else {
            System.out.println("Сумма снятия больше, чем на счету.");
        }
        showAmount();
    }

}
