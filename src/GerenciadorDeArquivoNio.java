import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.NumberFormat;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.stream.Stream;

public class GerenciadorDeArquivoNio implements GerenciadorDeArquivo {

    @Override
    public Flight criarObjetoAPartirDaLinhaCSV(String linha) {
        String[] valores = linha.split(";");

        // Esta faltando informacao na linha, entao pulamos ela.
        if (valores.length != 5) return null;

        Flight objeto = new Flight();
        objeto.setOrigin( valores[0] );
        objeto.setDestination( valores[1] );
        objeto.setDeparture(ZonedDateTime.parse(valores[2]));
        objeto.setArrival( ZonedDateTime.parse(valores[3]) );
        objeto.setPrice(Double.parseDouble((valores[4])));
        objeto.getDuration();
        /*
         * O scanner considera o locale na hora de converter string
         * para double. Ele automaticamente reconhecera se o valor
         * e separado por ponto ou por virgula.
         */
        return objeto;
    }


    @Override
    public List<Flight> lerCSV(Path arquivo) {
        List<Flight> objetos = new ArrayList<>();

        try {
            Files.readAllLines(arquivo)
                    .stream()
                    .skip(1)
                    .map(this::criarObjetoAPartirDaLinhaCSV)
                    .filter(Objects::nonNull)
                    .forEach(objetos::add);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return objetos;
    }

    @Override
    public void escreveLinhas(String caminhoArquivo, List<String> conteudoArquivo) {
        Path caminho = Paths.get(caminhoArquivo);

        try {
            Files.write(caminho, conteudoArquivo);
        } catch (IOException excecao) { }
    }

    @Override
    public void apagaArquivo(String caminhoArquivo) {

    }

    @Override
    public void apagaDiretorio(String caminhoDiretorio) {

    }

    @Override
    public void criaArquivo(String caminhoArquivo) {

    }

    @Override
    public void criaDiretorio(String caminhoDiretorio) {

    }

    @Override
    public String converterObjetosParaString(List<Flight> objetos) {
        StringBuilder conteudo = new StringBuilder();
        /*
         * Cria a linha de cabecalho automaticamente usando reflection para
         * obter o nome dos atributos declarados na classe ObjetoModelagem.
         */
        StringJoiner cabecalho = new StringJoiner(";", "", "");

        Stream.of(Flight.class.getDeclaredFields())
                .map(Field::getName)
                .forEach(cabecalho::add);

        conteudo.append(cabecalho)
                .append(System.lineSeparator());

        objetos.forEach(objeto -> conteudo
                .append(objeto.getOrigin())
                .append(";")
                .append(objeto.getDestination())
                .append(";")
                .append(objeto.getAirline())
                .append(";")
                .append(objeto.getDeparture())
                .append(";")
                .append(objeto.getArrival())
                .append(";")
                .append(objeto.getPrice())
                .append(";")
                .append(objeto.getDuration())
                .append(System.lineSeparator()));
        return conteudo.toString();
    }

    @Override
    public void escreverCSV1(String conteudo, Path arquivo) {
        try {
            // Apaga os dados antigos
            Files.deleteIfExists(arquivo);

            // Escreve os novos dados criando o arquivo se necessario
            Files.writeString(arquivo, conteudo, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void escreverCSV2(String conteudo, Path arquivo) {
        try {
            // Apaga os dados antigos
            Files.deleteIfExists(arquivo);

            // Escreve os novos dados criando o arquivo se necessario
            Files.writeString(arquivo, conteudo, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
