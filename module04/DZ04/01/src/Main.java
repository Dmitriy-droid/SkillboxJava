public class Main
{
    public static void main(String[] args)
    {
        Container container = new Container();
        container.count += 7843;

        System.out.println("container.count = " + container.count);
        System.out.println("Сумма цифр " + sumDigits(container.count));

    }

    public static Integer sumDigits(Integer number)
    {
        String numAsString = number.toString();
        Integer sum = 0;

//  Второй вариант вроде компактнее
//        for (int i = 0; i < numAsString.length(); i++) {
//            sum += Integer.parseInt(String.valueOf(numAsString.charAt(i)));
//            System.out.println(numAsString.charAt(i));
//        }

        for (char ch : numAsString.toCharArray()) {
            sum += Integer.parseInt(String.valueOf(ch));
            System.out.println(ch);
        }
        return sum;
    }
}
