import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class Phonebook {

    //Считаем, что номер может быть вида +7 (222) 333-44-11. Если что-то другое (хоть одна буква) - это имя (организация)
    private final Pattern PATTERN_PHONE = Pattern.compile("[-()\\\\+\\d\\s]+");

    //Ключ - имя (организация), Значение - телефон
    TreeMap<String, String> phBook = new TreeMap<>();
    Scanner scanner = new Scanner(System.in);

    public void printContent() {
        if (phBook.isEmpty()) {
            System.out.println("Нет записей");
        } else {
            for (String name : phBook.keySet()) {
                System.out.println(name + " - " + phBook.get(name));
            }
        }
    }

    public String readLine() {
        return scanner.nextLine().trim();
    }

    public boolean isPhone(String text) {
        return PATTERN_PHONE.matcher(text).matches();
    }

    public void processAsAPhone(String phone) {

        String name = findName(phone);
        if (name.isEmpty()) {
            System.out.println("Номер не найден, занести? Введите имя или q для отмены ввода");
            name = readLine();
            if (name.equals("q")) {
                return;  //если не нужно вводить новую запись- выходим
            } else if (isPhone(name)) {
                System.out.println("Кажется, это номер телефона, нету букаф");
                return;
            } else if (!addBookEntry(name, phone)) {
                return;
            }
        }

        //Телефон может быть один на несколько человек (рабочий)
        for (String nameInBook : phBook.keySet()) {
            String phoneInBook = phBook.get(nameInBook);
            if (phoneInBook.equals(phone)) {
                System.out.println(nameInBook + " - " + phoneInBook);
            }
        }
    }

    public void processAsAName(String name) {
        String phone = findPhone(name);
        if (phone.isEmpty()) {
            System.out.println("Абонент не найден, занести? Введите телефон или q для отмены ввода");
            phone = readLine();
            if (phone.equals("q")) {  //если не нужно вводить новую запись- выходим
                return;
            } else if (!isPhone(phone)) {
                System.out.println("Это не номер телефона!");
                return;
            } else if (!addBookEntry(name, phone)) {
                return;
            }
        }

        System.out.println(name + " - " + phone);
    }

    public boolean addBookEntry(String name, String number) {
        //проверка на существование абонента
        if (phBook.containsKey(name)) {
            System.out.println("Абонент " + name + " уже существует: " + phBook.get(name));
            return false;
        } else {
            phBook.put(name, number);
            return true;
        }
    }


    public String findName(String phone) {
        String name = "";
        if (phBook.containsValue(phone)) {
            for (Map.Entry<String, String> record : phBook.entrySet()) {
                if (phone.equals(record.getValue())) {
                    name = record.getKey();
                    break;
                }
            }
        }
        return name;
    }

    public String findPhone(String name) {
        return phBook.getOrDefault(name, "");
    }

}


