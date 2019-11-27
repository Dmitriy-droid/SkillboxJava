import java.util.Scanner;

public class Loader
{
    public static void main(String[] args) {
        EmailList emailList = new EmailList();
        emailList.showHelp();
        String command;

        Scanner scanner = new Scanner(System.in);

        for (; ; ) {
            command = scanner.nextLine().trim();
            emailList.parseCommand(command);
        }
    }
}
