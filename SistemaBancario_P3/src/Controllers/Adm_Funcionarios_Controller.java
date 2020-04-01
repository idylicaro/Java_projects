package Controllers;

import DAO.FuncionarioDao;
import SistemaBancario.Main;
import Model.TableFuncionario;
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

public class Adm_Funcionarios_Controller implements Initializable {

    @FXML // fx:id="btnCadastrar"
    private JFXButton btnCadastrar; // Value injected by FXMLLoader

    @FXML // fx:id="btnModificar"
    private JFXButton btnModificar; // Value injected by FXMLLoader

    @FXML // fx:id="bntExcluir"
    private JFXButton bntExcluir; // Value injected by FXMLLoader

    @FXML // fx:id="Jfxtable"
    private JFXTreeTableView<TableFuncionario> Jfxtable; // Value injected by FXMLLoader

    @FXML // fx:id="jtfConsulta"
    private JFXTextField jtfConsulta; // Value injected by FXMLLoader
    private ActionEvent event;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //INICIALIZANDO COL
        JFXTreeTableColumn<TableFuncionario, String> idCol = new JFXTreeTableColumn<>("ID");
        TableConfig.setConfigCol(idCol);
        idCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TableFuncionario, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<TableFuncionario, String> param) {
                return param.getValue().getValue().id_funcionario;
            }
        });

        JFXTreeTableColumn<TableFuncionario, String> nomeCol = new JFXTreeTableColumn<>("Nome");
        TableConfig.setConfigCol(nomeCol);
        nomeCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TableFuncionario, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<TableFuncionario, String> param) {
                return param.getValue().getValue().nome;
            }
        });

        JFXTreeTableColumn<TableFuncionario, String> cargoCol = new JFXTreeTableColumn<>("Cargo");
        TableConfig.setConfigCol(cargoCol);
        cargoCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TableFuncionario, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<TableFuncionario, String> param) {
                return param.getValue().getValue().cargo;
            }
        });
        Jfxtable.getColumns().setAll(idCol, nomeCol, cargoCol);

        prencheTable();

        jtfConsulta.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                prencheTable();
            }
        });
    }

    @FXML
    void onAtualizar(MouseEvent event) {

    }

    public void prencheTable() {
        FuncionarioDao fdao = new FuncionarioDao();
        ObservableList<TableFuncionario> funcionarios = FXCollections.observableArrayList();
        List<TableFuncionario> listaFunc;

        listaFunc = fdao.consultaAndViewTable(jtfConsulta.getText());


        funcionarios.addAll(listaFunc);

        //biuld table
        final TreeItem<TableFuncionario> tableRoot = new RecursiveTreeItem<TableFuncionario>(funcionarios, RecursiveTreeObject::getChildren);

        Jfxtable.setRoot(tableRoot);
        Jfxtable.setShowRoot(false);


    }

    @FXML
    void onCadastrar(ActionEvent event) {
        Main.changeScreen("cadastroFuncionarioForm");
    }

    @FXML
    void onModificar(ActionEvent event) {
        // o retorno do get.selectedIndex quando não tem nada selecionado é -1
        if (Jfxtable.getSelectionModel().getSelectedIndex() >= 0) {
            Main.changeScreen("alteraFuncionarioForm", Jfxtable.getSelectionModel().getSelectedItem().getValue().id_funcionario.getValue());
        } else {
            Main.changeScreen("mensageBox", "Escolha um funcionario da tabela para poder modificar");
        }
    }

    @FXML
    void onExcluir(ActionEvent event) {
        // o retorno do get.selectedIndex quando não tem nada selecionado é -1
        if (Jfxtable.getSelectionModel().getSelectedIndex() >= 0) {
            FuncionarioDao funcionarioDao = new FuncionarioDao();
            funcionarioDao.excluirFuncionario(Integer.parseInt(Jfxtable.getSelectionModel().getSelectedItem().getValue().id_funcionario.getValue()));
            Main.changeScreen("mensageBox","Excluido com sucesso!");
        } else {
            Main.changeScreen("mensageBox", "Escolha um funcionario da tabela para poder modificar");
        }
    }
}
