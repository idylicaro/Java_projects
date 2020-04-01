package Controllers;

import DAO.ContaDao;
import DAO.PessoaDao;
import DAO.TipoContaDao;
import Model.*;
import SistemaBancario.Main;
import Util.Utility;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CadastroConta_Tipo_Control implements Initializable {
    private Conta conta;
    @FXML
    private JFXTextField fieldNumConta;

    @FXML
    private JFXComboBox<Label> fieldTipoDeConta;

    @FXML
    private JFXButton btnCancelar;

    @FXML
    private JFXButton btnCadastrar;

    @FXML
    void onCadastrar(ActionEvent event) {
        if (fieldTipoDeConta.getValue() != null && (Integer.parseInt(String.valueOf(fieldTipoDeConta.getValue().getText().charAt(0))) != conta.getTipoConta().getId_tipoConta())){
            conta.getTipoConta().setId_tipoConta(Integer.parseInt(String.valueOf(fieldTipoDeConta.getValue().getText().charAt(0))));
            ContaDao contaDao = new ContaDao();
            contaDao.inserirConta(conta);
            Main.changeScreen("mensageBox","Tipo de conta criada!");
            Utility.closeWindow(btnCadastrar);
        }else {
            Main.changeScreen("mensageBox","Escolha um tipo de conta valido!");
        }
    }

    @FXML
    void onCancelar(ActionEvent event) {
        Utility.closeWindow(btnCancelar);
    }

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.addOnChangeScreenListeners(new Main.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object userData) {
                if (newScreen.equals("cadastroTipoConta")) {
                    conta = (Conta) userData;
                    ContaDao contaDao = new ContaDao();
                    contaDao.carregarModel(conta);
                    conta.getPessoa().setId_pessoa(conta.getNumeroConta());
                    fieldNumConta.setText(String.valueOf(conta.getNumeroConta()));
                    fieldNumConta.setDisable(true);
                }
            }
        });

        TipoContaDao tipoContaDao = new TipoContaDao();
        List<TipoConta> listaTipoConta = tipoContaDao.listarTiposConta();

        for (TipoConta list: listaTipoConta ) {
            fieldTipoDeConta.getItems().add(new Label(list.getId_tipoConta()+"-"+list.getNomeTipo() ) );
        }

    }
}
