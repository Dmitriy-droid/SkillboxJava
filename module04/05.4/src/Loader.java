import java.util.Scanner;

public class Loader
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите номер телефона");
        String phoneIn = scanner.nextLine().trim();
        String cleared = phoneIn.replaceAll("[^0-9]", "");
        System.out.printf("Номер: %s", cleared);
    }
}
