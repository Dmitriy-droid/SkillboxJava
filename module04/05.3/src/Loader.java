import java.util.Scanner;
import java.util.regex.Pattern;

public class Loader {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите ФИО в формате \"Фамилия Имя Отчество\"");
        String fio = scanner.nextLine().trim();
        String[] fullNameArray = fio.split("\\s+", 3); //Лишние слова просто отсечём.
        String surName = ""; //отчества может и не быть

        //Учтём двойные имена-фамилии-отчества). Остальные тонкости типа О'Генри или ещё чего не предусматриваю
        String pattern = "^[А-ЯЁ][а-яё]*(?:-[А-ЯЁ][а-яё]*)?$";

        String lastName = fullNameArray[0].trim();
        String firstName = fullNameArray[1].trim();

        if (fullNameArray.length > 2) {
            surName = fullNameArray[2].trim();
        }

        if (!Pattern.compile(pattern).matcher(lastName).matches()) {
            System.out.println("Неверно введена Фамилия");
        }
        else if (!Pattern.compile(pattern).matcher(firstName).matches()) {
            System.out.println("Неверно введено Имя");
        }
        else if ((!surName.equals(""))&&(!Pattern.compile(pattern).matcher(surName).matches()))
        {
            System.out.println("Неверно введено Отчество");
        }
        else {
            System.out.println("Фамилия: " + lastName);
            System.out.println("Имя: " + firstName);
            if (!surName.equals("")) {
                System.out.println("Отчество: " + surName);
            }
        }
    }
}