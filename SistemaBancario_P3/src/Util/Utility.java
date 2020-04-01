package Util;

import Model.ContaOperacoes;
import com.jfoenix.controls.JFXButton;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {
    public static void closeWindow(JFXButton btn) {
        Stage stage = (Stage) btn.getScene().getWindow(); //Obtendo a janela atual
        stage.close(); //Fechando o Stage
    }

    public static String getDataTimeSystem() {
        Date date = new Date();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return dateFormat.format(date);
    }
    public static String getDataTimeSystemToNameArquivo() {
        Date date = new Date();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd+(HH-mm-ss)");

        return dateFormat.format(date);
    }


    public static boolean Regex(String Senha) {

        final String regex = "\\D";

        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(Senha);
        if (ConferirQNumeros(Senha)) {
            return !(matcher.find());
        } else {
            return false;
        }
    }

    public static boolean ConferirQNumeros(String Senha) {

        return Senha.length() > 4 && Senha.length() <= 10;
    }
    public static void geraArquivoExtrato(List<ContaOperacoes> operacoesBancarias,int numeroConta ,String nomeTitular,int agencia) throws IOException {
        FileWriter arq = new FileWriter(getDataTimeSystemToNameArquivo()+"-"+String.valueOf(numeroConta)+".txt");
        PrintWriter gravarArq = new PrintWriter(arq);

        gravarArq.printf("----------------------------\n");
        gravarArq.printf("Extrato da conta: %d\n",numeroConta);
        gravarArq.printf("Agencia: %d\n",agencia);
        gravarArq.printf("Titular: %s.\n",nomeTitular);
        gravarArq.printf("----------------------------\n");
        for (ContaOperacoes op : operacoesBancarias) {
            gravarArq.printf("Operação: %s\n",op.getOperacao().getNome_tipo_Operacao());
            gravarArq.printf("Data/Horario da Transição: %s\n",op.getData_Operacao());
            gravarArq.printf("Valor: %.2f\n",op.getValor());
            if(op.getOperacao().getNome_tipo_Operacao().equals("Transferencia")){
                gravarArq.printf("Conta destino: %d\n",op.getContaDestinatario().getNumeroConta());
            }
            gravarArq.printf("\n----------------------------\n");
        }
        arq.close();
    }
}
