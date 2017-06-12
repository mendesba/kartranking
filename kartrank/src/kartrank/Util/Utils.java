package kartrank.Util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */
public class Utils {

    private static Utils instance = new Utils();
    public DateFormat ddMMyyyyHHmmssSSS = new SimpleDateFormat(Constantes.DDMMYYHHMMSSSSS);
    public DateFormat mmssSSS = new SimpleDateFormat(Constantes.MMSSSSS);
    public DecimalFormat df = new DecimalFormat(Constantes.FORMATAR_DECIMAL);

    public static Utils getInstance() {
        return instance;
    }

    public Date formataData(String tempo) {
        if (tempo == null || tempo.equals("")) {
            return null;
        }
        Date date = null;
        try {
            date = ddMMyyyyHHmmssSSS.parse(Constantes.CONST_DATA_REF.concat(tempo));
        } catch (ParseException e) {
        }
        return date;
    }

    public String retornarTempo(Date tempo) {
        return mmssSSS.format(tempo);
    }

    public String lpad(String valor, String caracter, int tamanho) {
        if (valor == null) {
            valor = "";
        }
        while (valor.length() < tamanho) {
            valor = caracter + valor;
        }

        return valor;
    }
}
