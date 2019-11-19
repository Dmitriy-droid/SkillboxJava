public class Loader
{
    static final int STRING_SIZE = 9;

    public static void main(String[] args)
    {
       String crossLine = new String(new char[STRING_SIZE]).replace('\0',' ');
       StringBuilder sbCrossLine = new StringBuilder(crossLine);

         for (int i = 0; i < STRING_SIZE; i++) {
             sbCrossLine.replace(0, STRING_SIZE, crossLine);
             sbCrossLine.setCharAt(i, 'x');
             sbCrossLine.setCharAt(STRING_SIZE - i -1, 'x');
             System.out.println(sbCrossLine.toString());
       }
    }
}
