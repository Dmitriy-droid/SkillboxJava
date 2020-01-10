package tasks;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Stream;

public class Task02Reduce {

    /**
     * Задача перемножить все числа переданные в список.
     * <p>
     * Метод должен вернуть -1 в 2-х случаях:
     * 1. Если в функцию попадает пустой стрим
     * 2. Если результат умножения превышает 100
     * <p>
     * Примеры:
     * <p>
     * (2,3) -> 6
     * (2,2,2) -> 8
     * () -> -1
     * (50, 3) -> -1
     * <p>
     * Понадобиться:
     * - Stream::reduce
     * - Optional::filter
     * - Optional::orElse
     *
     * @param linkedListOfNumbers
     * @return
     */
    public static Integer multiply(List<Integer> linkedListOfNumbers) {

        return linkedListOfNumbers.stream()
                .reduce((i, j) -> i * j)    //не могу понять это волшебное превращение из Stream в Optional после reduce
                .filter(x -> x <= 100)
                .orElse(-1);
    }
}
