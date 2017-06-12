/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kartrank.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import kartrank.LerArquivo;
import kartrank.Util.Utils;
import kartrank.entity.Piloto;
import kartrank.entity.Ranking;
import kartrank.entity.Volta;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Leandro
 */
public class CorridaTest {

    private Corrida corrida = new Corrida();
    private Bonus bonus = new Bonus();

    public CorridaTest() {
    }

    /**
     *
     */
    @Test
    public void deveRetonarOTempoTotalDaProva() {
        List<Volta> voltas = obterVoltas();
        Date tempo = corrida.calcularTempoTotalProva(voltas);
        assertEquals("04:39.662", Utils.getInstance().mmssSSS.format(tempo));
    }

    @Test
    public void deveRetonarPosicoesRanking() {
        List<Ranking> lista = corrida.obterRanking(obterLinhas());
        List<Ranking> ranking = corrida.obterPosicoes(lista);
        
        assertNotNull(ranking);
        assertEquals(Integer.valueOf(1), ranking.get(0).getPosicao());
        assertEquals("SENNA", ranking.get(0).getPiloto().getNome());
        assertEquals("RUBINHO", ranking.get(1).getPiloto().getNome());
        assertEquals(Integer.valueOf(2), ranking.get(1).getPosicao());
        assertEquals("MASSA", ranking.get(2).getPiloto().getNome());
        assertEquals(Integer.valueOf(3), ranking.get(2).getPosicao());
    }
    
    @Test
    public void deveRetonarMelhorVolta() {
        Ranking melhorVolta = bonus.obterMelhorVoltaCorrida(carregarRanking());
        assertNotNull(melhorVolta);
        assertNotNull("SENNA", melhorVolta.getPiloto().getNome());
        assertNotNull("01:22.270", Utils.getInstance().mmssSSS.format(melhorVolta.getMelhorVolta()));
    }
    
    @Test
    public void deveRetonarMelhoresVoltaPorPiloto() {
        List<Ranking> melhorVolta = bonus.obterMelhorVolta(carregarRanking());
        assertNotNull(melhorVolta);
        assertEquals("SENNA", melhorVolta.get(0).getPiloto().getNome());
        assertEquals("01:22.270", Utils.getInstance().mmssSSS.format(melhorVolta.get(0).getMelhorVolta()));
        assertEquals("RUBINHO", melhorVolta.get(1).getPiloto().getNome());
        assertEquals("01:35.331", Utils.getInstance().mmssSSS.format(melhorVolta.get(1).getMelhorVolta()));
        assertEquals("MASSA", melhorVolta.get(2).getPiloto().getNome());
        assertEquals("01:40.569", Utils.getInstance().mmssSSS.format(melhorVolta.get(2).getMelhorVolta()));
    }
    
    @Test
    public void deveRetonarVelocidadeMedia() {
        List<Ranking> melhorVolta = bonus.obterVelocidadeMediaPorPiloto(carregarRanking());
        assertNotNull(melhorVolta);
        assertEquals("SENNA", melhorVolta.get(0).getPiloto().getNome());
        assertEquals("42,743", melhorVolta.get(0).getVelocidadeMedia());
        assertEquals("RUBINHO", melhorVolta.get(1).getPiloto().getNome());
        assertEquals("43,592", melhorVolta.get(1).getVelocidadeMedia());
        assertEquals("MASSA", melhorVolta.get(2).getPiloto().getNome());
        assertEquals("43,775", melhorVolta.get(2).getVelocidadeMedia());
    }
    
    private List<Ranking> carregarRanking(){
        List<Ranking> lista = corrida.obterRanking(obterLinhas());
        return corrida.obterPosicoes(lista);
    }
    
    
    

    private List<Volta> obterVoltas() {
        List<Volta> voltas = new ArrayList<>();
        voltas.add(new Volta(1, Utils.getInstance().formataData("1:40.569"), 35.545D));
        voltas.add(new Volta(2, Utils.getInstance().formataData("1:35.331"), 35.225D));
        voltas.add(new Volta(3, Utils.getInstance().formataData("1:23.762"), 40.610D));

        return voltas;
    }

    private List<String> obterLinhas() {
        List<String> linhas = new ArrayList<String>();
        linhas.add(LerArquivo.getInstance().formatarLinha("10:49:08.277 	  008 – MASSA		  1		1:40.569 			44,275"));
        linhas.add(LerArquivo.getInstance().formatarLinha("10:49:01.858 	  012 – SENNA		  1		1:23.762 			43,243"));
        linhas.add(LerArquivo.getInstance().formatarLinha("10:49:11.075 	  054 – RUBINHO		  1		1:35.331 			43,408        "));
        linhas.add(LerArquivo.getInstance().formatarLinha("10:50:08.277 	  008 – MASSA		  2		1:40.888 			43,275"));
        linhas.add(LerArquivo.getInstance().formatarLinha("10:51:01.858 	  012 – SENNA		  2		1:22.270 			42,243"));
        linhas.add(LerArquivo.getInstance().formatarLinha("10:52:11.075 	  054 – RUBINHO		  2		1:35.908 			43,777        "));
        return linhas;
    }
}