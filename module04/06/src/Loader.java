import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Loader
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите дату в виде 21.08.1990");
        String birthDateStr = scanner.nextLine().trim();

        if(!Pattern.compile("([0-3][0-9])[\\.](0[1-9]|1[0-2])[\\.](18|19|20)[0-9]{2}").matcher(birthDateStr).matches())
            System.out.println("Неверно введена дата");
        else {
            Locale.setDefault(new Locale("ru", "RU"));

            DateTimeFormatter inputFormatter  = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate birthday = LocalDate.parse(birthDateStr, inputFormatter);

            DateTimeFormatter outputFormatter  = DateTimeFormatter.ofPattern("dd.MM.yyyy - EEEE");
            LocalDate today = LocalDate.now();

            ArrayList<LocalDate> list = new ArrayList<>();
            list.add(birthday);
            int counter = 0;

            do {
                list.add(list.get(counter).plusMonths(12));
                System.out.println(counter + " - " + list.get(counter).format(outputFormatter));
                counter++;
            } while (today.isAfter(list.get(counter)));
        }
    }
}
