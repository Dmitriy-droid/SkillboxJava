public class Manager extends AbstractEmployee {

    private final double MAX_EARN_MONEY = 200000.; //тут надо регулировать
    private final double MANAGER_BONUS_PERCENT = .05;
    double earnedMoney;


    Manager(String name) {
        super(name);
        earnedMoney = Math.random() * MAX_EARN_MONEY; //сколько прибыли принёс
        setSalary(Company.LOWEST_MANAGER_SALARY, Company.MAX_MANAGER_SALARY);
    }

    @Override
    public double getMonthSalary() {
        return getSalary() + earnedMoney * MANAGER_BONUS_PERCENT;
    }

}
