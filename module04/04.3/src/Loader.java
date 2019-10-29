import java.util.Scanner;

public class Loader
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите ФИО в формате \"Фамилия Имя Отчество\"");
        String fio = scanner.nextLine().trim();
        String lastName = fio.substring (0,fio.indexOf(" ")).trim();
        String firstName = fio.substring (fio.indexOf(" "), fio.lastIndexOf(" ")).trim();
        String surName = fio.substring (fio.lastIndexOf(" ")).trim();

        System.out.println("Фамилия: " + lastName);
        System.out.println("Имя: " + firstName);
        System.out.println("Отчество: " + surName);
    }
}