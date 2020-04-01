package Controllers;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import Model.Funcionario;
import SistemaBancario.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javax.swing.*;

public class SideBar_Controller implements Initializable{
    //Funcionario atual do sistema
    private Funcionario userSystem;

    @FXML
    private BorderPane borderpane;

    @FXML
    private AnchorPane anchorpane_sizebar;

    @FXML
    private void home(javafx.scene.input.MouseEvent mouseEvent){
        Carregar_UI("Home_UI");
    } //Criando evento para quando o usuário clicar no botão "Home" da SideBar

    @FXML
    private void abrirconta(javafx.scene.input.MouseEvent mouseEvent){
        if(userSystem.getCargo().getId_cargo() == 1 || userSystem.getCargo().getId_cargo() == 3) {
            Carregar_UI("Gerenciamento_Contas_UI");
        }else{
            Main.changeScreen("mensageBox","Usuario não tem permissão!");
        }
    }
    @FXML
    void CaixaPage(javafx.scene.input.MouseEvent mouseEvent) {
        if(userSystem.getCargo().getId_cargo() == 1 || userSystem.getCargo().getId_cargo() == 2) {
            Carregar_UI("PanelCaixa_UI");
        }else{
            Main.changeScreen("mensageBox","Usuario não tem permissão!");
        }
    }

    @FXML
    public void ADMPage(javafx.scene.input.MouseEvent mouseEvent) {
        if(userSystem.getCargo().getId_cargo() == 1) {
            Carregar_UI("ADM_Funcionarios_UI");
        }else{
            Main.changeScreen("mensageBox","Usuario não tem permissão!");
        }
    }

    private void Carregar_UI(String ui){
        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource("../View/" + ui + ".fxml")); //Criando caminho para o "FXML" da aba que quer carregar
        } catch (IOException ex) {
            Logger.getLogger(SideBar_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        borderpane.setCenter(root);
        borderpane.setRight(null);
        borderpane.setBottom(null);
    }
    //sobrecarga
    private void Carregar_UI(String ui,String leftUi){
        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource("../View/" + ui + ".fxml")); //Criando caminho para o "FXML" da aba que quer carregar
        } catch (IOException ex) {
            Logger.getLogger(SideBar_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        borderpane.setCenter(root);
        try{
            root = FXMLLoader.load(getClass().getResource("../View/" + leftUi + ".fxml")); //Criando caminho para o "FXML" da aba que quer carregar
        } catch (IOException ex) {
            Logger.getLogger(SideBar_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        borderpane.setRight(root);
    }

    /*@FXML
    private void dark_white(){
        anchorpane_sizebar.setStyle("-fx-background-color: #0000ff"); //mudar cor da sidebar7
    }*/

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Carregar_UI("Home_UI");
        // aqui ta criando a callback que vai ficar esperando sempre que for trocada de janela com o metodo la da main , passar o funcionario logado
        Main.addOnChangeScreenListeners(new Main.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object userData) {
                if(newScreen.equals("sidebar")){
                    userSystem = (Funcionario) userData;
                }
            }
        });

    }

    public void relatorioPage(javafx.scene.input.MouseEvent mouseEvent) {
        if(userSystem.getCargo().getId_cargo() == 1 ) {
            Carregar_UI("relatorio_UI");
        }else{
            Main.changeScreen("mensageBox","Usuario não tem permissão!");
        }
    }
}
