package Controllers;

import DAO.RelatorioDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class relatorio_Controller implements Initializable {

    @FXML
    private Label labelRnContas;

    @FXML
    private Label labelRsAgencia;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RelatorioDAO relatorioDAO = new RelatorioDAO();
        labelRsAgencia.setText(String.valueOf(relatorioDAO.getSaldoAgencia(1)));

        labelRnContas.setText(String.valueOf(relatorioDAO.getNumeroContaAgencia(1)));
    }
}
