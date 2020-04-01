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

public class AlterarConta_Controller implements Initializable {
    @FXML
    private JFXTextField fieldNome;

    @FXML
    private JFXTextField fieldTelefone;

    @FXML
    private JFXDatePicker fieldData;

    @FXML
    private JFXTextField fieldCpf;

    @FXML
    private JFXTextField fieldRg;

    @FXML
    private JFXTextField fieldCep;

    @FXML
    private JFXTextField fieldNumeroCasa;

    @FXML
    private JFXTextField fieldLogradouro;

    @FXML
    private JFXPasswordField fieldSenha;

    @FXML
    private JFXComboBox<Label> fieldTipoDeConta;

    @FXML
    private JFXButton btnSalvar;

    @FXML
    private JFXComboBox<Label> fieldCidade;

    @FXML
    private JFXComboBox<Label> fieldAgencia;

    @FXML
    private JFXButton btnCancelar;

    @FXML
    private JFXRadioButton fieldTipoPessoaFisica;

    @FXML
    private JFXRadioButton fieldTipoPessoaJuridica;

    private static int num_Conta;
    private static int id_pessoa;

    public static int getNum_Conta() {
        return num_Conta;
    }

    public static void setNum_Conta(int num_Conta) {
        AlterarConta_Controller.num_Conta = num_Conta;
    }

    public static int getId_pessoa() {
        return id_pessoa;
    }

    public static void setId_pessoa(int id_pessoa) {
        AlterarConta_Controller.id_pessoa = id_pessoa;
    }

    @FXML
    void btnSalvar(ActionEvent event) {
        if(verificaFields()) {
                Conta conta = new Conta();
                Pessoa pessoa = new Pessoa();
                Cidade cidade = new Cidade();
                TipoConta tipoConta = new TipoConta();
                Agencia agencia = new Agencia();

                conta.setNumeroConta(getNum_Conta());
                pessoa.setId_pessoa(getId_pessoa());

                pessoa.setNome(fieldNome.getText());
                pessoa.setTelefone(fieldTelefone.getText());
                pessoa.setCpf_cnpj(fieldCpf.getText());
                pessoa.setRg(fieldRg.getText());

                if(fieldTipoPessoaFisica.isSelected()){
                    pessoa.setFisica_ou_juridica('f');
                }else{
                    pessoa.setFisica_ou_juridica('j');
                }
                conta.getTipoConta().setId_tipoConta(Integer.parseInt(String.valueOf(fieldTipoDeConta.getValue().getText().charAt(0))));
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

                 PessoaDao pessoaDao;
                 ContaDao contaDao;
                 pessoaDao = new PessoaDao();
                if (pessoaDao.atualizarPessoa(pessoa)) {
                     contaDao = new ContaDao();
                    if (contaDao.atualizarConta(conta)) {
                        Main.changeScreen("mensageBox","Salvo com sucesso!");
                        Utility.closeWindow(btnSalvar);

                    } else {
                        System.out.println("Algum problema no cadastro!");
                    }
                } else {
                    System.out.println("Algum problema no registro de pessoa!");
                }
        }
    }

    @FXML
    void onCancelar(ActionEvent event) {
        Utility.closeWindow(btnCancelar);
    }

    public void carregaFields(Conta conta){
        fieldNome.setText(conta.getPessoa().getNome());
        fieldTelefone.setText(conta.getPessoa().getTelefone());
        try {
            fieldData.setValue(LocalDate.parse(conta.getPessoa().getNascimento()));
        }catch (Exception e){
            System.out.println("Set datapicker: "+e);
        }
        fieldCpf.setText(conta.getPessoa().getCpf_cnpj());
        fieldCpf.setDisable(true);
        fieldRg.setText(conta.getPessoa().getRg());
        fieldRg.setDisable(true);
        fieldLogradouro.setText(conta.getPessoa().getRua_endereco());
        fieldNumeroCasa.setText(String.valueOf(conta.getPessoa().getNumero_endereco()));
        fieldCep.setText(conta.getPessoa().getCep_endereco());

        Label lb = new Label();
        lb.setText(String.valueOf(conta.getPessoa().getCidade().getId_cidade()));
        fieldCidade.setValue(lb);

        fieldSenha.setText(String.valueOf(conta.getSenha()));
        fieldTipoDeConta.setValue(new Label(String.valueOf(conta.getTipoConta().getId_tipoConta())));

        fieldAgencia.setValue(new Label(String.valueOf(conta.getAgencia().getId_Agencia())));
        fieldAgencia.setDisable(true);

        if ((conta.getPessoa().getFisica_ou_juridica() == 'f')) {
            fieldTipoPessoaFisica.setSelected(true);
            fieldTipoPessoaJuridica.setDisable(true);
        } else {
            fieldTipoPessoaJuridica.setSelected(true);
            fieldTipoPessoaFisica.setDisable(false);
        }





    }
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
        if(fieldSenha.getText().equals("") || Utility.Regex(fieldSenha.getText())){
            resultado = false;
            mensagem += "(Senha) ";
        }
        if(fieldData.getValue() == null){
            resultado = false;
            mensagem += "(Data Nascimento)";
        }
        if(fieldCidade.getValue() == null){
            resultado = false;
            mensagem += "(Estado)";
        }
        if(fieldTipoDeConta.getValue() == null){
            resultado = false;
            mensagem += "(Tipo de conta)";
        }

        mensagem+=", esses campos precisam ser prenchidos corretamente!!!";
        if(!resultado) {
            Main.changeScreen("mensageBox",mensagem);
        }
        return resultado;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        Main.addOnChangeScreenListeners(new Main.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object userData) {
                if(newScreen.equals("alteraContaForm")){
                    // acho que da pra refatorar essa inicialização colocando super no construtor...
                    Conta conta = new Conta();
                    Pessoa pessoa = new Pessoa();
                    Cidade cidade = new Cidade();
                    TipoConta tipoConta = new TipoConta();
                    Agencia agencia = new Agencia();
                    pessoa.setCidade(cidade);

                    conta.setPessoa(pessoa);
                    conta.setTipoConta(tipoConta);
                    conta.setAgencia(agencia);


                    conta.setNumeroConta(Integer.parseInt(String.valueOf(userData)));

                    ContaDao contaDao = new ContaDao();
                    contaDao.getIdPessoaWhereNumConta(conta);

                    PessoaDao pessoaDao = new PessoaDao();
                    contaDao.carregarModel(conta);
                    pessoaDao.carregarModel(conta.getPessoa());

                    //carregando id's GLOBAL
                    setNum_Conta(conta.getNumeroConta());
                    setId_pessoa(conta.getPessoa().getId_pessoa());

                    carregaFields(conta);
                }
            }
        });

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
