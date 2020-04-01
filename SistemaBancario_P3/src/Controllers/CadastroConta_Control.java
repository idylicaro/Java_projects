package Controllers;

import DAO.*;
import Model.*;
import SistemaBancario.Main;
import Util.Utility;
import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class CadastroConta_Control implements Initializable {

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

    @FXML // fx:id="fieldCep"
    private JFXTextField fieldCep; // Value injected by FXMLLoader

    @FXML // fx:id="fieldNumeroCasa"
    private JFXTextField fieldNumeroCasa; // Value injected by FXMLLoader

    @FXML // fx:id="fieldLogradouro"
    private JFXTextField fieldLogradouro; // Value injected by FXMLLoader

    @FXML // fx:id="fieldSenha"
    private JFXPasswordField fieldSenha; // Value injected by FXMLLoader

    @FXML // fx:id="fieldTipoDeConta"
    private JFXComboBox<Label> fieldTipoDeConta; // Value injected by FXMLLoader

    @FXML // fx:id="btnCadastrar"
    private JFXButton btnCadastrar; // Value injected by FXMLLoader

    @FXML // fx:id="fieldCidade"
    private JFXComboBox<Label> fieldCidade; // Value injected by FXMLLoader

    @FXML // fx:id="btnCancelar"
    private JFXButton btnCancelar; // Value injected by FXMLLoader

    @FXML // fx:id="fieldAgencia"
    private JFXComboBox<Label> fieldAgencia; // Value injected by FXMLLoader

    @FXML
    private JFXRadioButton fieldTipoPessoaFisica;

    @FXML
    private ToggleGroup TipoPessoa;

    @FXML
    private JFXRadioButton fieldTipoPessoaJuridica;

    public boolean verificaFields() {
        boolean resultado = true;
        String mensagem = "Os Campos:";

        if (fieldNome.getText().equals("")) {
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
        if (fieldLogradouro.getText().equals("")) {
            resultado = false;
            mensagem += "(Logradouro) ";
        }
        if (fieldNumeroCasa.getText().equals(0)) {
            resultado = false;
            mensagem += "(Nº) ";
        }
        if (fieldSenha.getText().equals("") || Utility.Regex(fieldSenha.getText())) {
            resultado = false;
            mensagem += "(Senha) ";
        }
        if (fieldData.getValue() == null) {
            resultado = false;
            mensagem += "(Data Nascimento)";
        }
        if (fieldCidade.getValue() == null) {
            resultado = false;
            mensagem += "(Estado)";
        }
        if (fieldAgencia.getValue() == null) {
            resultado = false;
            mensagem += "(Agencia)";
        }
        if (fieldTipoDeConta.getValue() == null) {
            resultado = false;
            mensagem += "(Tipo de Conta)";
        }

        //Aqui precisa criar um "JOptionpane" para mostrar a mensagem;
        mensagem += ", esses campos precisam ser prenchidos corretamente!!!";
        if (!resultado) {
            Main.changeScreen("mensageBox", mensagem);
        }
        return resultado;

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

        if(!result){
            Main.changeScreen("mensageBox",mensage);
        }
        return result;
    }
    @FXML
    void btnCadastrarActionOn(ActionEvent event) {
        if(verificaFields()) {
            if(verifyUniqueFields()) {
                Conta conta = new Conta();
                Pessoa pessoa = new Pessoa();
                Cidade cidade = new Cidade();
                TipoConta tipoConta = new TipoConta();
                Agencia agencia = new Agencia();

                pessoa.setNome(fieldNome.getText());
                pessoa.setTelefone(fieldTelefone.getText());
                pessoa.setCpf_cnpj(fieldCpf.getText());
                pessoa.setRg(fieldRg.getText());
                if(fieldTipoPessoaFisica.isSelected()){
                    pessoa.setFisica_ou_juridica('f');
                }else{
                    pessoa.setFisica_ou_juridica('j');
                }

                //setando cidade
                try {
                    cidade.setId_cidade(Integer.parseInt(String.valueOf(fieldCidade.getValue().getText().charAt(0))));
                    tipoConta.setId_tipoConta(Integer.parseInt(String.valueOf(fieldTipoDeConta.getValue().getText().charAt(0))));
                    agencia.setId_Agencia(Integer.parseInt(String.valueOf(fieldAgencia.getValue().getText().charAt(0))));
                } catch (Exception e) {
                    System.out.println("ERRO EM ComboBox" + e);
                }
                pessoa.setCidade(cidade);

                pessoa.setCep_endereco(fieldCep.getText());
                pessoa.setRua_endereco(fieldLogradouro.getText());

                pessoa.setNumero_endereco(Integer.parseInt(fieldNumeroCasa.getText()));
                conta.setSenha(fieldSenha.getText());



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
                conta.setPessoa(pessoa);
                conta.setAgencia(agencia);
                conta.setTipoConta(tipoConta);

                PessoaDao pessoaDao = new PessoaDao();
                if (pessoaDao.inserirPessoa(pessoa)) {
                    ContaDao contaDao = new ContaDao();
                    contaDao.getIdPessoa(conta.getPessoa());
                    conta.setNumeroConta(conta.getPessoa().getId_pessoa());
                    if (contaDao.inserirConta(conta)) {
                        Main.changeScreen("mensageBox","Cadastro Efetuado!");
                        Utility.closeWindow(btnCadastrar);

                    } else {
                        System.out.println("Algum problema no cadastro!");

                    }
                } else {
                    System.out.println("Algum problema no registro de pessoa!");

                }
            }

        }
    }

    @FXML
    void onCancelar(ActionEvent event) {
        Utility.closeWindow(btnCancelar);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        CidadeDao cidadeDao = new CidadeDao();
        List<Cidade> listaCidades = cidadeDao.listarCidades();

        for (Cidade list: listaCidades ) {
            fieldCidade.getItems().add(new Label(list.getId_cidade()+"-"+list.getNome()));
        }

        TipoContaDao tipoContaDao = new TipoContaDao();
        List<TipoConta> listaTipoConta = tipoContaDao.listarTiposConta();

        for (TipoConta list: listaTipoConta ) {
            fieldTipoDeConta.getItems().add(new Label(list.getId_tipoConta()+"-"+list.getNomeTipo() ) );
        }

        AgenciaDao agenciaDao = new AgenciaDao();
        List<Agencia> listaAgencia = agenciaDao.listarAgencias();

        for (Agencia list : listaAgencia){
            fieldAgencia.getItems().add(new Label(String.valueOf(list.getId_Agencia()) ) );
        }

    }
}

