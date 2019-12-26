public class TopManager extends AbstractEmployee {

    Company company;
    private final int BONUS_PERCENT = 150;

    TopManager(String name, Company company) {
        super(name);
        setSalary(Company.LOWEST_TOP_MANAGER_SALARY, Company.MAX_TOP_MANAGER_SALARY);
        this.company = company;
    }

    @Override
    public double getMonthSalary() {
        double salary = getSalary();
        if (company.getIncome() >= 10000000.) {
            salary += (salary * BONUS_PERCENT) / 100;
        }
        return salary;
    }

}
