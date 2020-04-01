package Model;

public class Conta {
    private int id_conta;
    private int numeroConta;
    private String senha;

    public int getId_conta() {
        return id_conta;
    }

    public void setId_conta(int id_conta) {
        this.id_conta = id_conta;
    }

    private double saldo;
    private boolean contaAtiva;

    private TipoConta tipoConta;
    private Pessoa pessoa;
    private Agencia agencia;

    public Conta(int id_conta, int numeroConta, String senha, double saldo, boolean contaAtiva, TipoConta tipoConta, Pessoa pessoa, Agencia agencia) {
        this.id_conta = id_conta;
        this.numeroConta = numeroConta;
        this.senha = senha;
        this.saldo = saldo;
        this.contaAtiva = contaAtiva;
        this.tipoConta = tipoConta;
        this.pessoa = pessoa;
        this.agencia = agencia;
    }

    public Conta() {
        this.tipoConta = new TipoConta();
        this.pessoa = new Pessoa();
        this.agencia = new Agencia();
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean getContaAtiva() {
        return contaAtiva;
    }

    public void setContaAtiva(boolean contaAtiva) {
        this.contaAtiva = contaAtiva;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }
}
