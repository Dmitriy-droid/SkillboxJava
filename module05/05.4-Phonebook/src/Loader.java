
public class Loader {
    public static void main(String[] args) {


        Phonebook phonebook = new Phonebook();

        for (; ; ) {
            System.out.println("Введите имя или телефон. Вывод списка - LIST, выход - EXIT");
            String command = phonebook.readLine();

            if (command.isEmpty()) {
                continue;

            } else if (command.equals("EXIT")) {
                System.exit(0);

            } else if (command.equals("LIST")) {
                phonebook.printContent();

            } else if (phonebook.isPhone(command)) {
                phonebook.processAsAPhone(command);

            } else {  //если не номер, значит, абонент
                phonebook.processAsAName(command);
            }

        }
    }
}



