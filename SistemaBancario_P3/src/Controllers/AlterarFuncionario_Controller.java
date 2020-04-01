package Controllers;

import DAO.CargoDao;
import DAO.CidadeDao;
import DAO.FuncionarioDao;
import DAO.PessoaDao;
import Model.Cargo;
import Model.Cidade;
import Model.Funcionario;
import Model.Pessoa;
import SistemaBancario.Main;
import Util.Utility;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class AlterarFuncionario_Controller implements Initializable {
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
    private JFXTextField fieldUsuario;

    @FXML
    private JFXPasswordField fieldSenha;

    @FXML
    private JFXComboBox<Label> fieldCargo;

    @FXML
    private JFXTextField fieldSalario;

    @FXML
    private JFXButton btnSalvar;

    @FXML
    private JFXComboBox<Label> fieldCidade;

    @FXML
    private JFXButton btnCancelar;

    private static int id_funcionario;
    private static int id_pessoa;

    public static int getId_funcionario() {
        return id_funcionario;
    }

    public static void setId_funcionario(int id_funcionario) {
        AlterarFuncionario_Controller.id_funcionario = id_funcionario;
    }

    public static int getId_pessoa() {
        return id_pessoa;
    }

    public static void setId_pessoa(int id_pessoa) {
        AlterarFuncionario_Controller.id_pessoa = id_pessoa;
    }

    @FXML
    void btnSalvar(ActionEvent event) {
        if(verificaFields()) {
                 Funcionario funcionario = new Funcionario();
                 Pessoa pessoa = new Pessoa();
                 Cidade cidade = new Cidade();
                 Cargo cargo = new Cargo();
                 funcionario.setId_funcionario(getId_funcionario());
                 pessoa.setId_pessoa(getId_pessoa());

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
                 PessoaDao pessoaDao;
                 FuncionarioDao funcionarioDao;
                 pessoaDao = new PessoaDao();
                if (pessoaDao.atualizarPessoa(pessoa)) {
                     funcionarioDao = new FuncionarioDao();
                    if (funcionarioDao.atualizarFuncionario(funcionario)) {
                        System.out.println("Salvo com sucesso!");
                        Utility.closeWindow(btnSalvar);

                    } else {
                        System.out.println("Algum problema no cadastro!");

                    }
                } else {
                    System.out.println("Algum problema no registro de pessoa!");

                }
        }else{
            Main.changeScreen("mensageBox","Complete todos os campos e tente novamente!");
        }
    }

    @FXML
    void onCancelar(ActionEvent event) {
        Utility.closeWindow(btnCancelar);
    }
    public void carregaFields(Funcionario funcionario){
        fieldNome.setText(funcionario.getPessoa().getNome());
        fieldTelefone.setText(funcionario.getPessoa().getTelefone());
        try {
            fieldData.setValue(LocalDate.parse(funcionario.getPessoa().getNascimento()));
        }catch (Exception e){
            System.out.println("Set datapicker: "+e);
        }
        fieldCpf.setText(funcionario.getPessoa().getCpf_cnpj());
        fieldCpf.setDisable(true);
        fieldRg.setText(funcionario.getPessoa().getRg());
        fieldRg.setDisable(true);
        fieldLogradouro.setText(funcionario.getPessoa().getRua_endereco());
        fieldNumeroCasa.setText(String.valueOf(funcionario.getPessoa().getNumero_endereco()));
        fieldCep.setText(funcionario.getPessoa().getCep_endereco());

        Label lb = new Label();
        lb.setText(String.valueOf(funcionario.getPessoa().getCidade().getId_cidade()));
        fieldCidade.setValue(lb);

        fieldUsuario.setText(funcionario.getUsuario());
        fieldUsuario.setDisable(true);

        fieldSenha.setText(funcionario.getSenha());
        fieldSalario.setText(String.valueOf(funcionario.getSalario()));
        fieldCargo.setValue(new Label(String.valueOf(funcionario.getCargo().getId_cargo())));


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

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        Main.addOnChangeScreenListeners(new Main.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object userData) {
                if(newScreen.equals("alteraFuncionarioForm")){
                    Funcionario funcionario = new Funcionario();
                    Pessoa pessoa = new Pessoa();
                    Cidade cidade = new Cidade();
                    Cargo cargo = new Cargo();
                    pessoa.setCidade(cidade);
                    funcionario.setPessoa(pessoa);
                    funcionario.setCargo(cargo);

                    funcionario.setId_funcionario(Integer.parseInt(String.valueOf(userData)));

                    FuncionarioDao funcionarioDao = new FuncionarioDao();
                    funcionarioDao.getIdPessoaWhereIdFuncionario(funcionario);
                    PessoaDao pessoaDao = new PessoaDao();
                    funcionarioDao.carregarModel(funcionario);
                    pessoaDao.carregarModel(funcionario.getPessoa());

                    //carregando id's GLOBAL
                    setId_funcionario(funcionario.getId_funcionario());
                    setId_pessoa(funcionario.getPessoa().getId_pessoa());

                    carregaFields(funcionario);
                }
            }
        });

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
