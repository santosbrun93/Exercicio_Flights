import java.nio.file.Path;
import java.util.List;

public interface GerenciadorDeArquivo {

        void apagaArquivo(String caminhoArquivo);

        void apagaDiretorio(String caminhoDiretorio);

        void criaArquivo(String caminhoArquivo);

        void criaDiretorio(String caminhoDiretorio);

        String converterObjetosParaString(List<Flight> objetos);

        Flight criarObjetoAPartirDaLinhaCSV(String linha);

        List<Flight> lerCSV(Path arquivo);

        void escreveLinhas(String caminhoArquivo, List<String> conteudoArquivo);

        void escreverCSV1(String conteudo, Path arquivo);

        void escreverCSV2(String conteudo, Path arquivo);

}

