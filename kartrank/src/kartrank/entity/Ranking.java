package kartrank.entity;

import java.util.Date;
import java.util.List;

/**
 *
 */
public class Ranking {
    private Integer posicao;
    private Piloto piloto;
    private List<Volta> voltas;
    private Integer quantidadeVoltasCompletas;
    private Date tempoTotalProva;
    private Date melhorVolta;
    private String velocidadeMedia;
    private String qtdTempoDepois;

    public Integer getPosicao() {
        return posicao;
    }

    public void setPosicao(Integer posicao) {
        this.posicao = posicao;
    }

    public Piloto getPiloto() {
        return piloto;
    }

    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
    }

    public List<Volta> getVoltas() {
        return voltas;
    }

    public void setVoltas(List<Volta> voltas) {
        this.voltas = voltas;
    }

    public Integer getQuantidadeVoltasCompletas() {
        return quantidadeVoltasCompletas;
    }

    public void setQuantidadeVoltasCompletas(Integer quantidadeVoltasCompletas) {
        this.quantidadeVoltasCompletas = quantidadeVoltasCompletas;
    }

    public Date getTempoTotalProva() {
        return tempoTotalProva;
    }

    public void setTempoTotalProva(Date tempoTotalProva) {
        this.tempoTotalProva = tempoTotalProva;
    }
    
    public String getVelocidadeMedia() {
      return velocidadeMedia;
    }

    public void setVelocidadeMedia(String velocidadeMedia) {
        this.velocidadeMedia = velocidadeMedia;
    }

    public Date getMelhorVolta() {
        return melhorVolta;
    }

    public void setMelhorVolta(Date melhorVolta) {
        this.melhorVolta = melhorVolta;
    }

    public String getQtdTempoDepois() {
        return qtdTempoDepois;
    }

    public void setQtdTempoDepois(String qtdTempoDepois) {
        this.qtdTempoDepois = qtdTempoDepois;
    }
}
