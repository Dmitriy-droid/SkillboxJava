import com.skillbox.airport.Aircraft;
import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.util.*;

import static java.util.Calendar.HOUR;

public class Loader {
    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        List<Aircraft> listAirCrafts = airport.getAllAircrafts();
        List<Terminal> terminals = airport.getTerminals();

        Calendar calendar = new GregorianCalendar();
        Date now = calendar.getTime();
        calendar.add(HOUR, 2);
        Date plusTwoHours = calendar.getTime();
        List<Flight> allTerminalsFlights = new ArrayList<>();

        for (Terminal terminal : terminals) {
            List<Flight> listFlights = terminal.getFlights();
            allTerminalsFlights.addAll(listFlights);
        }

        allTerminalsFlights.stream()
                .filter(flight -> flight.getType() == Flight.Type.DEPARTURE)
                .filter(flight -> (
                        flight.getDate().after(now) &&
                                flight.getDate().before(plusTwoHours)))
                .sorted(Comparator.comparing(flight -> flight.getDate()))
                .forEach(flight -> System.out.println(flight.getDate() + " "
                        + flight.getType() + " " + flight.getAircraft()));

    }
}


