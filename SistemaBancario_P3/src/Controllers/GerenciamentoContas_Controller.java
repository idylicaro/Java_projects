package Controllers;

import DAO.ContaDao;
import Model.Conta;
import Model.TableContas;
import SistemaBancario.Main;
import Util.TableConfig;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GerenciamentoContas_Controller implements Initializable {
    @FXML
    private JFXButton btnCadastrar;

    @FXML
    private JFXButton btnModificar;

    @FXML
    private JFXButton bntAlteraStatus;

    @FXML
    private JFXTreeTableView<TableContas> jfxtable;

    @FXML
    private JFXTextField jtfConsulta;

    @FXML
    private JFXButton btnCadastrarExistente;

    @FXML
    void onCadastrarExistente(ActionEvent event) {
        // o retorno do get.selectedIndex quando não tem nada selecionado é -1
        if (jfxtable.getSelectionModel().getSelectedIndex() >= 0) {
            Conta conta = new Conta();
            conta.setNumeroConta(Integer.parseInt(jfxtable.getSelectionModel().getSelectedItem().getValue().numConta.getValue()));
            conta.getTipoConta().setId_tipoConta(Integer.parseInt(jfxtable.getSelectionModel().getSelectedItem().getValue().id_tipoconta.getValue()));
            Main.changeScreen("cadastroTipoConta", conta);
        } else {
            Main.changeScreen("mensageBox", "Escolha uma conta da tabela para poder modificar");
        }
    }

    @FXML
    void onAtualizar(MouseEvent event) {
        prencheTable(getData());
    }

    @FXML
    void onCadastrar(ActionEvent event) {
        Main.changeScreen("cadastroContaForm");
    }

    @FXML
    void onAlteraStatus(ActionEvent event) {
        // o retorno do get.selectedIndex quando não tem nada selecionado é -1
        if (jfxtable.getSelectionModel().getSelectedIndex() >= 0) {
            Conta conta = new Conta();
            conta.setNumeroConta(Integer.parseInt(jfxtable.getSelectionModel().getSelectedItem().getValue().numConta.getValue()));
            conta.setContaAtiva(!Boolean.parseBoolean(jfxtable.getSelectionModel().getSelectedItem().getValue().contaStatus.getValue()));
            ContaDao contaDao = new ContaDao();
            contaDao.updateAtiveConta(conta);
        } else {
            Main.changeScreen("mensageBox", "Escolha uma conta da tabela para poder altera seu status");
        }

    }

    @FXML
    void onModificar(ActionEvent event) {
        // o retorno do get.selectedIndex quando não tem nada selecionado é -1
        if (jfxtable.getSelectionModel().getSelectedIndex() >= 0) {
            Main.changeScreen("alteraContaForm", jfxtable.getSelectionModel().getSelectedItem().getValue().numConta.getValue());
        } else {
            Main.changeScreen("mensageBox", "Escolha uma conta da tabela para poder modificar");
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //CRIANDO COLUNAS

        JFXTreeTableColumn<TableContas, String> numCol = new JFXTreeTableColumn<>("Numero Conta");
        TableConfig.setConfigCol(numCol,100);
        //call back para buscar os valores
        numCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TableContas, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<TableContas, String> param) {
                return param.getValue().getValue().numConta;
            }
        });

        JFXTreeTableColumn<TableContas, String> titularCol = new JFXTreeTableColumn<>("Titular");
        TableConfig.setConfigCol(titularCol);
        //call back para buscar os valores
        titularCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TableContas, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<TableContas, String> param) {
                return param.getValue().getValue().titular;
            }
        });

        JFXTreeTableColumn<TableContas, String> statusCol = new JFXTreeTableColumn<>("Status");
        TableConfig.setConfigCol(statusCol);
        //call back para buscar os valores
        statusCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TableContas, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<TableContas, String> param) {
                return param.getValue().getValue().contaStatus;
            }
        });

        JFXTreeTableColumn<TableContas, String> saldoCol = new JFXTreeTableColumn<>("Tipo Conta");
        TableConfig.setConfigCol(saldoCol);
        //call back para buscar os valores
        saldoCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TableContas, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<TableContas, String> param) {
                return param.getValue().getValue().id_tipoconta;
            }
        });
        jfxtable.getColumns().setAll(numCol, titularCol, statusCol, saldoCol);

        prencheTable(getData());
        //Evento da barra de consulta
        jtfConsulta.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                prencheTable(getData());
            }
        });

    }
    public void prencheTable(ObservableList<TableContas> contas){
        TreeItem<TableContas> root = new RecursiveTreeItem<TableContas>(contas, RecursiveTreeObject::getChildren);
        jfxtable.setRoot(root);
        jfxtable.setShowRoot(false);
    }
    public ObservableList<TableContas> getData(){
        ContaDao contaDao = new ContaDao();
        ObservableList<TableContas> contas = FXCollections.observableArrayList();
        List<TableContas> listaContas = contaDao.consultaAndViewTable(jtfConsulta.getText());
        contas.addAll(listaContas);
        return contas;
    }
}
