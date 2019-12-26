import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.TreeSet;

public class Company {

    private String companyName;
    private int income;

    //ограничения по фиксированным зарплатам в компании.
    // Конечно, для разных компаний должны быть свои правила
    public static final double LOWEST_OPERATOR_SALARY = 45000;
    public static final double MAX_OPERATOR_SALARY = 98000;
    public static final double LOWEST_MANAGER_SALARY = 74000;
    public static final double MAX_MANAGER_SALARY = 140000;
    public static final double LOWEST_TOP_MANAGER_SALARY = 270000;
    public static final double MAX_TOP_MANAGER_SALARY = 480000;

    enum EmployeeType {OPERATOR, MANAGER, TOP_MANAGER}

    public TreeSet<AbstractEmployee> staff; //будет сразу отсортирован по зарплате

    Company(String name, int income) {
        this.companyName = name;
        this.income = income;
        staff = new TreeSet<>();
    }


    public void hire(String name, EmployeeType type) {
        AbstractEmployee person;

        switch (type) {
            case TOP_MANAGER:
                person = new TopManager(name, this);
                break;
            case MANAGER:
                person = new Manager(name);
                break;
            default:
                person = new Operator(name);
                break;
        }
        staff.add(person);
    }

    public void hireAll(ArrayList<String> list, EmployeeType type) {
        for (String e : list) {
            hire(e, type);
        }
    }

    public void fire(Employee e) {
        if (staff.contains(e)) { //вроде бы это лишнее
            staff.remove(e);
        }
    }

    public String getCompanyName() {
        return companyName;
    }


    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }


    public ArrayList<AbstractEmployee> getTopSalaryStaff(int count) {

        int numberOfEmloyees = staff.size();
        if (count > numberOfEmloyees) {
            count = numberOfEmloyees;
        }
        ArrayList<AbstractEmployee> list = new ArrayList<>(count);
        Iterator<AbstractEmployee> itr = staff.descendingIterator();

        for (int i = 0; i < count; i++) {
            list.add(itr.next());
        }
        return list;
    }

    public ArrayList<AbstractEmployee> getLowestSalaryStaff(int count) {
        int numberOfEmloyees = staff.size();
        if (count > numberOfEmloyees) {
            count = numberOfEmloyees;
        }
        ArrayList<AbstractEmployee> list = new ArrayList<>(count);
        Iterator<AbstractEmployee> itr = staff.iterator();

        for (int i = 0; i < count; i++) {
            list.add(itr.next());
        }
        return list;
    }

}
