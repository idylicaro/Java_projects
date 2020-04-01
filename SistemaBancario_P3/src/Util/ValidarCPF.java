package Util;

import java.util.regex.Pattern;

public class ValidarCPF {
    public static Boolean validarCPF(String CPF) {
        int Soma = 0;
        if (("66666666666".equals(CPF)) || ("55555555555".equals(CPF)) || ("11111111111".equals(CPF)) || ("33333333333".equals(CPF)) ||
                ("22222222222".equals(CPF)) || ("44444444444".equals(CPF)) || ("77777777777".equals(CPF)) || ("88888888888".equals(CPF)) ||
                ("99999999999".equals(CPF)) || ("00000000000".equals(CPF)) || (CPF.length() != 11)) {
            return false;
        } else {

            String[] Separador = CPF.split(Pattern.quote(""));
            for (int x = 0; x < 11; x++) {
                int Converter = Integer.parseInt(Separador[x]);
                Soma += Converter;
            }
            if (Soma % 11 == 0) {
                return true;
            } else {
                return false;
            }
        }

    }


    public static boolean ValidarCNPJ(String CNPJ) {

        int Verificar;
        if (("66666666666666".equals(CNPJ)) || ("55555555555555".equals(CNPJ)) || ("11111111111111".equals(CNPJ)) || ("33333333333333".equals(CNPJ)) ||
                ("22222222222222".equals(CNPJ)) || ("44444444444444".equals(CNPJ)) || ("77777777777777".equals(CNPJ)) || ("88888888888888".equals(CNPJ)) ||
                ("99999999999999".equals(CNPJ)) || (CNPJ.length() != 14)) {
            return false;
        } else {
            int cont = 0;
            String[] Separador = CNPJ.split(Pattern.quote(""));
            for (int x = 0; x < 14; x++) {
                int Converter = Integer.parseInt(Separador[x]);

                Verificar = Converter;
                if (Verificar != '0' || Verificar != '1' || Verificar != '2' || Verificar != '3' || Verificar != '4' || Verificar != '5' || Verificar != '6' || Verificar != '7'
                        || Verificar != '8' || Verificar != '9') {
                    cont += 1;
                }

            }
            if (cont == 14) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static boolean ValidarRG(String RG) {

        int Verificar;
        if (("666666666".equals(RG)) || ("555555555".equals(RG)) || ("111111111".equals(RG)) || ("333333333".equals(RG)) ||
                ("222222222".equals(RG)) || ("444444444".equals(RG)) || ("777777777".equals(RG)) || ("888888888".equals(RG)) ||
                ("999999999".equals(RG)) || (RG.length()!=9)) {
            return false;
        } else {
            int cont = 0;
            String[] Separador = RG.split(Pattern.quote(""));
            for (int x = 0; x < 9; x++) {
                int Converter = Integer.parseInt(Separador[x]);

                Verificar = Converter;
                if (Verificar != '0' || Verificar != '1' || Verificar != '2' || Verificar != '3' || Verificar != '4' || Verificar != '5' ||
                        Verificar != '6' || Verificar != '7' || Verificar != '8' || Verificar != '9') {
                    cont += 1;
                }


            }
            if (cont == 9) {
                return true;
            } else {
                return false;
            }
        }
    }

}
