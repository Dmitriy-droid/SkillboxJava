package tasks;

import java.util.stream.Collectors;
import java.util.stream.Stream;

class Task01StringStreams {

    /**
     * Функция должна вернуть число строчных символов в строке.
     * <p>
     * Пример:
     * "abcDE" -> 3
     * "ABC" -> 0
     */
    static long countLowercaseLetters(String str) {
        return str.chars()
                .filter(Character::isLowerCase)
                .count();
    }


    /**
     * Функция должна заменить каждое слово в строке его длинной.
     * <p>
     * Слова разделяются одним или более пробелами.
     * <p>
     * Пример:
     * "a b cd" -> "1 1 2"
     * "one two   three" -> "3 3 5"
     * <p>
     * Тут подойдут эти методы:
     * - String::split
     * - Stream::map
     * - Stream::collect
     * - Collectors.joining
     */
    static String replaceWordsOnLength(String str) { //Здесь возвращался Object, но и со String
                                                     //работает
        return  Stream.of(str.split("\\s+"))
                .map(s -> Integer.toString((new String(s)).length()))
                .collect(Collectors.joining(" "));
    }
}