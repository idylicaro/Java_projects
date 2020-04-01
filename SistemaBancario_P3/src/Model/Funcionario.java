package Model;

public class Funcionario {
    private int id_funcionario;
    private String usuario;
    private String senha;
    private float salario;

    private Cargo cargo;
    private Pessoa pessoa;

    public Funcionario(int id_funcionario, String usuario, String senha, float salario, Cargo cargo, Pessoa pessoa) {
        this.id_funcionario = id_funcionario;
        this.usuario = usuario;
        this.senha = senha;
        this.salario = salario;
        this.cargo = cargo;
        this.pessoa = pessoa;
    }

    public Funcionario() {

    }

    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }


}
