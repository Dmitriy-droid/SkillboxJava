import java.util.Scanner;

public class Loader {
    public static void main(String[] args) {
        ToDoList myToDoList = new ToDoList();
        myToDoList.showHelp();
        String command;

        Scanner scanner = new Scanner(System.in);

        for (; ; ) {
            command = scanner.nextLine().trim();
            myToDoList.parseCommand(command);
        }
    }
}
