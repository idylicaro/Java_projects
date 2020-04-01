package Testes;

import Util.ValidarCPF;

import static org.junit.jupiter.api.Assertions.*;

class ValidarCPFTest {

    @org.junit.jupiter.api.Test
    void junitValidarCPF() {
        //cpf valido
        assertEquals(true, ValidarCPF.validarCPF("33164818505"));
        //cps invalidos
        //assertEquals(false,ValidarCPF.validarCPF("abcdaesftfs"));
        assertEquals(false,ValidarCPF.validarCPF("99999999999"));
        assertEquals(false,ValidarCPF.validarCPF("999999999999"));
        assertEquals(false,ValidarCPF.validarCPF("331648185055"));
        assertEquals(false,ValidarCPF.validarCPF("18758957814"));
        //assertEquals(true, ValidarCPF.validarCPF("3316481850")); //no regex impedir menos de 11 caracteres
    }

    @org.junit.jupiter.api.Test
    void junitvalidarCNPJ() {
        //Cnpj valido
        assertEquals(true,ValidarCPF.ValidarCNPJ("98551202000126"));
        //cnpj Invalidos
        //assertEquals(false,ValidarCPF.ValidarCNPJ("9855120200012a6"));  // tira no regex a possibilidade de entrada com char no meio
        assertEquals(false,ValidarCPF.ValidarCNPJ("9855120200016"));
        assertEquals(false,ValidarCPF.ValidarCNPJ("985512020001267"));
        assertEquals(false,ValidarCPF.ValidarCNPJ("11111111111111"));
    }

    @org.junit.jupiter.api.Test
    void junitValidarRG() {
        //RG valido
        assertEquals(true,ValidarCPF.ValidarRG("232343627"));
        //RG Invalido
        assertEquals(false,ValidarCPF.ValidarRG("2323436277"));
        assertEquals(false,ValidarCPF.ValidarRG("23234277"));
        assertEquals(false,ValidarCPF.ValidarRG("888888888"));
    }
}