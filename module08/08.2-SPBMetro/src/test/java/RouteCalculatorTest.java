import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

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
        List<Station> route = new ArrayList<>();

        route.add(stIndex.getStation("11"));
        route.add(stIndex.getStation("12"));
        route.add(stIndex.getStation("31"));
        route.add(stIndex.getStation("32"));

        double actual = RouteCalculator.calculateDuration(route);
        double expected = 8.5;
        assertEquals(expected, actual);
    }


    public void testGetShortestRouteOnTheLine() {
        List<Station> expected = new ArrayList() {{
            add(stIndex.getStation("21"));
            add(stIndex.getStation("22"));
            add(stIndex.getStation("23"));
        }};

        RouteCalculator routeCalculator = new RouteCalculator(stIndex);
        List<Station> actual = routeCalculator.getShortestRoute(
                stIndex.getStation("21"), stIndex.getStation("23"));
        assertEquals("On the Line: ", expected, actual);
    }

    public void testGetShortestRouteWithOneConnection() {
        List<Station> expected = new ArrayList() {{
            add(stIndex.getStation("11"));
            add(stIndex.getStation("12"));
            add(stIndex.getStation("31"));
            add(stIndex.getStation("32"));
        }};

        RouteCalculator routeCalculator = new RouteCalculator(stIndex);
        List<Station> actual = routeCalculator.getShortestRoute(
                stIndex.getStation("11"), stIndex.getStation("32"));
        assertEquals("With one connection: ", expected, actual);
    }

    public void testGetShortestRouteWithTwoConnection() {
        List<Station> expected = new ArrayList() {{
            add(stIndex.getStation("11"));
            add(stIndex.getStation("12"));
            add(stIndex.getStation("31"));
            add(stIndex.getStation("32"));
            add(stIndex.getStation("22"));
            add(stIndex.getStation("23"));
        }};

        RouteCalculator routeCalculator = new RouteCalculator(stIndex);
        List<Station> actual = routeCalculator.getShortestRoute(
                stIndex.getStation("11"), stIndex.getStation("23"));
        assertEquals("With two connection: ", expected, actual);
    }


    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
