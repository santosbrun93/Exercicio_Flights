import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class SumarizacaoFlights {

    private String origin;

    private String destination;



    private long menorFlight;

    private long maiorFlight;

    private double maisBaratoFlight;

    private long averageDuration;

    private double averagePrice;


    public SumarizacaoFlights(List<Flight> voos) {
        this(voos.get(0).getOrigin(), voos.get(0).getDestination(), voos.stream());
    }

    public SumarizacaoFlights(String origin, String destination, Stream<Flight> voos) {
        this(
                origin,
                destination,
                voos.mapToLong(Flight::getDuration).min().getAsLong(),
                voos.mapToLong(Flight::getDuration).max().getAsLong(),
                voos.mapToDouble(Flight::getPrice).min().getAsDouble(),
                voos.mapToLong(Flight::getDuration).average().getAsDouble(),
                voos.mapToDouble(Flight::getPrice).average().getAsDouble()
        );
    }


    public SumarizacaoFlights(String origin, String destination, long asLong, long asLong1, double asDouble, double asDouble1, double asDouble2) {
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public long getMenorFlight() {
        return menorFlight;
    }

    public void setMenorFlight(long menorFlight) {
        this.menorFlight = menorFlight;
    }

    public long getMaiorFlight() {
        return maiorFlight;
    }

    public void setMaiorFlight(long maiorFlight) {
        this.maiorFlight = maiorFlight;
    }

    public double getMaisBaratoFlight() {
        return maisBaratoFlight;
    }

    public void setMaisBaratoFlight(double maisBaratoFlight) {
        this.maisBaratoFlight = maisBaratoFlight;
    }

    public long getAverageDuration() {
        return averageDuration;
    }

    public void setAverageDuration(long averageDuration) {
        this.averageDuration = averageDuration;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(double averagePrice) {
        this.averagePrice = averagePrice;
    }
}
