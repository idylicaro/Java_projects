package Model;

public class Agencia {
    private int id_Agencia;
    private String rua_endereco;
    private int numero_endereco;
    private String cep_endereco;

    private Cidade cidade;

    public Agencia(int id_Agencia, String rua_endereco, int numero_endereco, String cep_endereco, Cidade cidade) {
        this.id_Agencia = id_Agencia;
        this.rua_endereco = rua_endereco;
        this.numero_endereco = numero_endereco;
        this.cep_endereco = cep_endereco;
        this.cidade = cidade;
    }

    public Agencia() {

    }

    public int getId_Agencia() {
        return id_Agencia;
    }

    public void setId_Agencia(int id_Agencia) {
        this.id_Agencia = id_Agencia;
    }

    public String getRua_endereco() {
        return rua_endereco;
    }

    public void setRua_endereco(String rua_endereco) {
        this.rua_endereco = rua_endereco;
    }

    public int getNumero_endereco() {
        return numero_endereco;
    }

    public void setNumero_endereco(int numero_endereco) {
        this.numero_endereco = numero_endereco;
    }

    public String getCep_endereco() {
        return cep_endereco;
    }

    public void setCep_endereco(String cep_endereco) {
        this.cep_endereco = cep_endereco;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}
