import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Flight {

    private String origin;

    private String destination;

    private String airline;

    private ZonedDateTime departure;

    private ZonedDateTime arrival;

    private double price;

    private long duration;


    public Flight(String origin, String destination, String airline, ZonedDateTime departure, ZonedDateTime arrival, double price) {
        this.origin = origin;
        this.destination = destination;
        this.airline = airline;
        this.departure = departure;
        this.arrival = arrival;
        this.price = price;
    }

    public Flight(String origin, String destination, String airline, ZonedDateTime departure,
                  ZonedDateTime arrival, double price, long duration) {
        this.origin = origin;
        this.destination = destination;
        this.airline = airline;
        this.departure = departure;
        this.arrival = arrival;
        this.price = price;
        this.duration = duration;
    }

    public Flight() {
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

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public ZonedDateTime getDeparture() {
        return departure;
    }

    public void setDeparture(ZonedDateTime departure) {
        this.departure = departure;
    }

    public ZonedDateTime getArrival() {
        return arrival;
    }

    public void setArrival(ZonedDateTime arrival) {
        this.arrival = arrival;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getDuration() {
        return (long) BigDecimal.valueOf(departure.until(arrival, ChronoUnit.MINUTES))
                .divide(new BigDecimal("60"))
                .doubleValue();
    }

    public String getOrigin_Destination() {
        return this.getOrigin() +"_"+getDestination();
    }


    @Override
    public String toString() {
        return "Flight{" +
                "origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", airline='" + airline + '\'' +
                ", departure=" + departure +
                ", arrival=" + arrival +
                ", price=" + price +
                ", duration=" + duration +
                '}';
    }
}

