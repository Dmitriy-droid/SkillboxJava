
public class Loader
{
    public static void main(String[] args)
    {
        //для проверки с чётными добавил чёрный
        String[] rainbowColors = {"Красный","Оранжевый","Жёлтый","Зелёный","Голубой","Синий","Фиолетовый","Чёрный"};
        printElements(rainbowColors);

        //при делении int остаток всегда отбрасывается
        for (int i = 0; i < rainbowColors.length / 2; i++)
        {
            String tempElement = rainbowColors[i];
            rainbowColors[i] = rainbowColors[rainbowColors.length - 1 - i];
            rainbowColors[rainbowColors.length - 1 - i] = tempElement;
        }

        System.out.println("\nПосле переворота\n");
        printElements(rainbowColors);
    }

    private static void printElements (String [] array)
    {
        for (String element : array) {
            System.out.println(element);
        }
    }
}
