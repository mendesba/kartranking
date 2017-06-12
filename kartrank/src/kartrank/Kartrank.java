package kartrank;

import java.util.List;
import java.util.Scanner;
import kartrank.Service.Bonus;
import kartrank.Service.Corrida;
import kartrank.Util.Constantes;
import kartrank.entity.Ranking;

/**
 *
 */
public class Kartrank {

    /**
     * @param args argumentos da linha de comando
     */
    public static void main(String[] args) {
        Corrida corrida = new Corrida();
        Bonus bonus = new Bonus();
        Scanner ler = new Scanner(System.in, Constantes.FORMATCAO_UTF_8);
        System.out.printf("Informe o caminho e o nome do arquivo:\n");
        String nomeArquivo = ler.nextLine();
        System.out.printf("\nConteúdo do arquivo texto:\n");

        //Ranking
        List<Ranking> ranking = corrida.iniciar(nomeArquivo);
        corrida.imprimirRanking(ranking);
        //Bonus
        //Melhor volta por Piloto
        bonus.imprimirMelhoresVoltas(ranking);
        
        //Melhor volta da Corrida
        bonus.imprimirMelhorVoltaDaProva(ranking);
        
        //Velocidade Media
        bonus.imprimirVelocidadeMediaPorPiloto(ranking);
        
        //Velocidade Media
        bonus.imprimirQuantidadeTempoDepoisPrimeiro(ranking);
        
        System.out.println();
    }
}
