import java.util.Iterator;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailList {
    private static TreeSet<String> emailList;

    //Паттерн для e-mail взял "рекомендованный" (пункт 5) отсюда:
    //https://howtodoinjava.com/regex/java-regex-validate-email-address/
    static private String mailRegEx =
            "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    Pattern patternAdd = Pattern.compile("^ADD\\s+(.+)");
    Pattern patternDel = Pattern.compile("^DEL\\s+(.+)");
    Matcher addMatcher;
    Matcher delMatcher;


    EmailList() {
        emailList = new TreeSet<>();
    }


    public void parseCommand(String inputStr) {
        String command = inputStr;
        addMatcher = patternAdd.matcher(inputStr);
        delMatcher = patternDel.matcher(inputStr);

        if (inputStr.equals("EXIT")) {
            System.exit(0);

        } else if (inputStr.equals("LIST")) {
            showItems();

        } else if (addMatcher.matches()) { //ADD e-mail address
            addItem(addMatcher.group(1));

        } else if (emailList.isEmpty()) {   //Если список пуст, удалять нечего
            System.out.println("\nСписок пуст\n");

        } else if (delMatcher.matches()) {   //DEL e-mail address
            delItem(delMatcher.group(1));

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
            for (Iterator iter = emailList.iterator(); iter.hasNext(); ) {
                System.out.println(iter.next());
            }
        }
    }


    public static boolean isValid(String address) {
        return address.matches(mailRegEx);
    }

    public static void showHelp() {
        String helpText = "\nПросмотр списка: LIST\n" +
                "Добавить email: ADD user@server.domain\n" +
                "Удалить email: DEL user@server.domain\n" +
                "Выйти: EXIT\n";

        System.out.println(helpText);
    }
}
