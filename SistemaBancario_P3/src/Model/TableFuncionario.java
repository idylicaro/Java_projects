package Model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class TableFuncionario extends RecursiveTreeObject<TableFuncionario> {
    public StringProperty id_funcionario;
    public StringProperty nome;
    public StringProperty cargo;

    public TableFuncionario(String id_funcionario, String nome, String cargo) {
        this.id_funcionario = new SimpleStringProperty(id_funcionario);
        this.nome = new SimpleStringProperty(nome);
        this.cargo = new SimpleStringProperty(cargo);
    }
}
