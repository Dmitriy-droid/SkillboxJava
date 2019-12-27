import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

public class Company {

    private String companyName;
    private int income;

    public TreeSet<AbstractEmployee> staff; //будет сразу отсортирован по зарплате

    Company(String name, int income) {
        this.companyName = name;
        this.income = income;
        staff = new TreeSet<>();
    }


    public void hire(AbstractEmployee person) {
        staff.add(person);
    }

    public void hireAll(ArrayList<AbstractEmployee> list) {
        for (AbstractEmployee e : list) {
            hire(e);
        }
    }

    public void fire(AbstractEmployee e) {
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


    private ArrayList<AbstractEmployee> getSalaryStaff(int count, boolean isTop) {

        int numberOfEmloyees = staff.size();

        if (count > numberOfEmloyees) {
            count = numberOfEmloyees;
        }
        ArrayList<AbstractEmployee> list = new ArrayList<>(count);
        Iterator<AbstractEmployee> itr;

        if (isTop) {
            itr = staff.descendingIterator();
        } else {
            itr = staff.iterator();
        }

        for (int i = 0; i < count; i++) {
            list.add(itr.next());
        }
        return list;
    }

    public ArrayList<AbstractEmployee> getTopSalaryStaff(int count) {
        return getSalaryStaff(count, true);
    }

    public ArrayList<AbstractEmployee> getLowestSalaryStaff(int count) {
        return getSalaryStaff(count, false);
    }

}
