public abstract class AbstractEmployee implements Comparable<AbstractEmployee> {

    private String name;
    private double salary;

    AbstractEmployee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double getMonthSalary();

    public double getFixedSalary() {
        return salary;
    }

    protected void setFixedSalary(double lowBound, double topBound) {
        salary = lowBound + (Math.random() * (topBound - lowBound));
    }

    public int compareTo(AbstractEmployee anotherEmployee) {
        if (this.getMonthSalary() > anotherEmployee.getMonthSalary())
            return 1;
        else if (this.getMonthSalary() == anotherEmployee.getMonthSalary())
            return 0;
        else
            return -1;
    }
}
