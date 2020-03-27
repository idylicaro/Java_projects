package ProgamUI;

import Program.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static Program.program.displayAutomato;

public class Controller implements Initializable {

    @FXML
    private AnchorPane anchorID;

    @FXML
    private Button btnCarregarAFD;

    @FXML
    private TextField fieldCarregaAFD;

    @FXML
    private Button btnSPCarregarAFD;

    @FXML
    private Button btnSalvarAFD;

    @FXML
    private TextField fieldSalvarAFD;

    @FXML
    private Button btnSPSalvarAFD;

    @FXML
    private TextField fieldNomeAFD;

    @FXML
    private Label labelSucesso;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    void onActionSPCarregarAFD(ActionEvent event) {
        fieldCarregaAFD.setText(selectFile());
    }

    @FXML
    void onActionSPSalvarAFD(ActionEvent event) {
        fieldSalvarAFD.setText(selectPath());
    }
    @FXML
    void OnMinimizar(ActionEvent event) {
            try {
                labelSucesso.setVisible(false);
                // carregando o xml
                ReadXML cxml = new ReadXML();
                List<Estado> estadosFromXml = cxml.extract(fieldCarregaAFD.getText());
                // ordenando as transiões
                for (Estado e : estadosFromXml) {
                    e.sortTransicoes();
                }
                // passo 2
                // Iniciada tabela,prenchida e marcado os não triviais equivalentes
                EQ[] tabela = EQ.prencherEQ(estadosFromXml);

                // passo 3
                EQ.processamento(tabela);

                Automato automato = new Automato();
                automato.listaDeEstados = automato.geraAutomato(tabela, estadosFromXml);
                displayAutomato(automato);
                WriteXML.geraJff(automato, fieldSalvarAFD.getText() + "\\" + fieldNomeAFD.getText() + ".jff");
                labelSucesso.setVisible(true);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                fieldCarregaAFD.setText("");
                fieldNomeAFD.setText("");
            }



    }
    public String selectFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Jff Files","*.jff"));
        Stage stage = (Stage) anchorID.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        if(selectedFile!= null){
            return selectedFile.getAbsolutePath();
        }
        return null;
    }
    public String selectPath(){
        final DirectoryChooser directoryChooser = new DirectoryChooser();

        Stage stage = (Stage) anchorID.getScene().getWindow();
        File file = directoryChooser.showDialog(stage);

        if (file != null){
            return file.getAbsolutePath() ;
        }
        return null;
    }
}
