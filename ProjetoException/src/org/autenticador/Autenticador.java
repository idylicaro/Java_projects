package org.autenticador;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Autenticador {
    public Autenticador(String arquivo){
        try {
            Scanner scanner = new Scanner(new File(arquivo));
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String [] userInfo = linha.split("-");
                if(userInfo.length != 3){
                    throw  new ArquivoDeAutenticacaoInvalidoException("Formato errado:" + linha);
                }
            }
        } catch (FileNotFoundException e) {
            throw new ArquivoDeAutenticacaoInvalidoException(e.getMessage(),e);
        }
    }
}
