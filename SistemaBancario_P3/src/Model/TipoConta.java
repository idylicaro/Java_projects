package Model;

public class TipoConta {
    private int id_tipoConta;
    private String nomeTipo;

    public TipoConta(int id_tipoConta, String nomeTipo) {
        this.id_tipoConta = id_tipoConta;
        this.nomeTipo = nomeTipo;
    }

    public TipoConta() {

    }

    public int getId_tipoConta() {
        return id_tipoConta;
    }

    public void setId_tipoConta(int id_tipoConta) {
        this.id_tipoConta = id_tipoConta;
    }

    public String getNomeTipo() {
        return nomeTipo;
    }

    public void setNomeTipo(String nomeTipo) {
        this.nomeTipo = nomeTipo;
    }
}
