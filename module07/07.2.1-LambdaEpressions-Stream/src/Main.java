import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    private static String staffFile = "data/staff.txt";

    public static void main(String[] args) {
        ArrayList<Employee> staff = loadStaffFromFile();

        System.out.print("Обладатель максимальной зарплаты в 2017 году - ");
        staff.stream()
                .filter(e -> e.getWorkStart().getYear() == 2017) //здесь не стал делать переменную, а то и в
                                                    //выводимом тексте выше придётся городить вставку правильного года
                .max(Comparator.comparing(Employee::getSalary))
                .ifPresent(System.out::println);
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
                        (LocalDate.parse(fragments[2], DateTimeFormatter.ofPattern(Employee.DATE_FORMAT)))));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return staff;
    }
}