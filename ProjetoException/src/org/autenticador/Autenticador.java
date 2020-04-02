package org.autenticador;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Autenticador {

    private Map<String,Usuario> usuarios = new HashMap<>();

    public Autenticador(String arquivo){
        try {
            Scanner scanner = new Scanner(new File(arquivo));
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String [] userInfo = linha.split("-");
                if(userInfo.length != 3){
                    throw  new ArquivoDeAutenticacaoInvalidoException("Formato errado:" + linha);
                }else {
                    Usuario u = new Usuario(userInfo[0],userInfo[1],userInfo[2]);
                    usuarios.put(u.getLogin(),u);
                }

            }
        } catch (FileNotFoundException e) {
            throw new ArquivoDeAutenticacaoInvalidoException(e.getMessage(),e);
        }
    }

    public Usuario autenticar(String login,String senha) throws AutenticacaoException{
        Usuario u = usuarios.get(login);
        if (u == null){
            throw new UsuarioInvalidoException("O usuário não existe no arquivo.",login);
        }
        if(u.verificaSenha(senha)){
            return u;
        }
        return null;
    }
}
