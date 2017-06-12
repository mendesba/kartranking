package kartrank.Comparator;

import java.util.Comparator;
import java.util.Date;
import kartrank.entity.Volta;

/**
 *
 */
public class MelhorVoltaComparator implements Comparator<Volta> {

    @Override
    public int compare(Volta volta1, Volta volta2) {
        Date data1 = volta1.getTempo();
        Date data2 =  volta2.getTempo();

        int result = 0;
        
        if(data1.compareTo(data2)<0){
            result =  -1;
        }else if(data1.compareTo(data2)>0){
            result =  +1;
        }

        return result;
    }
}
