import java.util.HashMap;
import java.util.regex.Pattern;

public class CustomerStorage {
    private HashMap<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    private final Pattern PATTERN_NAME = Pattern.compile("^[А-ЯЁ][а-яё]*(?:-[А-ЯЁ][а-яё]*)?$");
    private final Pattern PATTERN_PHONE = Pattern.compile("^\\+[0-9]+");
    private final Pattern PATTERN_MAIL = Pattern.compile(
            "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");


    public static String addCommand = "add Василий Петров " +
            "vasily.petrov@gmail.com +79215637722";
    public static String commandExamples = "\t" + addCommand + "\n" +
            "\tlist\n" +
            "\tcount\n" +
            "\tremove Василий Петров\n" +
            "\texit";
    public static String commandError = "Wrong command! Available command examples: \n" +
            commandExamples;
    public static String helpText = "Command examples:\n" + commandExamples;


    //проверка на количество аргументов, формат имени, адреса и телефона
    public void addCustomer(String data) {
        String[] components = data.split("\\s+");

        if (components.length != 4) {
            throw new IllegalArgumentException("Wrong format. Correct format: \n" + addCommand);
        }

        if (!(isNameMatches(components[0]) && isNameMatches(components[1]))) {
            throw new IllegalArgumentException("Wrong name format. Correct format: \nВасилий Петров\n");
        }

        String name = components[0] + " " + components[1];

        if (!isEmailMatches(components[2])) {
            throw new IllegalArgumentException("Wrong e-mail format. Correct format: \nvasily.petrov@gmail.com\n");
        }

        if (!isPhoneMatches(components[3])) {
            throw new IllegalArgumentException("Wrong phone format. Correct format: \n+79215637722\n");
        }
        storage.put(name, new Customer(name, components[3], components[2]));
    }

    // +проверка, не пуст ли список
    public void listCustomers() {
        if (storage.isEmpty()) {
            System.out.println("List is empty.\n");
        } else {
            storage.values().forEach(System.out::println);
        }
    }

    // +проверка на существование
    public void removeCustomer(String name) {
        if (storage.containsKey(name)) {
            storage.remove(name);
        } else {
            System.out.println("Customer " + name + " is not in the list.\n");
        }
    }


    public int getCount() {
        return storage.size();
    }


    private boolean isNameMatches(String text) {
        return PATTERN_NAME.matcher(text).matches();
    }

    private boolean isPhoneMatches(String text) {
        return PATTERN_PHONE.matcher(text).matches();
    }

    private boolean isEmailMatches(String text) {
        return PATTERN_MAIL.matcher(text).matches();
    }

}
