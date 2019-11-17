public class Loader
{
    public static String text = "“Beware of the deep dark,” says Teresa Wren, who has worked the night shift " +
            "for 30 years as a labor-and-delivery nurse at the University of Washington Medical Center in Seattle." +
            " With sufficient sleep during the day, you can stay alert, but you’ll have to get through a wave of" +
            " fatigue between 2 a.m. and 4 a.m. Take a short nap if you can. Wren warns against eating unhealthful" +
            " snacks or candy to stay awake; her go-to weapons against the darkness are one caffeinated diet soda per" +
            " night and a walk up and down the external stairwell in the cool air.\n" +
            "Schedule your nights as though they were days. The world won’t be broadcasting the passage of time at" +
            " you with school bells, lunch specials, happy hours and traffic. “Time is either dragging or it’s flying" +
            " by,” says Wren, who eats a family dinner (her breakfast) before work. If you drink caffeine, do so at " +
            "the start of your shift and cut yourself off at least five hours before you intend to sleep. Take a " +
            "lunch break in the middle of the night. Keep your work space well lit. It helps to have stimulating, " +
            "nonrepetitive work (childbirth, in Wren’s case).";

    public static void main(String[] args)
    {
        //цифры, дефис и апостроф тоже решил включить.
        // С a.m. p.m. ничего не получилось, в лучшем случае так, но тогда точки остаются
        //String words[] = text.split("(?<=(a\\\\.m\\\\.))|[^\\w’-]+");
        //Дошёл до того, что хотел сначала пройтись по тексту и заменить a.m. p.m. на какие-то уникальные строки
        //вроде D5B469879987AAC, разбить на слова, пройти по массиву слов и заменить обратно. Если эти замены
        //завести в какую-то структуру данных (hashmap?), то это будет вполне расширяемым способом - только дополнять
        // структуру с соответствиями. Но, думаю, всё-таки тут как-то и регуляркой можно обойтись (если только a.m.
        // p.m. искать).

        String words[] = text.split("[^\\w\\-’]+");
        int wordsCount = words.length;
        for (int i = 0; i < wordsCount; i++)
        {
            System.out.println(words[i]);
        }
        System.out.print("Total words: "+ wordsCount);
    }
}