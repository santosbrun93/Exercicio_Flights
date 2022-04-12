import java.nio.file.Path;
import java.util.List;

public interface GerenciadorDeArquivo {

        String converterLista1ParaString(List<Flight> objetos);

        String converterLista2ParaString(List<SumarizacaoFlights> lista);

        Flight criarObjetoAPartirDaLinhaCSV(String linha);

        //List<Flight> lerCSV();

        void escreverCSV1(String conteudo, Path arquivo);

        void escreverCSV2(String conteudo, Path arquivo);

}

