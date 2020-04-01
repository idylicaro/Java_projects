package Controllers;

import DAO.ContaDao;
import DAO.OperacaoBancariaDao;
import Model.Conta;
import Model.ContaOperacoes;
import Model.TipoConta;
import SistemaBancario.Main;
import Util.Utility;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PanelCaixa_Controller implements Initializable {

    @FXML
    private JFXTextField fieldNumConta;

    @FXML
    private JFXButton btnValidar;

    @FXML
    private Label labelVerificado;

    @FXML
    private JFXPasswordField fieldSenha;

    @FXML
    private JFXTextField fieldAgencia;

    @FXML
    private JFXTextField fieldTipoConta;

    @FXML
    private Label labelNome;

    @FXML
    private Label labelCpf;

    @FXML
    private Label labelTipoConta;

    @FXML
    private JFXButton btnDepositar;

    @FXML
    private JFXTextField fieldValorDepositar;

    @FXML
    private JFXButton btnSacar;

    @FXML
    private JFXTextField fieldValorSaque;

    @FXML
    private JFXButton btnTransferir;

    @FXML
    private JFXTextField fieldValorTed;

    @FXML
    private JFXTextField fieldDestinatarioTed;

    @FXML
    private JFXTextField fieldTipoContaTed;

    @FXML
    private JFXButton btnGerarExtrato;

    @FXML
    private Label labelSaldoConta;

    private Conta conta;


    public void deposito(Conta conta, double valor) {
        //APLICAR REGRAS DE NEGOCIO AQUI
        conta.setSaldo((conta.getSaldo())+valor);
    }


    public boolean saque(Conta conta, double valor) {
        if(valor <= conta.getSaldo()){
            //APLICAR REGRAS DE NEGOCIO AQUI
            conta.setSaldo(conta.getSaldo()-valor);
            return true;
        }
        return false;
    }

    public boolean transferencia(Conta remetente, Conta destinatario, double valor) {
        //verifica se há saldo e reduz no model
        if(valor <= remetente.getSaldo()){
            //APLICAR REGRAS DE NEGOCIO AQUI
            remetente.setSaldo(remetente.getSaldo() - valor);
            destinatario.setSaldo(destinatario.getSaldo() + valor);
            return true;
        }
        return false;
    }
    //--METODOS DOS BOTÕES---------------------------------------------------------------------------------->
    @FXML
    void onDepositar(ActionEvent event) {
        Double valor = Double.parseDouble(fieldValorDepositar.getText());

        deposito(conta,valor);
        ContaDao contaDao = new ContaDao();
        if(contaDao.atualizarSaldoConta(conta)){
            //salvando no historico do cliente
            OperacaoBancariaDao operacaoBancariaDao = new OperacaoBancariaDao();
            operacaoBancariaDao.inserirOperaçãolInLog(conta,null, Utility.getDataTimeSystem(),3,valor);
            Main.changeScreen("mensageBox","Deposito Efetuado!!!");
        }else{
            Main.changeScreen("mensageBox","Algum Problema no deposito!");
        }
    }

    @FXML
    void onTransferir(ActionEvent event) {
        Double valor = Double.parseDouble(fieldValorTed.getText());
        ContaDao contaDao = new ContaDao();
        if (contaDao.verifyExistConta(fieldDestinatarioTed.getText(),fieldTipoContaTed.getText())){

            Conta destinatario = new Conta();
            destinatario.setNumeroConta(Integer.parseInt(fieldDestinatarioTed.getText()));
            destinatario.getTipoConta().setId_tipoConta(Integer.parseInt(fieldTipoContaTed.getText()));
            contaDao.carregarModel(destinatario);

            if(transferencia(conta, destinatario, valor)){
                contaDao.atualizarSaldoConta(conta);
                contaDao.atualizarSaldoConta(destinatario);
                //salvando no historico do cliente
                OperacaoBancariaDao operacaoBancariaDao = new OperacaoBancariaDao();
                operacaoBancariaDao.inserirOperaçãolInLog(conta, destinatario, Utility.getDataTimeSystem(),1,valor);
                Main.changeScreen("mensageBox","Transferencia Feita!");
            }else {
                Main.changeScreen("mensageBox","Saldo Insuficiente!");
            }

        }else{
            Main.changeScreen("mensageBox","Conta destino não encontrada!");
        }
    }
    @FXML
    void onExtrato(ActionEvent event) {
        Main.changeScreen("extrato",conta);
    }

    @FXML
    void onValidar(ActionEvent event) {
        ContaDao contaDao = new ContaDao();
        conta = new Conta();
        conta.setNumeroConta(Integer.parseInt(fieldNumConta.getText()));
        conta.setSenha(fieldSenha.getText());
        conta.getAgencia().setId_Agencia(Integer.parseInt(fieldAgencia.getText()));
        conta.getTipoConta().setId_tipoConta(Integer.parseInt(fieldTipoConta.getText()));

        if (contaDao.validarContaEcarregarModel(conta)){
            labelVerificado.setVisible(true);
            labelNome.setText(conta.getPessoa().getNome());
            labelCpf.setText(conta.getPessoa().getCpf_cnpj());
            labelTipoConta.setText(String.valueOf(conta.getTipoConta().getId_tipoConta()));
            labelSaldoConta.setText(String.valueOf(conta.getSaldo()));
            desabilitaBtn(false);
        }else{
            labelVerificado.setVisible(false);
            labelNome.setText("");
            labelCpf.setText("");
            labelTipoConta.setText("");
            labelSaldoConta.setText("");
            desabilitaBtn(true);
        }
    }

    @FXML
    void onSacar(ActionEvent event) {
        Double valor = Double.parseDouble(fieldValorSaque.getText());
        if(saque(conta, valor)){
            ContaDao contaDao = new ContaDao();
            if(contaDao.atualizarSaldoConta(conta)) {
                //salvando no historico do cliente
                OperacaoBancariaDao operacaoBancariaDao = new OperacaoBancariaDao();
                operacaoBancariaDao.inserirOperaçãolInLog(conta,null, Utility.getDataTimeSystem(),2,valor);
                Main.changeScreen("mensageBox", "Saque Efetuado Com Sucesso!!");
            }
        }else{
            Main.changeScreen("mensageBox","Saldo insuficiente!!");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        desabilitaBtn(true);
    }
    public void desabilitaBtn(boolean value){
        btnDepositar.setDisable(value);
        btnSacar.setDisable(value);
        btnTransferir.setDisable(value);
        btnGerarExtrato.setDisable(value);
    }
}
