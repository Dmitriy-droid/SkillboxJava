public class Operator extends AbstractEmployee {


    Operator(String name) {
        super(name);
        setSalary(Company.LOWEST_OPERATOR_SALARY, Company.MAX_OPERATOR_SALARY);
    }

    @Override
    public double getMonthSalary() {
        return getSalary();
    }

}
