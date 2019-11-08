import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Loader
{
    public static void main(String[] args) {
        String text = "Вася заработал 8000 рублей, Петя - 7563 рубля, а Маша - 3000 рублей";
        System.out.println(text);

        Pattern pattern = Pattern.compile("[0-9]{1,}");
        Matcher matcher = pattern.matcher(text);

        int total = 0;

        while (matcher.find()) {
            total += Integer.parseInt(matcher.group());
        }
        System.out.println("Все вместе заработали " + total + getRoublesStr(Integer.toString(total)));
    }

    private static String getRoublesStr(String totalStr){
        char lastChar = totalStr.charAt(totalStr.length() - 1);

        switch (lastChar) {
            case '1':
                return " рубль.";
            case '2':
            case '3':
            case '4':
                return " рубля.";
            default:
                return " рублей.";
        }
    }
}
