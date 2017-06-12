package kartrank.Comparator;

import java.util.Comparator;
import java.util.Date;
import kartrank.entity.Ranking;

/**
 *
 */
public class CorridaComparator implements Comparator<Ranking> {

    @Override
    public int compare(Ranking corrida1, Ranking corrida2) {
        Date data1 = corrida1.getTempoTotalProva();
        Date data2 = corrida2.getTempoTotalProva();
        Integer qtdVoltas1 = corrida1.getQuantidadeVoltasCompletas();
        Integer qtdVoltas2 = corrida2.getQuantidadeVoltasCompletas();

        int result = 0;

        if (qtdVoltas1.compareTo(qtdVoltas2) < 0) {
            result = +1;
        } else if (qtdVoltas1.compareTo(qtdVoltas2) > 0) {
            result = -1;
        } else {
            if (data1.compareTo(data2) < 0) {
                result = -1;
            } else if (data1.compareTo(data2) > 0) {
                result = +1;
            } else {
                result = 0;
            }
        }

        return result;
    }
}
