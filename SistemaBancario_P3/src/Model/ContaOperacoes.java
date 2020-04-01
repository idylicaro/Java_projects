package Model;

public class ContaOperacoes {
    private int id_contaOperacoes;
    private String data_Operacao;
    private double valor;
    private int tipoConta;

    public int getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(int tipoConta) {
        this.tipoConta = tipoConta;
    }

    private Operacao operacao;
    private Conta contaRemetente;
    private Conta contaDestinatario;

    public ContaOperacoes() {
        this.operacao = new Operacao();
        this.contaRemetente = new Conta();
        this.contaDestinatario = new Conta();
    }

    public ContaOperacoes(int id_contaOperacoes, String data_Operacao, double valor, Operacao operacao, Conta contaRemetente, Conta contaDestinatario) {
        this.id_contaOperacoes = id_contaOperacoes;
        this.data_Operacao = data_Operacao;
        this.valor = valor;
        this.operacao = operacao;
        this.contaRemetente = contaRemetente;
        this.contaDestinatario = contaDestinatario;
    }

    public int getId_contaOperacoes() {
        return id_contaOperacoes;
    }

    public void setId_contaOperacoes(int id_contaOperacoes) {
        this.id_contaOperacoes = id_contaOperacoes;
    }

    public String getData_Operacao() {
        return data_Operacao;
    }

    public void setData_Operacao(String data_Operacao) {
        this.data_Operacao = data_Operacao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Operacao getOperacao() {
        return operacao;
    }

    public void setOperacao(Operacao operacao) {
        this.operacao = operacao;
    }

    public Conta getContaRemetente() {
        return contaRemetente;
    }

    public void setContaRemetente(Conta contaRemetente) {
        this.contaRemetente = contaRemetente;
    }

    public Conta getContaDestinatario() {
        return contaDestinatario;
    }

    public void setContaDestinatario(Conta contaDestinatario) {
        this.contaDestinatario = contaDestinatario;
    }
}
