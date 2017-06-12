package kartrank.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import kartrank.Comparator.CorridaComparator;
import kartrank.Comparator.MelhorVoltaComparator;
import kartrank.Util.Constantes;
import kartrank.Util.Utils;
import kartrank.entity.Ranking;
import kartrank.entity.Volta;

/**
 *
 */
public class Bonus {

    public List<Ranking> obterMelhorVolta(List<Ranking> corridas) {
        for (Ranking corrida : corridas) {
            MelhorVoltaComparator ordenacao = new MelhorVoltaComparator();
            if (!corrida.getVoltas().isEmpty()) {
                Collections.sort(corrida.getVoltas(), ordenacao);
                Date tempo = corrida.getVoltas().get(0).getTempo();
                corrida.setMelhorVolta(tempo);
            }
        }
        return corridas;
    }

    public Ranking obterMelhorVoltaCorrida(List<Ranking> corridas) {
        Ranking corrida = null;
        List<Ranking> lista = obterMelhorVolta(corridas);
        List<Ranking> listaAux = new ArrayList<Ranking>();

        for (Ranking c : lista) {
            if (!c.getVoltas().isEmpty()) {
                Date tempo = c.getVoltas().get(0).getTempo();
                c.setMelhorVolta(tempo);
            }
            listaAux.add(c);
        }

        CorridaComparator ordenacao = new CorridaComparator();
        if (!listaAux.isEmpty()) {
            Collections.sort(listaAux, ordenacao);
            corrida = listaAux.get(0);
        }
        return corrida;
    }

    public List<Ranking> obterVelocidadeMediaPorPiloto(List<Ranking> corridas) {
        for (Ranking corrida : corridas) {
            corrida.setVelocidadeMedia(calcularVelocidadeMedia(corrida.getVoltas()));
        }
        return corridas;
    }

    public List<Ranking> obterTempoAposPrimeiroColocado(List<Ranking> corridas) {
        if (!corridas.isEmpty()) {
            Ranking primeiroColocado = corridas.get(0);
             Calendar calPrimerico = GregorianCalendar.getInstance();
             calPrimerico.setTime(primeiroColocado.getTempoTotalProva());
            
            for (Ranking corrida : corridas) {
                if (corrida.getPosicao()>1 && corrida.getQuantidadeVoltasCompletas() == Constantes.numeroTotalVoltas) {
                    Calendar cal = GregorianCalendar.getInstance();
                    cal.setTime(corrida.getTempoTotalProva());
                    long qtdTempoDepois = cal.getTimeInMillis()- calPrimerico.getTimeInMillis();
                    Date tempo = new Date(qtdTempoDepois);
                    corrida.setQtdTempoDepois(Utils.getInstance().retornarTempo(tempo));
                }
            }
        }
        return corridas;
    }

    public void imprimirMelhoresVoltas(List<Ranking> lista) {
        List<Ranking> melhores = obterMelhorVolta(lista);
        if (!melhores.isEmpty()) {
            System.out.print("\nMelhor volta da corrida - Piloto:\n");
            StringBuilder cabecalho = new StringBuilder();
            cabecalho.append(Constantes.CONST_CODIGO_PILOTO).append(Constantes.CONST_TAB).append(Constantes.CONST_TAB)
                    .append(Constantes.CONST_NOME_PILOTO).append(Constantes.CONST_TAB).append(Constantes.CONST_TAB)
                    .append(Constantes.CONST_MELHOR_VOLTA).append(Constantes.CONST_TAB).append(Constantes.CONST_TAB)
                    .append("\n");
            System.out.printf(cabecalho.toString());
            for (Ranking ranking : melhores) {
                StringBuilder linha = new StringBuilder();
                linha.append(Utils.getInstance().lpad(ranking.getPiloto().getCodigo(), " ", 13)).append(Constantes.CONST_TAB).append(Constantes.CONST_TAB)
                        .append(Utils.getInstance().lpad(ranking.getPiloto().getNome(), " ", 21)).append(Constantes.CONST_TAB).append(Constantes.CONST_TAB)
                        .append(Utils.getInstance().lpad(Utils.getInstance().retornarTempo(ranking.getMelhorVolta()), " ", 21))
                        .append("\n");

                System.out.print(linha.toString());
            }
        }
    }

    public void imprimirMelhorVoltaDaProva(List<Ranking> lista) {
        Ranking ranking = obterMelhorVoltaCorrida(lista);
        if (ranking != null) {
            System.out.print("\nMelhor volta da corrida:\n");
            StringBuilder cabecalho = new StringBuilder();
            cabecalho.append(Constantes.CONST_CODIGO_PILOTO).append(Constantes.CONST_TAB).append(Constantes.CONST_TAB)
                    .append(Constantes.CONST_NOME_PILOTO).append(Constantes.CONST_TAB).append(Constantes.CONST_TAB)
                    .append(Constantes.CONST_MELHOR_VOLTA_PROVA).append(Constantes.CONST_TAB).append(Constantes.CONST_TAB)
                    .append("\n");
            System.out.printf(cabecalho.toString());

            StringBuilder linha = new StringBuilder();
            linha.append(Utils.getInstance().lpad(ranking.getPiloto().getCodigo(), " ", 13)).append(Constantes.CONST_TAB).append(Constantes.CONST_TAB)
                    .append(Utils.getInstance().lpad(ranking.getPiloto().getNome(), " ", 21)).append(Constantes.CONST_TAB).append(Constantes.CONST_TAB)
                    .append(Utils.getInstance().lpad(Utils.getInstance().retornarTempo(ranking.getMelhorVolta()), " ", 22))
                    .append("\n");

            System.out.print(linha.toString());
        }
    }

    public void imprimirVelocidadeMediaPorPiloto(List<Ranking> lista) {
        List<Ranking> velocidades = obterVelocidadeMediaPorPiloto(lista);
        if (!velocidades.isEmpty()) {
            System.out.print("\nMelhor volta da corrida:\n");
            StringBuilder cabecalho = new StringBuilder();
            cabecalho.append(Constantes.CONST_CODIGO_PILOTO).append(Constantes.CONST_TAB).append(Constantes.CONST_TAB)
                    .append(Constantes.CONST_NOME_PILOTO).append(Constantes.CONST_TAB).append(Constantes.CONST_TAB)
                    .append(Constantes.CONST_VELOCIDADE_MEDIA_CORRIDA).append(Constantes.CONST_TAB).append(Constantes.CONST_TAB)
                    .append("\n");
            System.out.printf(cabecalho.toString());

            for (Ranking ranking : velocidades) {
                StringBuilder linha = new StringBuilder();
                linha.append(Utils.getInstance().lpad(ranking.getPiloto().getCodigo(), " ", 13)).append(Constantes.CONST_TAB).append(Constantes.CONST_TAB)
                        .append(Utils.getInstance().lpad(ranking.getPiloto().getNome(), " ", 21)).append(Constantes.CONST_TAB).append(Constantes.CONST_TAB)
                        .append(Utils.getInstance().lpad(ranking.getVelocidadeMedia(), " ", 28))
                        .append("\n");

                System.out.print(linha.toString());
            }
        }
    }
    public void imprimirQuantidadeTempoDepoisPrimeiro(List<Ranking> lista) {
        List<Ranking> velocidades = obterTempoAposPrimeiroColocado(lista);
        if (!velocidades.isEmpty()) {
            System.out.print("\nQuantidade de Tempo depois do Primeiro:\n");
            StringBuilder cabecalho = new StringBuilder();
            cabecalho.append(Constantes.CONST_CODIGO_PILOTO).append(Constantes.CONST_TAB).append(Constantes.CONST_TAB)
                    .append(Constantes.CONST_NOME_PILOTO).append(Constantes.CONST_TAB).append(Constantes.CONST_TAB)
                    .append(Constantes.CONST_TEMPO_PROVA).append(Constantes.CONST_TAB).append(Constantes.CONST_TAB)
                    .append(Constantes.CONST_QUANTIDADE_DEPOIS_PRIMEIRO).append(Constantes.CONST_TAB).append(Constantes.CONST_TAB)
                    .append("\n");
            System.out.printf(cabecalho.toString());

            for (Ranking ranking : velocidades) {
                StringBuilder linha = new StringBuilder();
                linha.append(Utils.getInstance().lpad(ranking.getPiloto().getCodigo(), " ", 13)).append(Constantes.CONST_TAB).append(Constantes.CONST_TAB)
                        .append(Utils.getInstance().lpad(ranking.getPiloto().getNome(), " ", 21)).append(Constantes.CONST_TAB).append(Constantes.CONST_TAB)
                        .append(Utils.getInstance().lpad(Utils.getInstance().retornarTempo(ranking.getTempoTotalProva()), " ", 22)).append(Constantes.CONST_TAB).append(Constantes.CONST_TAB)
                        .append(Utils.getInstance().lpad(ranking.getQtdTempoDepois(), " ", 25))
                        .append("\n");

                System.out.print(linha.toString());
            }
        }
    }

    public String calcularVelocidadeMedia(List<Volta> voltas) {
        Double total = new Double(0);

        int qtd = voltas.isEmpty() ? 0 : voltas.size();
        for (Volta volta : voltas) {
            total += volta.getVelocidadeMedia();
        }

        if (total == 0 || qtd == 0) {
            return "-";
        }

        Double valor = total / qtd;

        return Utils.getInstance().df.format(valor);
    }
}
