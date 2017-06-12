package kartrank.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kartrank.Comparator.CorridaComparator;
import kartrank.Comparator.VoltaComparator;
import kartrank.LerArquivo;
import kartrank.Util.Constantes;
import kartrank.Util.Utils;
import kartrank.entity.Piloto;
import kartrank.entity.Ranking;
import kartrank.entity.Volta;

/**
 *
 */
public class Corrida {

    private LerArquivo lerArquivo = new LerArquivo();

    public List<Ranking> iniciar(String caminhoArquivo) {
        List<String> linhas = lerArquivo.obterLinhasArquivo(caminhoArquivo);
        return obterRanking(linhas);
    }

    public List<Ranking> obterRanking(List<String> linhas) {
        Map<Piloto, List<Volta>> map = new HashMap();
        for (String linha : linhas) {
            String[] campos = linha.split(";");

            String piloto = campos[1];
            String[] dados = piloto.split("–");
            Piloto p = new Piloto(dados[0], dados[1]);

            if (!map.containsKey(p)) {
                map.put(p, new ArrayList<Volta>());
            }

            if (Integer.valueOf(campos[2]) <= Constantes.numeroTotalVoltas) {
                Volta volta = new Volta();
                volta.setNumero(Integer.valueOf(campos[2]));
                try {
                    volta.setTempo(Utils.getInstance().formataData(campos[3]));
                } catch (Exception ex) {
                }
                volta.setVelocidadeMedia(Double.valueOf(campos[4].replace(",", ".")));
                map.get(p).add(volta);
            }
        }

        List<Ranking> corridas = new ArrayList();
        for (Map.Entry<Piloto, List<Volta>> entry : map.entrySet()) {
            Ranking corrida = new Ranking();
            corrida.setPiloto(entry.getKey());
            corrida.setVoltas(entry.getValue());
            int qtdVoltasCompletas = corrida.getVoltas().size() == Constantes.numeroTotalVoltas ? Constantes.numeroTotalVoltas : corrida.getVoltas().size();
            corrida.setQuantidadeVoltasCompletas(qtdVoltasCompletas);
            corrida.setTempoTotalProva(calcularTempoTotalProva(entry.getValue()));
            corridas.add(corrida);
        }

        obterPosicoes(corridas);
        return corridas;
    }
    
    public void imprimirRanking(List<Ranking> corridas){
        if (!corridas.isEmpty()) {
            System.out.printf("\nRanking:\n");
            StringBuilder cabecalho = new StringBuilder();
            cabecalho.append(Constantes.CONST_POSICAO_CHEGADA).append(Constantes.CONST_TAB).append(Constantes.CONST_TAB)
                    .append(Constantes.CONST_CODIGO_PILOTO).append(Constantes.CONST_TAB).append(Constantes.CONST_TAB)
                    .append(Constantes.CONST_NOME_PILOTO).append(Constantes.CONST_TAB).append(Constantes.CONST_TAB)
                    .append(Constantes.CONST_QUANTIDADE_VOLTAS).append(Constantes.CONST_TAB).append(Constantes.CONST_TAB)
                    .append(Constantes.CONST_TEMPO_PROVA)
                    .append("\n");
            System.out.printf(cabecalho.toString());

        }
        for (Ranking corrida : corridas) {
            StringBuilder linha = new StringBuilder();
            linha.append(Utils.getInstance().lpad(corrida.getPosicao().toString(), " ", 15)).append(Constantes.CONST_TAB).append(Constantes.CONST_TAB)
                    .append(Utils.getInstance().lpad(corrida.getPiloto().getCodigo(), " ", 13)).append(Constantes.CONST_TAB).append(Constantes.CONST_TAB)
                    .append(Utils.getInstance().lpad(corrida.getPiloto().getNome(), " ", 21)).append(Constantes.CONST_TAB).append(Constantes.CONST_TAB)
                    .append(Utils.getInstance().lpad(corrida.getQuantidadeVoltasCompletas().toString(), " ", 24)).append(Constantes.CONST_TAB).append(Constantes.CONST_TAB)
                    .append(Utils.getInstance().lpad(Utils.getInstance().retornarTempo(corrida.getTempoTotalProva()), " ", 21))
                    .append("\n");

            System.out.print(linha.toString());
        }
    }

    public Date calcularTempoTotalProva(List<Volta> voltas) {
        VoltaComparator ordenacao = new VoltaComparator();
        Collections.sort(voltas, ordenacao);
        long somaTempos = 0;
        for (Volta volta : voltas) {
            Calendar cal = GregorianCalendar.getInstance();
            cal.setTime(volta.getTempo());
            somaTempos += cal.getTimeInMillis();
        }
        return new Date(somaTempos);
    }

    public List<Ranking> obterPosicoes(List<Ranking> posicoes) {
        CorridaComparator ordenacao = new CorridaComparator();
        Collections.sort(posicoes, ordenacao);
        for (int i = 0; i < posicoes.size(); i++) {
            Ranking ranking = posicoes.get(i);
            ranking.setPosicao(i + 1);
        }

        return posicoes;
    }
}
