package kartrank.entity;

import java.util.Date;

/**
 *
 */
public class Volta {
    private Integer numero;
    private Date tempo;
    private Double velocidadeMedia;

    public Volta() {
    }

    public Volta(Integer numero, Date tempo, Double velocidadeMedia) {
        this.numero = numero;
        this.tempo = tempo;
        this.velocidadeMedia = velocidadeMedia;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Date getTempo() {
        return tempo;
    }

    public void setTempo(Date tempo) {
        this.tempo = tempo;
    }

    public Double getVelocidadeMedia() {
        return velocidadeMedia;
    }

    public void setVelocidadeMedia(Double velocidadeMedia) {
        this.velocidadeMedia = velocidadeMedia;
    }
}
