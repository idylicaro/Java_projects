package Program;

import java.util.ArrayList;
import java.util.List;

public class Estado {
    int id;
    String nome;
    boolean statusFinal;
    boolean statusInicial;
    List<Transicao> transicoes;

    public Estado(int id, String nome, boolean statusFinal, boolean statusInicial) {
        this.id = id;
        this.nome = nome;
        this.statusFinal = statusFinal;
        this.statusInicial = statusInicial;
        this.transicoes = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Estado{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", statusFinal=" + statusFinal +
                ", statusInicial=" + statusInicial +
                '}';
    }

    public Estado() {
        this.transicoes = new ArrayList<>();
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isStatusFinal() {
        return statusFinal;
    }

    public void setStatusFinal(boolean statusFinal) {
        this.statusFinal = statusFinal;
    }

    public boolean isStatusInicial() {
        return statusInicial;
    }

    public void setStatusInicial(boolean statusInicial) {
        this.statusInicial = statusInicial;
    }

    public void sortTransicoes(){
        Transicao.sortArraList(this);
    }
}
