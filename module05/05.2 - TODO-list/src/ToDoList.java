import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToDoList {
    private static ArrayList<String> todoList;


    Pattern patternAdd;
    Pattern patternInsert;
    Pattern patternEdit;
    Pattern patternDel;
    Matcher matcher;

    ToDoList() {
        todoList = new ArrayList<String>() {{
            add("Принести воды");
            add("Позвать мышку");
            add("Покормить кошку");
            add("Заправить машину");
        }};

        patternAdd = Pattern.compile("^ADD\\s+(.+)");
        patternInsert = Pattern.compile("^ADD\\s+(\\d+?)\\s+(.+)");
        patternEdit = Pattern.compile("^EDIT\\s+(\\d+?)\\s+(.+)");
        patternDel = Pattern.compile("^DELETE\\s+(\\d+?)$");
    }


    public void parseCommand(String inputStr) {
        String command = inputStr;

        if (inputStr.equals("QUIT")) {
            System.exit(0);
        }

        if (inputStr.equals("LIST")) {
            showItems();
            return;
        }

        //ADD n text
        matcher = patternInsert.matcher(inputStr);
        if (matcher.matches()) {
            addItem(Integer.parseInt(matcher.group(1)), matcher.group(2));
            return;
        }

        //ADD text
        matcher = patternAdd.matcher(inputStr);
        if (matcher.matches()) {
            addItem(todoList.size(), matcher.group(1));
            return;
        }

        //Если список пуст, редактировать и удалять нечего
        if (todoList.size() == 0) {
            System.out.println("\nСписок пуст\n");
            return;
        }

        //EDIT n text
        matcher = patternEdit.matcher(inputStr);
        if (matcher.matches()) {
            editItem(Integer.parseInt(matcher.group(1)), matcher.group(2));
            return;
        }

        //DELETE n
        matcher = patternDel.matcher(inputStr);
        if (matcher.matches()) {
            delItem(Integer.parseInt(matcher.group(1)));
            return;
        }
        showHelp();
    }


    private void delItem(int itemIndex) {

        if (indexOK(itemIndex)) {
            todoList.remove(itemIndex);
            showItems();
        }
    }


    private void editItem(int itemIndex, String text) {

        if (indexOK(itemIndex)) {
            todoList.set(itemIndex, text);
            showItems();
        }
    }


    private void addItem(int itemIndex, String text) {
        if (itemIndex > todoList.size()) {
            itemIndex = todoList.size();
        }

        todoList.add(itemIndex, text);
        showItems();
    }


    private boolean indexOK(int itemIndex) {

        if (itemIndex > todoList.size() - 1) {
            System.out.println("\nНеправильный номер записи, должно быть от 0 до " + (todoList.size() - 1));
            return false;
        }
        return true;
    }


    private static void showItems() {
        if (todoList.size() == 0) {
            System.out.println("\nСписок пуст\n");
            return;
        }

        for (String item : todoList) {
            System.out.println(todoList.indexOf(item) + " - " + item);
        }
    }

    public static void showHelp() {
        String helpText = "\nПросмотр списка: LIST\n" +
                "Добавить запись: ADD\n" +
                "Вставить запись: ADD номер записи\n" +
                "Редактировать запись: EDIT номер записи\n" +
                "Удалить запись: DEL  номер записи\n" +
                "Выйти: QUIT\n";

        System.out.println(helpText);
    }

}
