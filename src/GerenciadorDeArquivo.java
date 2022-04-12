import java.nio.file.Path;
import java.util.List;

public interface GerenciadorDeArquivo {

        String converterListaParaString(List<Flight> objetos);

        Flight criarObjetoAPartirDaLinhaCSV(String linha);

        List<Flight> lerCSV(Path arquivo);

        void escreverCSV1(String conteudo, Path arquivo);

        void escreverCSV2(String conteudo, Path arquivo);

}

