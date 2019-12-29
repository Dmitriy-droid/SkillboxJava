import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    private static String staffFile = "data/staff.txt";
    private static String dateFormat = "dd.MM.yyyy";

    public static void main(String[] args) {
        ArrayList<Employee> staff = loadStaffFromFile();

        //Вообще, этот вариант мне кажется симпатичнее
        //Collections.sort(staff, (Comparator.comparing(Employee::getSalary)).thenComparing(Employee::getName));
        //но если нужно именно в виде лямбд

        Collections.sort(staff, (
                Comparator
                        .<Employee, Integer>comparing(e -> e.getSalary())
                        .thenComparing(e -> e.getName())
        ));
        //так и не понял, почему сначала понадобилось <Employee, Integer>, а для thenComparing ничего не нужно


        for (Employee employee : staff) {
            System.out.println(employee);
        }

    }

    private static ArrayList<Employee> loadStaffFromFile() {
        ArrayList<Employee> staff = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(staffFile));
            for (String line : lines) {
                String[] fragments = line.split("\t");
                if (fragments.length != 3) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                staff.add(new Employee(
                        fragments[0],
                        Integer.parseInt(fragments[1]),
                        (new SimpleDateFormat(dateFormat)).parse(fragments[2])
                ));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return staff;
    }
}