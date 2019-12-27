import java.util.ArrayList;

public class Loader {
    public static void main(String[] args) {

        Company company = new Company("ABC-Zimbabwe", 12000000);

        //Наберём сотрудников из NameList, там их 500, поэтому проверки выхода не делаю
        int currentNameInList = 0;

        for (int i = currentNameInList; i < currentNameInList + 180; i++) {
            company.hire(Operator.createRandom(NameList.names[i]));
        }

        for (int i = currentNameInList; i < currentNameInList + 80; i++) {
            company.hire(Manager.createRandom(NameList.names[i]));
        }

        for (int i = currentNameInList; i < currentNameInList + 10; i++) {
            company.hire(TopManager.createRandom(NameList.names[i], company));
        }

        ArrayList<AbstractEmployee> topSalary = company.getTopSalaryStaff(15);
        ArrayList<AbstractEmployee> lowestSalary = company.getLowestSalaryStaff(15);

        System.out.println("\n15 самых низких зарплат\n");
        for (AbstractEmployee person : lowestSalary) {
            System.out.println(person.getName() + " - " + (int) person.getFixedSalary());
        }

        System.out.println("\n15 самых высоких зарплат\n");
        for (AbstractEmployee person : topSalary) {
            System.out.println(person.getName() + " - " + (int) person.getFixedSalary());
        }

    }
}
