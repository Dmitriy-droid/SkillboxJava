import java.util.Scanner;
import java.util.regex.Pattern;

public class Loader {
    private static Pattern compiledPattern;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите ФИО в формате \"Фамилия Имя Отчество\"");
        String fio = scanner.nextLine().trim();
        String[] fullNameArray = fio.split("\\s+", 3); //Лишние слова просто отсечём.

        if (fullNameArray.length < 2)
        {
            System.out.println("Недостаточно данных");
            return;
        }

        String lastName = fullNameArray[0].trim();
        String firstName = fullNameArray[1].trim();
        String surName = (fullNameArray.length == 3)? fullNameArray[2].trim() :""; //отчества может и не быть

        //Учтём двойные имена-фамилии-отчества). Остальные тонкости типа О'Генри или ещё чего не предусматриваю
        String patternStr = "^[А-ЯЁ][а-яё]*(?:-[А-ЯЁ][а-яё]*)?$";
        compiledPattern = Pattern.compile(patternStr);

        boolean isOk = true;
        isOk = isOk && isNameMatches("Фамилия", lastName);
        isOk = isOk && isNameMatches("Имя", firstName);
        isOk = isOk && (surName.isEmpty() || isNameMatches("Отчество", surName));
        if (isOk) {
            System.out.println("Фамилия: " + lastName);
            System.out.println("Имя: " + firstName);
            if (!surName.equals("")) {
                System.out.println("Отчество: " + surName);
            }
        }
    }


    public static boolean isNameMatches (String kindOfName, String name) {
        if (!compiledPattern.matcher(name).matches()) {
            System.out.println("Неверный ввод: (" + kindOfName + ")");
            return false;
        }
        return true;
    }
}