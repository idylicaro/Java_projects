package Model;

public class Cidade {
    private int id_cidade;
    private String nome;
    private Estado estado;

    public Cidade(int id_cidade, String nome, Estado estado) {
        this.id_cidade = id_cidade;
        this.nome = nome;
        this.estado = estado;
    }

    public Cidade() {

    }

    public int getId_cidade() {
        return id_cidade;
    }

    public void setId_cidade(int id_cidade) {
        this.id_cidade = id_cidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
