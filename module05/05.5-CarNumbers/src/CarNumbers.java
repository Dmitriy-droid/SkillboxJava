import javax.swing.border.AbstractBorder;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;

public class CarNumbers {

    private final Pattern CAR_NUM_PATTERN = Pattern.compile("^[ABCEHKMOPTYX]\\d{3}[ABCEHKMOPTYX]{2}[17]?\\d{2}$");

    //Действующие у нас номера регионов для автомобильных номеров
    //Не знаю, нужно ли массивы констант набирать заглавными
    private final String regions[] = {"01", "02", "03", "04", "05", "06", "07", "08", "09",
            "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
            "21", "22", "23", "24", "25", "26", "27", "28", "29",
            "30", "31", "32", "33", "34", "35", "36", "37", "38", "39",
            "40", "41", "42", "43", "44", "45", "46", "47", "48", "49",
            "50", "51", "52", "53", "54", "55", "56", "57", "58", "59",
            "60", "61", "62", "63", "64", "65", "66", "67", "68", "69",
            "70", "71", "72", "73", "74", "75", "76", "77", "78", "79",
            "80", "81", "82", "83", "84", "85", "86", "87", "88", "89",
            "90", "91", "92", "93", "94", "95", "96", "97", "98", "99",
    };          //также впереди может быть добавлена 1 или 7, поэтому полный список сгенерим (getAllRegions)

    private final String[] allowedSymbols = {"AAA", "BBB", "CCC", "EEE", "HHH", "KKK", "MMM",
            "OOO", "PPP", "TTT", "YYY", "XXX"};

    private final String digits[] = {"001", "111", "222", "333", "444", "555", "666", "777", "888", "999"};

    public int totalNumbers;

    ArrayList<String> specialNumbersArrList;
    HashSet<String> specialNumbersHashSet;
    TreeSet<String> specialNumbersTreeSet;

    CarNumbers() {
        specialNumbersArrList = GenerateSpecialNumbersList();
        totalNumbers = specialNumbersArrList.size();

        specialNumbersHashSet = new HashSet<>();
        specialNumbersHashSet.addAll(specialNumbersArrList);

        specialNumbersTreeSet = new TreeSet<>();
        specialNumbersTreeSet.addAll(specialNumbersArrList);
    }

    public boolean isCarNumber(String text) {
        return CAR_NUM_PATTERN.matcher(text).matches();
    }


    private ArrayList<String> getAllRegions() {
//полный список номеров регионов - добавляются 1 и 7 спереди, с сортировкой
        ArrayList<String> allRegsList = new ArrayList<>();

        allRegsList.addAll(Arrays.asList(regions));

        for (String regNumber : regions) {
            allRegsList.add("1" + regNumber);
        }

        for (String regNumber : regions) {
            allRegsList.add("7" + regNumber);
        }

        return allRegsList;
    }


    private ArrayList<String> GenerateSpecialNumbersList() {

        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> allRegions = getAllRegions();
        StringBuilder carNumber = new StringBuilder(9);  //в номере от 8 до 9 знаков

        for (String letters : allowedSymbols) {
            for (String dig : digits) {
                for (String reg : allRegions) {
                    carNumber.replace(0, 9, "");
                    carNumber.append(letters);
                    carNumber.insert(1, dig);
                    carNumber.append(reg);
                    list.add(carNumber.toString());
                    System.out.println(carNumber.toString());
                }
            }
        }
        return list;
    }

    /* Хотел сделать что-то вроде measureDuration (number, findDirect());
       или measureDuration (number -> CarNumbers.findDirect(number))
       т.е., функцию замера, в которую передаём методы поиска. Но что-то пока не получилось.

      public boolean findDirect(String number) {
           return specialNumbersArrList.contains(number);
       }

       public boolean findBinarySearch(String number) {
           return Collections.binarySearch(specialNumbersArrList, number) >= 0;
       }

       public boolean findTreeSet (String number) {
           return specialNumbersTreeSet.contains(number);
       }

       public boolean findHashSet (String number) {
           return specialNumbersHashSet.contains(number);
       }
   */
    //Номеров генерится маловато (35280), поэтому взял System.nanoTime(),
    // и даже в этом случае время HashSet = o
    public void measureDuration(String number) {

        long startDirect = System.nanoTime();
        specialNumbersArrList.contains(number);
        long findDirectTime = System.nanoTime() - startDirect;

        long startBinary = System.nanoTime();
        Collections.binarySearch(specialNumbersArrList, number);
        long findBinaryTime = System.nanoTime() - startBinary;

        long startTreeSet = System.nanoTime();
        specialNumbersTreeSet.contains(number);
        long findTreeSetTime = System.nanoTime() - startTreeSet;

        long startHashSet = System.currentTimeMillis();
        specialNumbersHashSet.contains(number);
        long findHashSetTime = System.currentTimeMillis() - startHashSet;

        System.out.println("Прямой перебор : " + findDirectTime);
        System.out.println("Бинарный поиск : " + findBinaryTime);
        System.out.println("TreeSet        : " + findTreeSetTime);
        System.out.println("HashSet        : " + findHashSetTime);

    }


}
