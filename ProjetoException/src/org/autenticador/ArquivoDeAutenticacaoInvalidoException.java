package org.autenticador;

public class ArquivoDeAutenticacaoInvalidoException extends RuntimeException{
    public ArquivoDeAutenticacaoInvalidoException(){

    }
    public ArquivoDeAutenticacaoInvalidoException(String mensage){
        super(mensage);
    }
    public ArquivoDeAutenticacaoInvalidoException(Throwable cause){
        super(cause);
    }
    public ArquivoDeAutenticacaoInvalidoException(String mensage, Throwable cause){
        super(mensage, cause);
    }
    public ArquivoDeAutenticacaoInvalidoException(String mensage, Throwable cause,
                                                  boolean enableSuppression,boolean writableStackTrace){
        super(mensage, cause,enableSuppression,writableStackTrace);
    }

}
