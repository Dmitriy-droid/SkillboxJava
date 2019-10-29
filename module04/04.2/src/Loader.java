
public class Loader
{
    public static void main(String[] args)
    {
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";

        System.out.println(text);

        int vasyaIndex1 = text.indexOf("л") + 1;
        int vasyaIndex2 = text.indexOf("руб");
        int vasyaIncome = Integer.parseInt(text.substring(vasyaIndex1, vasyaIndex2).trim());

        int mashaIndex1 = text.lastIndexOf("-") + 1;
        int mashaIndex2 = text.lastIndexOf("руб");
        int mashaIncome = Integer.parseInt(text.substring(mashaIndex1, mashaIndex2).trim());

        int total = vasyaIncome + mashaIncome;
        String lastWord = "";
        String totalStr = Integer.toString(total);
        char ch = totalStr.charAt(totalStr.length() - 1);

        switch (ch) {
            case '1':
                lastWord = " рубль.";
                break;
            case '2':case '3':case '4':
                lastWord = " рубля.";
                break;
            default:
                lastWord = " рублей.";
                break;
        }

        System.out.println("Вася и Маша вместе заработали " + totalStr + lastWord);
    }
}