import java.util.Scanner;

public class Loader {
    public static void main(String[] args) {
        final int CONTAINER_CAPACITY = 27; //27 ящиков в контейнере
        final int TRUCK_CAPACITY = 12; // 12 контейнеров в грузовике

        System.out.println("Введите количество ящиков:");
        Scanner scanner = new Scanner(System.in);
        int totalBoxes = scanner.nextInt();

        if (totalBoxes == 0) {
            System.out.println("требуется некоторое количество ящиков");
            return;
        }

        int currentContainer = 1;
        int currentTruck = 1;

        for (int i = 1; i <= totalBoxes; i++) {

            //если количество ящиков превысило требуемое для грузовика, выдаём новый грузовик
            if ((i - 1) % (CONTAINER_CAPACITY * TRUCK_CAPACITY) == 0) {
                System.out.println("Грузовик " + currentTruck++ + ":");
            }

            //если количество ящиков превысило требуемое для контейнера, выдаём новый контейнер
            if ((i - 1) % (CONTAINER_CAPACITY) == 0) {
                System.out.println("    Контейнер " + currentContainer++ + ":");
            }

            System.out.println("        Ящик " + i);

        }
    }
}
