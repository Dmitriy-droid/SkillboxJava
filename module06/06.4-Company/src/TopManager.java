public class TopManager extends AbstractEmployee {

    Company company;
    private final int BONUS_PERCENT = 150;
    private final double LOWEST_BONUS_INCOME = 10000000.;
    private final double LOWEST_TOP_MANAGER_SALARY = 270000;
    private final double MAX_TOP_MANAGER_SALARY = 480000;


    TopManager(String name, Company company) {
        super(name);
        setFixedSalary(LOWEST_TOP_MANAGER_SALARY, MAX_TOP_MANAGER_SALARY);
        this.company = company;
    }

    public static AbstractEmployee createRandom(String name, Company company) {
        return new TopManager(name, company);
    }

    @Override
    public double getMonthSalary() {
        double salary = getFixedSalary();
        if (company.getIncome() >= LOWEST_BONUS_INCOME) {
            salary += (salary * BONUS_PERCENT) / 100;
        }
        return salary;
    }

}
