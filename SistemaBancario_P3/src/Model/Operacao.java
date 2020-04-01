package Model;

public class Operacao {
    /*está tabela operaçao tem os tipo de operaçoes , ex:saque,deposito,TED...*/
    private int id_operacao;
    private String nome_tipo_Operacao;

    public Operacao(int id_operacao, String nome_tipo_Operacao) {
        this.id_operacao = id_operacao;
        this.nome_tipo_Operacao = nome_tipo_Operacao;
    }

    public Operacao() {

    }

    public int getId_operacao() {
        return id_operacao;
    }

    public void setId_operacao(int id_operacao) {
        this.id_operacao = id_operacao;
    }

    public String getNome_tipo_Operacao() {
        return nome_tipo_Operacao;
    }

    public void setNome_tipo_Operacao(String nome_tipo_Operacao) {
        this.nome_tipo_Operacao = nome_tipo_Operacao;
    }
}
