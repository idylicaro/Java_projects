package Program;

import java.util.Collections;
import java.util.Comparator;

public class Transicao {
    int idOrigem;
    String idDestino;
    String simbolo;

    public Transicao() {
    }

    public int getIdOrigem() {
        return idOrigem;
    }

    @Override
    public String toString() {
        return "Transicao{" +
                "idOrigem=" + idOrigem +
                ", idDestino=" + idDestino +
                ", simbolo=" + simbolo +
                '}';
    }

    public Transicao(int idOrigem, String idDestino, String simbolo) {
        this.idOrigem = idOrigem;
        this.idDestino = idDestino;
        this.simbolo = simbolo;
    }

    public void setIdOrigem(int idOrigem) {
        this.idOrigem = idOrigem;
    }

    public String getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(String idDestino) {
        this.idDestino = idDestino;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public static void sortArraList(Estado estado){
        Collections.sort(estado.transicoes, new Comparator<Transicao>() {
            @Override
            public int compare(Transicao o1, Transicao o2) {
                return o1.getSimbolo().compareTo(o2.getSimbolo());
            }
        });
    }
}
