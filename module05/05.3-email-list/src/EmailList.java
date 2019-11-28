import java.util.Iterator;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailList {
    private static TreeSet<String> emailList;

    //Паттерн для e-mail взял "рекомендованный" (пункт 5) отсюда:
    //https://howtodoinjava.com/regex/java-regex-validate-email-address/
    private static final String MAIL_REGEX =
            "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    private static final Pattern PATTERN_ADD = Pattern.compile("^ADD\\s+(?<address>.+)");
    private static final Pattern PATTERN_DEL = Pattern.compile("^DEL\\s+(?<address>.+)");


    EmailList() {
        emailList = new TreeSet<>();
    }


    public void parseCommand(String inputStr) {
        String command = inputStr;
        Matcher addMatcher = PATTERN_ADD.matcher(inputStr);
        Matcher delMatcher = PATTERN_DEL.matcher(inputStr);

        if (inputStr.equals("EXIT")) {
            System.exit(0);

        } else if (inputStr.equals("LIST")) {
            showItems();

        } else if (addMatcher.matches()) { //ADD e-mail address
            addItem(addMatcher.group("address"));

        } else if (emailList.isEmpty()) {   //Если список пуст, удалять нечего
            System.out.println("\nСписок пуст\n");

        } else if (delMatcher.matches()) {   //DEL e-mail address
            delItem(delMatcher.group("address"));

        } else {
            showHelp();
        }
    }


    private void addItem(String item) {
        if (isValid(item)) {
            emailList.add(item);
            showItems();
        } else {
            System.out.println("Не похоже на e-mail");
        }
    }


    private void delItem(String item) {

        if (!emailList.remove(item)) {
            System.out.println("Такого нет в списке\n");
        }
        showItems();
    }


    private static void showItems() {
        if (emailList.isEmpty()) {
            System.out.println("\nСписок пуст\n");
        } else {
            emailList.forEach(System.out::println);
            }
        }



    public static boolean isValid(String address) {
        return address.matches(MAIL_REGEX);
    }

    public static void showHelp() {
        String helpText = "\nПросмотр списка: LIST\n" +
                "Добавить email: ADD user@server.domain\n" +
                "Удалить email: DEL user@server.domain\n" +
                "Выйти: EXIT\n";

        System.out.println(helpText);
    }
}
