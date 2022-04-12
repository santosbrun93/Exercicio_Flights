import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static List<SumarizacaoFlights> main(String[] args) {

        Path local1 = Paths.get("voosOrdenados.csv");

        Path local2 = Paths.get("resultados.csv");

        System.out.println("Hello World!!");
        System.out.println("Leitura de CSV");
        System.out.println("Caminho informado: diretório Local");

        GerenciadorDeArquivoNio io = new GerenciadorDeArquivoNio();

        List<Flight> voos = io.lerCSV(Path.of("flights.csv"));

        List<Flight> voosOrdenados =  voos.stream()
                .sorted(Comparator.comparing(Flight::getOrigin)
                        .thenComparing(Flight::getDestination)
                        .thenComparing(Flight::getDuration)
                        .thenComparing(Flight::getPrice)
                        .thenComparing(Flight::getAirline)
                        )
                .collect(Collectors.toList());


        public List<Flight> obtemResultados (List<Flight> voos) {
            List<SumarizacaoFlights> resultadoVoos = voos.stream()
                    .collect(Collectors.groupingBy(Flight::getOrigin_Destination))
                    .values()
                    .stream()
                    .map(SumarizacaoFlights::new)
                    .collect(Collectors.toList());
            return resultadoVoos;

        }

        String dados1 = io.converterListaParaString( voosOrdenados );

        String dados2 = io.converterListaParaString( resultadoVoos );

        io.escreverCSV1(dados1, local1);

        io.escreverCSV2(dados2 ,local2);


    }

    private static List<Flight> mapToResultados(Map.Entry<String, List<Flight>> entry) {

        String necessidade = entry.getKey();
        List<Flight> voos1 = entry.getValue();
        OptionalDouble menorPreco = voos1.stream().mapToDouble(Flight::getPrice).min();
        OptionalDouble mediaPreco = voos1.stream().mapToDouble(Flight::getPrice).average();
        OptionalLong maisRapido = voos1.stream().mapToLong(Flight::getDuration).min();
        OptionalLong maiorDuracao = voos1.stream().mapToLong(Flight::getDuration).max();
        OptionalDouble mediaDuracao = voos1.stream().mapToLong(Flight::getDuration).average();
        return 0;
    }


}
