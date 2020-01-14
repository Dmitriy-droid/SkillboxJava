package tasks;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;

/**
 * Есть грузовик Truck, у которого задана максимальная грузоподьемность.
 * <p>
 * Грузовики делятся на 3 типа в зависимости от грузоподьемности:
 * - Pickup        - до 2 тонн
 * - SmallBoxTruck - до 12 тонн
 * - SemiTrailer   - до 20 тонн
 */
public class Task03TruckTypes {

    /**
     * Возвращает тип грузовика с наименьшей грузоподьемностью,
     * который сможет перевести заданный вес.
     * <p>
     * Если вес слишком большой, то метод должен кинуть WeightTooHighException с сообщением "слишком большой вес"
     *
     * <pre>
     * Пример:
     *   1_000 -> Pickup   (для одной тонны достаточно пикапа)
     * </pre>
     * <p>
     * Вы можете решить это задание как через Stream, так и через цикл.
     * Какой код выйдет проще и легче для понимания, тот и используйте.
     * <p>
     *
     * <i>Подсказка: Доступные значения в enum можно перебирать через метод values()</i>
     * <p>Совет: Нажмите Ctrl+Q (Cmd+Q) чтобы увидеть комментарии с форматированием.
     *
     * @param weight
     * @return
     */
    public static TruckType getTypeByWeight(int weight) {

        return Arrays.stream(TruckType.values())
                .filter(t -> t.canHandleWeight(weight))
                .findFirst()                       //В enum они идут в порядке возрастания веса
                .orElseThrow(WeightTooHighException::new);

    }

    /**
     * Сгруппировать все грузовики по их грузоподьемности.
     *
     * <p>Пример:
     * <pre>
     *      List(Truck(5_000), Truck(5_100), Truck(20_000))
     *      ->
     *      Map [
     *          SmallBoxTruck => List(Truck(5_000), Truck(5_100))
     *          SemiTrailer   => List(Truck(20_000))
     *      ]
     *
     * Понадобиться:
     *   - Stream::collect
     *   - Collectors.groupingBy
     * </pre>
     *
     * @param trucks
     * @return
     */
    public static Map<TruckType, List<Truck>> groupTrucksByType(List<Truck> trucks) {

        return trucks.stream()
                .collect(Collectors.groupingBy(
                        truck -> getTypeByWeight(truck.maxWeightKg)));
    }

    /**
     * Посчитать кол-во грузовиков в каждой группе.
     *
     * <p>Пример:
     * <pre>
     *      List(Truck(5_000), Truck(5_100), Truck(20_000))
     *      ->
     *      Map [
     *          SmallBoxTruck => 2
     *          SemiTrailer   => 1
     *      ]
     *
     * Понадобиться:
     *   - Stream::collect
     *   - Collectors.groupingBy
     *   - Collectors.counting
     * </pre>
     *
     * @param trucks
     * @return
     */
    public static Map<TruckType, Long> countTrucksByType(List<Truck> trucks) {
        return trucks.stream()
                .collect(Collectors.groupingBy(
                        truck -> getTypeByWeight(truck.maxWeightKg),
                        counting()));
    }

    /**
     * Грузовик и его грузоподьемность.
     */
    static class Truck {
        int maxWeightKg;

        Truck(int maxWeightKg) {
            this.maxWeightKg = maxWeightKg;
        }
    }

    /**
     * Тип грузовика по грузоподьемности в кг.
     */
    enum TruckType {
        Pickup(2000),
        SmallBoxTruck(12_000),
        SemiTrailer(20_000);

        private int maxLoad;

        TruckType(int maxLoad) {
            this.maxLoad = maxLoad;
        }

        public boolean canHandleWeight(int weight) {
            return weight <= this.maxLoad;
        }
    }

    public static class WeightTooHighException extends RuntimeException {
        public WeightTooHighException() {
            super("Weight is too high for any type of vehicle available");
        }
    }

}
