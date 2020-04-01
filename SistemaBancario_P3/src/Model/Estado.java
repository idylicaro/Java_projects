package Model;

public class Estado {
    private int id_estado;
    private String nome;

    public Estado(int id_estado, String nome) {
        this.id_estado = id_estado;
        this.nome = nome;
    }

    public Estado() {

    }

    public int getId_estado() {
        return id_estado;
    }

    public void setId_estado(int id_estado) {
        this.id_estado = id_estado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
