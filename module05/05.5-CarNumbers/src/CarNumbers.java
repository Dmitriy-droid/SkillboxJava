import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;

public class CarNumbers {

    private final Pattern CAR_NUM_PATTERN = Pattern.compile("^[ABCEHKMOPTYX]\\d{3}[ABCEHKMOPTYX]{2}[17]?\\d{2}$");

    //Действующие у нас номера регионов для автомобильных номеров
    //Не знаю, нужно ли массивы констант набирать заглавными
    private static final String REGIONS[] = {"01", "02", "03", "04", "05", "06", "07", "08", "09",
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

    private static final String[] ALLOWED_SYMBOLS = {"AAA", "BBB", "CCC", "EEE", "HHH", "KKK", "MMM",
            "OOO", "PPP", "TTT", "YYY", "XXX"};

    private static final String ALLOWED_DIGITS[] = {"001", "111", "222", "333", "444", "555", "666", "777", "888", "999"};


    ArrayList<String> specialNumbersArrList;
    HashSet<String> specialNumbersHashSet;
    TreeSet<String> specialNumbersTreeSet;

    CarNumbers() {
        specialNumbersArrList = generateSpecialNumbersList();
        specialNumbersHashSet = new HashSet<>(specialNumbersArrList);
        specialNumbersTreeSet = new TreeSet<>(specialNumbersArrList);
    }

    public boolean isItCarNumber(String text) {
        return CAR_NUM_PATTERN.matcher(text).matches();
    }

    public int getTotalNumbers() { return specialNumbersHashSet.size(); }

    private ArrayList<String> getAllRegions() {
//полный список номеров регионов - добавляются 1 и 7 спереди
        ArrayList<String> allRegsList = new ArrayList<>();

        allRegsList.addAll(Arrays.asList(REGIONS));

        for (String regNumber : REGIONS) {
            allRegsList.add("1" + regNumber);
        }

        for (String regNumber : REGIONS) {
            allRegsList.add("7" + regNumber);
        }

        return allRegsList;
    }


    private ArrayList<String> generateSpecialNumbersList() {

        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> allRegions = getAllRegions();
        StringBuilder carNumber = new StringBuilder(9);  //в номере от 8 до 9 знаков

        for (String letters : ALLOWED_SYMBOLS) {
            for (String digits : ALLOWED_DIGITS) {
                for (String reg : allRegions) {
                    carNumber.replace(0, 9, "");
                    carNumber.append(letters);
                    carNumber.insert(1, digits);
                    carNumber.append(reg);
                    list.add(carNumber.toString());
                }
            }
        }
        Collections.sort(list);  //из-за добавлений 1 и 7 в номера региона не получилось просто сделать
                                //отсортированный лист, пришлось сортировать в конце
        return list;
    }


    Function<String, Boolean> tryFindDirectSearch = s -> specialNumbersArrList.contains(s);
    Function<String, Boolean> tryFindBinarySearch = s -> Collections.binarySearch(specialNumbersArrList, s) >= 0;
    Function<String, Boolean> tryFindTreeSetSearch = s -> specialNumbersTreeSet.contains(s);
    Function<String, Boolean> tryFindHashSetSearch = s -> specialNumbersHashSet.contains(s);


    private final MeasureResult tryFind(String number, Function<String, Boolean> method) {
        long start = System.nanoTime();
        boolean isNumberFound = method.apply(number);
        long duration =  System.nanoTime() - start;

        return new MeasureResult(isNumberFound, duration);
    }


    public void measureDuration(String number) {

        var results = new LinkedHashMap<String, MeasureResult>();

        results.put("Прямой перебор", tryFind(number, tryFindDirectSearch));
        results.put("Бинарный поиск", tryFind(number, tryFindBinarySearch));
        results.put("Поиск TreeSet", tryFind(number, tryFindTreeSetSearch));
        results.put("Поиск HashSet", tryFind(number, tryFindHashSetSearch));


        for (var e : results.entrySet()) {
            if (e.getValue().isFound) {
                System.out.printf("%15s: %d%n", e.getKey(), e.getValue().nanoTime);
            } else {
                System.out.printf("%15s: не найден%n", e.getKey());
            }
        }
    }

    private static class MeasureResult {
        final boolean isFound;
        final long nanoTime;

        // !! конструктора по умолчанию нет
        MeasureResult(boolean isFound, long nanoTime) {
            this.isFound = isFound;
            this.nanoTime = nanoTime;
        }
    }

}

