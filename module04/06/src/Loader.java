import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Loader
{
    static final DateTimeFormatter IN_DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    static final DateTimeFormatter OUT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy - EEEE");

    public static void main(String[] args) {
        Locale.setDefault(new Locale("ru", "RU"));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите дату в виде 21.08.1990");
        String birthDateStr = scanner.nextLine().trim();

        try {
            LocalDate birthday = LocalDate.parse(birthDateStr, IN_DATE_FORMAT);
            LocalDate today = LocalDate.now();

            int counter = 0;
            do {
                System.out.println(counter + " - " + birthday.format(OUT_DATE_FORMAT));
                birthday = birthday.plusYears(1);
                counter++;
            } while (today.isAfter(birthday));

        } catch (Exception e) {
            System.out.println("Неверно введена дата");
        }

    }
}
