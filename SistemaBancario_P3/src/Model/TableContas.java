package Model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TableContas extends RecursiveTreeObject<TableContas> {
    public StringProperty numConta;
    public StringProperty titular;
    public StringProperty contaStatus;
    public StringProperty id_tipoconta;

    public TableContas(String numConta, String titular, String contaStatus, String id_tipoconta) {
        this.numConta = new SimpleStringProperty(numConta);
        this.titular = new SimpleStringProperty(titular);
        this.contaStatus = new SimpleStringProperty(contaStatus);
        this.id_tipoconta = new SimpleStringProperty(id_tipoconta);
    }
}
