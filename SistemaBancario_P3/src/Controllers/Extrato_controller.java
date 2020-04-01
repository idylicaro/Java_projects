package Controllers;

import DAO.OperacaoBancariaDao;
import Model.Conta;
import Model.ContaOperacoes;
import Model.ContaTableLog;
import SistemaBancario.Main;
import Util.Utility;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Extrato_controller implements Initializable {
    private Conta user;

    @FXML
    private JFXButton btnClose;
    @FXML
    private TableView<ContaTableLog> tabela;

    @FXML
    private TableColumn<ContaTableLog, String> operacaoCol;

    @FXML
    private TableColumn<ContaTableLog, String> dataCol;

    @FXML
    private TableColumn<ContaTableLog, Double> valorCol;

    @FXML
    private TableColumn<ContaTableLog, String> contaDestinoCol;

    @FXML
    private Label lbImprimindo;

    @FXML
    void onClose(ActionEvent event) {
        Utility.closeWindow(btnClose);
    }

    @FXML
    void onVisualizar(ActionEvent event) {
        ObservableList<ContaTableLog> historicoView = listaDeOperacoes(user);
        tabela.setItems(historicoView);
    }
    @FXML
    void onImprimir(ActionEvent event) {
        gerarArchive(user);
        lbImprimindo.setVisible(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.addOnChangeScreenListeners(new Main.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object userData) {
                if(newScreen.equals("extrato")){
                    user = (Conta) userData;
                }
            }
        });
        // n se pode botar funções que precide informações logo de inicio no initializable , pq ele fica se atualizandoa cada momento

        //TABELA
        operacaoCol.setCellValueFactory(
                new PropertyValueFactory<>("operacao"));
        dataCol.setCellValueFactory(
                new PropertyValueFactory<>("data"));
        valorCol.setCellValueFactory(
                new PropertyValueFactory<>("valor"));
        contaDestinoCol.setCellValueFactory(
                new PropertyValueFactory<>("ContaDestino"));

    }
    private ObservableList<ContaTableLog> listaDeOperacoes(Conta user) {
        OperacaoBancariaDao operacaoBancariaDao = new OperacaoBancariaDao();
        List<ContaOperacoes> listOperacao = operacaoBancariaDao.carregaExtratoConta(user);
        List<ContaTableLog> tableList = new ArrayList<ContaTableLog>();
        ContaTableLog ctl;
        for (ContaOperacoes cop: listOperacao) {
            String numContaDestino = String.valueOf(cop.getContaDestinatario().getNumeroConta());
            if(cop.getContaDestinatario() == null || cop.getContaDestinatario().getNumeroConta() == 0){
                numContaDestino = "Nenhuma";
            }
            ctl = new ContaTableLog(cop.getOperacao().getNome_tipo_Operacao(),cop.getData_Operacao(),cop.getValor(),numContaDestino);
            tableList.add(ctl);
        }
        return FXCollections.observableArrayList(tableList);
    }
    public static void gerarArchive(Conta user){
        OperacaoBancariaDao operacaoBancariaDao = new OperacaoBancariaDao();
        List<ContaOperacoes> listOperacao = operacaoBancariaDao.carregaExtratoConta(user);
        if(!listOperacao.isEmpty()){
            try {
                Utility.geraArquivoExtrato(listOperacao, user.getNumeroConta(), user.getPessoa().getNome(), user.getAgencia().getId_Agencia());
            } catch (IOException e) {
                System.out.println("Erro ao gerar Extrato:"+e);
            }
        }else{
            Main.changeScreen("mensageBox", "Não há operações para gerar o extrato");
        }
    }
}
