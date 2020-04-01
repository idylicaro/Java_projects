package Model;

public class Pessoa {
    private int id_pessoa;
    private String nome;
    private String telefone;
    private String nascimento;
    private String cpf_cnpj;
    private char fisica_ou_juridica;// F | J
    private String rg;
    private String rua_endereco;
    private String cep_endereco;
    private int numero_endereco;

    public Pessoa() {
    }

    public Pessoa(int id_pessoa, String nome, String telefone, String nascimento, String cpf_cnpj, char fisica_ou_juridica, String rg, String rua_endereco, String cep_endereco, Cidade cidade) {
        this.id_pessoa = id_pessoa;
        this.nome = nome;
        this.telefone = telefone;
        this.nascimento = nascimento;
        this.cpf_cnpj = cpf_cnpj;
        this.fisica_ou_juridica = fisica_ou_juridica;
        this.rg = rg;
        this.rua_endereco = rua_endereco;
        this.cep_endereco = cep_endereco;
        this.cidade = cidade;
    }

    private Cidade cidade;

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public int getId_pessoa() {
        return id_pessoa;
    }

    public void setId_pessoa(int id_pessoa) {
        this.id_pessoa = id_pessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    public char getFisica_ou_juridica() {
        return fisica_ou_juridica;
    }

    public void setFisica_ou_juridica(char fisica_ou_juridica) {
        this.fisica_ou_juridica = fisica_ou_juridica;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getRua_endereco() {
        return rua_endereco;
    }

    public void setRua_endereco(String rua_endereco) {
        this.rua_endereco = rua_endereco;
    }

    public String getCep_endereco() {
        return cep_endereco;
    }

    public void setCep_endereco(String cep_endereco) {
        this.cep_endereco = cep_endereco;
    }

    public int getNumero_endereco() {
        return numero_endereco;
    }

    public void setNumero_endereco(int numero_endereco) {
        this.numero_endereco = numero_endereco;
    }
}

