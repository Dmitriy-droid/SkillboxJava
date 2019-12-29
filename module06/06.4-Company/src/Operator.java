public class Operator extends AbstractEmployee {

    private final static double LOWEST_OPERATOR_SALARY = 45000;
    private final static double MAX_OPERATOR_SALARY = 98000;

    Operator(String name) {
        super(name);
        setFixedSalary(LOWEST_OPERATOR_SALARY, MAX_OPERATOR_SALARY);
    }

    public static Operator createRandom(String name) {
        return new Operator(name);
    }

    @Override
    public double getMonthSalary() {
        return getFixedSalary();
    }

}
