package kartrank;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import kartrank.Util.Constantes;

/**
 *
 */
public class LerArquivo {

    private static LerArquivo instance = new LerArquivo();

    public static LerArquivo getInstance() {
        return instance;
    }

    public String formatarLinha(String linha) {
        linha = linha.replace(" ", "");
        linha = linha.replace("	", ";");
        linha = linha.replace(";;;", ";");
        linha = linha.replace(";;", ";");

        return linha;
    }


    public List<String> obterLinhasArquivo(String nomeArquivo) {
        List<String> linhas = new ArrayList();

        try {
            InputStreamReader arquivo = new InputStreamReader(new FileInputStream(nomeArquivo), Constantes.FORMATCAO_UTF_8);
            BufferedReader lerArquivo = new BufferedReader(arquivo);
            String linha = lerArquivo.readLine();

            if (linha != null) {
                linha = lerArquivo.readLine();
            }
            while (linha != null) {
                linhas.add(formatarLinha(linha));
                linha = lerArquivo.readLine();
            }

            arquivo.close();
        } catch (IOException e) {
            System.err.printf("Erro no arquivo: %s.\n", e.getMessage());
        }

        return linhas;
    }
}
