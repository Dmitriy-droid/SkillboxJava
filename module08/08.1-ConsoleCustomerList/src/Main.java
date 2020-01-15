import java.util.Scanner;

public class Main
{

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        CustomerStorage executor = new CustomerStorage();
        for(;;)
        {
            try {
                String command = scanner.nextLine();
                String[] tokens = command.split("\\s+", 2);
                if (tokens[0].equals("add")) {
                    executor.addCustomer(tokens[1]);
                } else if (tokens[0].equals("list")) {
                    executor.listCustomers();
                } else if (tokens[0].equals("remove")) {
                    executor.removeCustomer(tokens[1]);
                } else if (tokens[0].equals("count")) {
                    System.out.println("There are " + executor.getCount() + " customers");
                } else if (tokens[0].equals("help")) {
                    System.out.println(executor.helpText);
                } else if (tokens[0].equals("exit")) {
                    System.exit(0);
                } else {
                    System.out.println(executor.commandError);
                }
            }
            catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
