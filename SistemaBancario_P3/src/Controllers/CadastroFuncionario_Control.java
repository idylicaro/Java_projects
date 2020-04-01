package Controllers;

import DAO.*;
import Model.*;
import SistemaBancario.Main;
import Util.Utility;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class    CadastroFuncionario_Control implements Initializable {

    @FXML // fx:id="fieldNome"
    private JFXTextField fieldNome; // Value injected by FXMLLoader

    @FXML // fx:id="fieldTelefone"
    private JFXTextField fieldTelefone; // Value injected by FXMLLoader

    @FXML // fx:id="fieldData"
    private JFXDatePicker fieldData; // Value injected by FXMLLoader

    @FXML // fx:id="fieldCpf"
    private JFXTextField fieldCpf; // Value injected by FXMLLoader

    @FXML // fx:id="fieldRg"
    private JFXTextField fieldRg; // Value injected by FXMLLoader

    @FXML // fx:id="fieldCidade;"
    private JFXComboBox<Label> fieldCidade; // Value injected by FXMLLoader

    @FXML // fx:id="fieldCep"
    private JFXTextField fieldCep; // Value injected by FXMLLoader

    @FXML // fx:id="fieldLogradouro"
    private JFXTextField fieldLogradouro; // Value injected by FXMLLoader

    @FXML // fx:id="fieldNumeroCasa"
    private JFXTextField fieldNumeroCasa; // Value injected by FXMLLoader

    @FXML // fx:id="fieldUsuario"
    private JFXTextField fieldUsuario; // Value injected by FXMLLoader

    @FXML // fx:id="fieldSenha"
    private JFXPasswordField fieldSenha; // Value injected by FXMLLoader

    @FXML // fx:id="fieldCargo"
    private JFXComboBox<Label> fieldCargo; // Value injected by FXMLLoader

    @FXML // fx:id="fieldSalario"
    private JFXTextField fieldSalario; // Value injected by FXMLLoader

    @FXML // fx:id="btnCadastrar"
    private JFXButton btnCadastrar; // Value injected by FXMLLoader

    @FXML // fx:id="fieldAgencia"
    private JFXComboBox<Label> fieldAgencia; // Value injected by FXMLLoader

    @FXML
    private JFXButton btnCancelar;

    public boolean verificaFields(){
        boolean resultado = true;
        String mensagem = "Os Campos:";
        if(fieldNome.getText().equals("")){
            resultado = false;
            mensagem += "(Nome Completo) ";
        }
        if(fieldTelefone.getText().equals("") || fieldTelefone.getText().length() > 12){
            resultado = false;
            mensagem += "(Telefone) ";
        }
        if(fieldCpf.getText().equals("") || fieldCpf.getText().length() != 11){
            resultado = false;
            mensagem += "(CPF) ";
        }
        if(fieldRg.getText().equals("") || fieldRg.getText().length() != 9){
            resultado = false;
            mensagem += "(RG) ";
        }
        if(fieldCep.getText().equals("") || fieldCep.getText().length() !=8){
            resultado = false;
            mensagem += "(CEP) ";
        }
        if(fieldLogradouro.getText().equals("")){
            resultado = false;
            mensagem += "(Logradouro) ";
        }
        if(fieldNumeroCasa.getText().equals(0)){
            resultado = false;
            mensagem += "(Nº) ";
        }
        if(fieldUsuario.getText().equals("")){
            resultado = false;
            mensagem += "(Usuario) ";
        }
        if(fieldSenha.getText().equals("")){
            resultado = false;
            mensagem += "(Senha) ";
        }
        if(fieldSalario.equals(0)){
            resultado = false;
            mensagem += "(Salario) ";
        }
        if(fieldData.getValue() == null){
            resultado = false;
            mensagem += "(Data Nascimento)";
        }
        if(fieldCidade.getValue() == null){
            resultado = false;
            mensagem += "(Estado)";
        }
        if(fieldCargo.getValue() == null){
            resultado = false;
            mensagem += "(Cargo)";
       }

        //Aqui precisa criar um "JOptionpane" para mostrar a mensagem;
        mensagem+=", esses campos precisam ser prenchidos corretamente!!!";
        if(!resultado) {
            Main.changeScreen("mensageBox",mensagem);
        }
        return resultado;

    }
    @FXML
    void btnCadastrarActionOn(ActionEvent event) {
        if(verificaFields()) {
            if(verifyUniqueFields()) {
                Funcionario funcionario = new Funcionario();
                Pessoa pessoa = new Pessoa();
                Cidade cidade = new Cidade();
                Cargo cargo = new Cargo();

                pessoa.setNome(fieldNome.getText());
                pessoa.setTelefone(fieldTelefone.getText());
                pessoa.setCpf_cnpj(fieldCpf.getText());
                pessoa.setRg(fieldRg.getText());
                pessoa.setFisica_ou_juridica('f');

                //setando cidade
                try {
                    cidade.setId_cidade(Integer.parseInt(String.valueOf(fieldCidade.getValue().getText().charAt(0))));
                } catch (Exception e) {
                    //ta dando erro pq ta indo null pq n selecionou nada
                    System.out.println("ERRO EM CIDADE" + e);
                }
                pessoa.setCidade(cidade);

                pessoa.setCep_endereco(fieldCep.getText());
                pessoa.setRua_endereco(fieldLogradouro.getText());

                pessoa.setNumero_endereco(Integer.parseInt(fieldNumeroCasa.getText()));
                funcionario.setUsuario(fieldUsuario.getText());
                funcionario.setSenha(fieldSenha.getText());

                funcionario.setSalario(Float.parseFloat(fieldSalario.getText()));

                cargo.setId_cargo(Integer.parseInt(String.valueOf(fieldCargo.getValue().getText().charAt(0))));


                // pegando a data do dataPicker e transformando no formato do java DATE
                try {
                    LocalDate ld = fieldData.getValue();
                    Calendar c = Calendar.getInstance();
                    c.set(ld.getYear(), (ld.getMonthValue() - 1), ld.getDayOfMonth());
                    Date dataNascimento = c.getTime();
                    //aqui formata no padrao que o SQLite quer
                    DateFormat FData = new SimpleDateFormat("yyyy-MM-dd");
                    pessoa.setNascimento(FData.format(dataNascimento));
                } catch (Exception e) {
                    System.out.println(e);
                }


                //finalizando cadastro
                funcionario.setPessoa(pessoa);
                funcionario.setCargo(cargo);

                PessoaDao pessoaDao = new PessoaDao();
                if (pessoaDao.inserirPessoa(pessoa)) {
                    FuncionarioDao funcionarioDao = new FuncionarioDao();
                    if (funcionarioDao.inserirFuncionario(funcionario)) {
                        Main.changeScreen("mensageBox","Cadastro Efetuado!");
                        Utility.closeWindow(btnCadastrar);

                    } else {
                        System.out.println("Algum problema no cadastro!");

                    }
                } else {
                    System.out.println("Algum problema no registro de pessoa!");

                }
            }

        }else{
            Main.changeScreen("mensageBox","Complete todos os campos e tente novamente!");
        }
    }

    @FXML
    void onCancelar(ActionEvent event) {
        Utility.closeWindow(btnCancelar);
    }

    public boolean verifyUniqueFields(){
        PessoaDao pDao = new PessoaDao();
        FuncionarioDao fDao = new FuncionarioDao();
        boolean result = true;
        String mensage = "Estas informações já tem outros usuarios usando :";

        if(!pDao.verifyUniqueConditionTelefone(fieldTelefone.getText())){
            result = false;
            mensage += "(Telefone)";
        }
        if(!pDao.verifyUniqueConditionCpfCnpj(fieldCpf.getText())){
            result = false;
            mensage += "(Cpf)";
        }
        if(!pDao.verifyUniqueConditionRg(fieldRg.getText())){
            result = false;
            mensage += "(RG)";
        }
        if(!fDao.verifyUniqueConditionUsuario(fieldUsuario.getText())){
            result = false;
            mensage += "(Usuario)";
        }

        if(!result){
            Main.changeScreen("mensageBox",mensage);
        }
            return result;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        CidadeDao cidadeDao = new CidadeDao();
        List<Cidade> listaCidades = cidadeDao.listarCidades();

        for (Cidade list: listaCidades ) {
            fieldCidade.getItems().add(new Label(list.getId_cidade()+"-"+list.getNome()));
        }

        CargoDao cargoDao = new CargoDao();
        List<Cargo> listaCargo = cargoDao.listarCargo();

        for (Cargo list:listaCargo){
            fieldCargo.getItems().add(new Label(list.getId_cargo()+"-"+list.getCargo()));
        }
    }
}
