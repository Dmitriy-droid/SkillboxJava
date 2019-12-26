public abstract class AbstractEmployee implements Employee, Comparable<AbstractEmployee> {
    //кажется, для минимальных условий задачи хватало интерфейса Employee, но я как-то сразу упёрся в то,
//что у сотрудников должны быть имена - и поехало...
    private String name;
    private double salary;

    AbstractEmployee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    protected void setSalary(double lowBound, double topBound) {
        salary = lowBound + (Math.random() * (topBound - lowBound));
    }

    public int compareTo(AbstractEmployee anotherEmployee) {
        if (this.getSalary() > anotherEmployee.getSalary())
            return 1;
        else if (this.getSalary() == anotherEmployee.getSalary())
            return 0;
        else
            return -1;
    }
}
