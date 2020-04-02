package org.autenticador;

public class Usuario {
    private String login;
    private String senha;
    private String acesso;

    public Usuario(String login, String senha, String acesso) {
        this.login = login;
        this.senha = senha;
        this.acesso = acesso;
    }

    public String getLogin() {
        return login;
    }
    public boolean verificaSenha(String senha){
        return this.senha.equals(senha);
    }
    public String getAcesso() {
        return acesso;
    }
}
