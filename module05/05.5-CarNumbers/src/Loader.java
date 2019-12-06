import java.util.Scanner;

public class Loader {
    public static void main(String[] args) {

        CarNumbers carNumbers = new CarNumbers();
        Scanner scanner = new Scanner(System.in);

        for (; ; ) {
            System.out.println("Введите номер. Выход - EXIT");
            String inText = scanner.nextLine();

            if (inText.isEmpty()) {
                continue;

            } else if (inText.equals("EXIT")) {
                System.exit(0);

            } else if (carNumbers.isItCarNumber(inText)) {
                System.out.println("Всего номеров: " + carNumbers.getTotalNumbers());
                carNumbers.measureDuration(inText);
            } else {
                System.out.println("\nНеправильный формат номера\n");
            }
        }
    }

}