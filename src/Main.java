import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        Path local1 = Paths.get("C:\\Users\\bruni\\IdeaProjects\\FlightPlain\\src\\voosOrdenados.csv");

        Path local2 = Paths.get("C:\\Users\\bruni\\IdeaProjects\\FlightPlain\\src\\resultados.csv");

        System.out.println("Hello World!!");
        System.out.println("Leitura de CSV");
        System.out.println("Caminho informado: diretório Local");

        GerenciadorDeArquivoNio io = new GerenciadorDeArquivoNio();

        String path = "C:\\Users\\bruni\\IdeaProjects\\FlightPlain\\src\\flights.csv";

        List<Flight> voosCSV = new ArrayList<Flight>();

        final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss (XXX)");

        try (BufferedReader br = new BufferedReader(new FileReader(path, StandardCharsets.UTF_8))) {

            br.readLine();
            String line = br.readLine();
            while (line != null) {


                String[] vect = line.split(";");
                String origin = vect[0];
                String destitation = vect[1];
                String airlane = vect[2];
                ZonedDateTime departure;
                departure = ZonedDateTime.parse(vect[3], FORMATTER);
                ZonedDateTime arrival = ZonedDateTime.parse(vect[4], FORMATTER);
                double price = Double.parseDouble(vect[5]);

                Flight voo = new Flight(origin, destitation, airlane, departure, arrival, price);
                voo.getDuration();

                voosCSV.add(voo);

                line = br.readLine();
            }

            System.out.println("Voos:");
            for (Flight f : voosCSV) {
                System.out.println(f);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Carregado lista de voos!!");


        System.out.println(voosCSV);

        List<Flight> voosOrdenados = voosCSV.stream()
                .sorted(Comparator.comparing(Flight::getOrigin)
                        .thenComparing(Flight::getDestination)
                        .thenComparing(Flight::getDuration)
                        .thenComparing(Flight::getPrice)
                        .thenComparing(Flight::getAirline)
                )
                .collect(Collectors.toList());
        System.out.println("Realizada ordenação da lista do Voos!!");

        String dados1 = io.converterLista1ParaString(voosOrdenados);

        io.escreverCSV1(dados1, local1);

        System.out.println("Realizada gravação do primeiro Arquivo com sucesso!!");


        List<SumarizacaoFlights> sumarizacao = voosCSV.stream()
                .collect(Collectors.groupingBy(Flight::getOrigin_Destination)) //Map<String, List<Pedido>>
                .entrySet() //Set<Entry<String, List<Flight>>>
                .stream() //Map<String, List<Flight>> -> Map<String, SumarizacaoFlights>
                .map(Main::mapToSumarizador) //Entry<String, List<Flight> -> Entry<String, SumarizacaoFlights>
                .collect(Collectors.toList());//        Map<String, SumarizacaoFlights>

        System.out.println(sumarizacao);


        System.out.println("Realizada sumarização dos Voos pelas caracteristicas determinadas!!");

        String dados2 = io.converterLista2ParaString(sumarizacao);

        io.escreverCSV2(dados2 ,local2);

        System.out.println("Realizada gravação do segundo Arquivo com sucesso!!");

        System.out.println("Execução concluída, Até logo.");



    }
    private static SumarizacaoFlights mapToSumarizador(Map.Entry<String, List<Flight>> entry) {
        String key = entry.getKey();
        String[] keyParts = key.split("_");
        String origin = keyParts[0];
        String destination = keyParts[1];

        List<Flight> voos = entry.getValue();
        long menorFlight = voos.stream().mapToLong(Flight::getDuration).min().getAsLong();
        long maiorFlight = voos.stream().mapToLong(Flight::getDuration).max().getAsLong();
        double maisBaratoFlight = voos.stream().mapToDouble(Flight::getPrice).min().getAsDouble();
        long averageDuration = (long) voos.stream().mapToLong(Flight::getDuration).average().getAsDouble();
        double averagePrice = voos.stream().mapToDouble(Flight::getPrice).average().getAsDouble();
        return  new SumarizacaoFlights( origin, destination, menorFlight, maiorFlight, maisBaratoFlight, averageDuration, averagePrice);
    }
}
