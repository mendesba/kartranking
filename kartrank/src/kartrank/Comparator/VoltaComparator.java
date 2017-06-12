package kartrank.Comparator;

import java.util.Comparator;
import kartrank.entity.Volta;

/**
 *
 */
public class VoltaComparator implements Comparator<Volta> {

    @Override
    public int compare(Volta volta1, Volta volta2) {
        Integer numero1 = volta1.getNumero();
        Integer numero2 = volta2.getNumero();

        int result = numero1.compareTo(numero2) < 0 ? -1 : +1;

        return result;
    }
}
