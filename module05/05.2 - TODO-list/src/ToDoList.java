import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToDoList {
    private static ArrayList<String> todoList;


    Pattern patternAdd = Pattern.compile("^ADD\\s+(.+)");
    Pattern patternInsert = Pattern.compile("^ADD\\s+(\\d+?)\\s+(.+)");
    Pattern patternEdit = Pattern.compile("^EDIT\\s+(\\d+?)\\s+(.+)");
    Pattern patternDel = Pattern.compile("^DELETE\\s+(\\d+?)$");
    Matcher addMatcher;
    Matcher insertMatcher;
    Matcher editMatcher;
    Matcher delMatcher;

    ToDoList() {
        todoList = new ArrayList<String>() {{
            add("Принести воды");
            add("Позвать мышку");
            add("Покормить кошку");
            add("Заправить машину");
        }};
    }


    public void parseCommand(String inputStr) {
        String command = inputStr;
        insertMatcher = patternInsert.matcher(inputStr);
        addMatcher = patternAdd.matcher(inputStr);
        editMatcher = patternEdit.matcher(inputStr);
        delMatcher = patternDel.matcher(inputStr);

        if (inputStr.equals("QUIT")) {
            System.exit(0);

        } else if (inputStr.equals("LIST")) {
            showItems();

        } else if (insertMatcher.matches()) { //ADD n text
            addItem(Integer.parseInt(insertMatcher.group(1)), insertMatcher.group(2));

        } else if (addMatcher.matches()) { //ADD text
            addItem(todoList.size(), addMatcher.group(1));

        } else if (todoList.size() == 0) {   //Если список пуст, редактировать и удалять нечего
            System.out.println("\nСписок пуст\n");

        } else if (editMatcher.matches()) {   //EDIT n text
            editItem(Integer.parseInt(editMatcher.group(1)), editMatcher.group(2));

        } else if (delMatcher.matches()) {   //DELETE n
            delItem(Integer.parseInt(delMatcher.group(1)));
        } else {
            showHelp();
        }
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
