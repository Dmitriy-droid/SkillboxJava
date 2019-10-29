public class Loader
{
    public static void main(String[] args) {
        String latinLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        System.out.println("\nКоды латинских букв\n");

        for (int i = 0; i <  latinLetters.length(); i++) {
            char ch = latinLetters.charAt(i);
            System.out.println(ch + " = " + (int) ch);
        }

        String russianLettes = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя";

        System.out.println("\nКоды русских букв\n");

        for(int i = 0 ;i <  russianLettes.length();i++)
        {
            char ch = russianLettes.charAt(i);
            System.out.println( ch + " = " + (int)ch);
        }

    }
}
