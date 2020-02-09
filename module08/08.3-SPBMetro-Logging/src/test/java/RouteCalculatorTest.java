import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Metro scheme that used in tests:
 * <pre>{@code
 *
 *            Первая      Вторая
 *              11          21
 *              ^           ^
 *              ^           ^
 *            12/31 ->->-> 32/22  Третья
 *              ^           ^
 *              ^           ^
 *              13          23
 *
 * }</pre>
 */

public class RouteCalculatorTest extends TestCase {

    StationIndex stIndex;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        ArrayList<Line> lines = new ArrayList<>();
        lines.add(new Line(1, "Первая"));
        lines.add(new Line(2, "Вторая"));
        lines.add(new Line(3, "Третья"));

        stIndex = new StationIndex();
        lines.stream()
                .forEach(line -> stIndex.addLine(line));

        addNewStationToMap(stIndex, lines, "11", 0);
        addNewStationToMap(stIndex, lines, "12", 0);
        addNewStationToMap(stIndex, lines, "13", 0);

        addNewStationToMap(stIndex, lines, "21", 1);
        addNewStationToMap(stIndex, lines, "22", 1);
        addNewStationToMap(stIndex, lines, "23", 1);

        addNewStationToMap(stIndex, lines, "31", 2);
        addNewStationToMap(stIndex, lines, "32", 2);

        List<Station> connection1 = new ArrayList<>();
        connection1.add(stIndex.getStation("12"));
        connection1.add(stIndex.getStation("31"));

        List<Station> connection2 = new ArrayList<>();
        connection2.add((stIndex.getStation("22")));
        connection2.add((stIndex.getStation("32")));

        stIndex.addConnection(connection1);
        stIndex.addConnection(connection2);
    }

    private void addNewStationToMap(StationIndex sti, ArrayList<Line> lines, String name, int lineIndex) {
        sti.addStation(new Station(name, lines.get(lineIndex)));
        lines.get(lineIndex).addStation(sti.getStation(name));
    }


    public void testCalculateDuration() {
        assertEquals(8.5, RouteCalculator.calculateDuration(makeRoute("11 -> 12 -> 31 -> 32")));
    }


    public void testGetShortestRouteOnTheLine() {
        RouteCalculator routeCalculator = new RouteCalculator(stIndex);
        assertEquals("On the Line: ", makeRoute("21 -> 22 -> 23"),
                routeCalculator.getShortestRoute(stIndex.getStation("21"), stIndex.getStation("23")));
    }

    public void testGetShortestRouteOnTheLineReverse() {
        RouteCalculator routeCalculator = new RouteCalculator(stIndex);
        assertEquals("On the Line - Reverse: ", makeRoute("23 -> 22 -> 21"),
                routeCalculator.getShortestRoute(stIndex.getStation("23"), stIndex.getStation("21")));
    }

    public void testGetShortestRouteWithOneConnection() {
        RouteCalculator routeCalculator = new RouteCalculator(stIndex);
        assertEquals("With one connection: ", makeRoute("11 -> 12 -> 31 -> 32"),
                routeCalculator.getShortestRoute(stIndex.getStation("11"), stIndex.getStation("32")));
    }

    public void testGetShortestRouteWithTwoConnection() {
        RouteCalculator routeCalculator = new RouteCalculator(stIndex);
        assertEquals("With two connection: ", makeRoute("11 -> 12 -> 31 -> 32 -> 22 -> 23"),
                routeCalculator.getShortestRoute(stIndex.getStation("11"), stIndex.getStation("23")));
    }


    public void testGetRouteViaConnectedLine()
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        RouteCalculator routeCalculator = new RouteCalculator(stIndex);
        Method method = RouteCalculator.class.getDeclaredMethod
                ("getRouteViaConnectedLine", Station.class, Station.class);
        method.setAccessible(true);
        assertEquals(makeRoute("31 -> 32"), method.invoke(routeCalculator,
                stIndex.getStation("12"), stIndex.getStation("22")));
    }


    public void testGetRouteWithOneConnectionWithEqualStationPassed()
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        RouteCalculator routeCalculator = new RouteCalculator(stIndex);
        Method method = RouteCalculator.class.getDeclaredMethod
                ("getRouteWithOneConnection", Station.class, Station.class);
        method.setAccessible(true);
        assertNull(method.invoke(
                routeCalculator, stIndex.getStation("11"), stIndex.getStation("11")));
    }


    public void testGetRouteWithTwoConnectionsWithEqualStationPassed()
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        RouteCalculator routeCalculator = new RouteCalculator(stIndex);
        Method method = RouteCalculator.class.getDeclaredMethod
                ("getRouteWithTwoConnections", Station.class, Station.class);
        method.setAccessible(true);
        assertNull((List<Station>) method.invoke(
                routeCalculator, stIndex.getStation("11"), stIndex.getStation("11")));
    }

    List<Station> makeRoute(String routeStr) {
        List<Station> route = new ArrayList<>();

        for (String stationStr : routeStr.split(" -> ")) {
            route.add(stIndex.getStation(stationStr));
        }
        return route;
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
