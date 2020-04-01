package Model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class ContaTableLog {
    private final SimpleStringProperty operacao;
    private final SimpleStringProperty data;
    private final SimpleDoubleProperty valor;
    private final SimpleStringProperty ContaDestino;

    public String getOperacao() {
        return operacao.get();
    }

    public SimpleStringProperty operacaoProperty() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao.set(operacao);
    }

    public String getData() {
        return data.get();
    }

    public SimpleStringProperty dataProperty() {
        return data;
    }

    public void setData(String data) {
        this.data.set(data);
    }

    public double getValor() {
        return valor.get();
    }

    public SimpleDoubleProperty valorProperty() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor.set(valor);
    }

    public String getContaDestino() {
        return ContaDestino.get();
    }

    public SimpleStringProperty contaDestinoProperty() {
        return ContaDestino;
    }

    public void setContaDestino(String contaDestino) {
        this.ContaDestino.set(contaDestino);
    }

    public ContaTableLog(String operacao, String data, double valor, String contaDestino) {
        this.operacao = new SimpleStringProperty(operacao);
        this.data = new SimpleStringProperty(data);
        this.valor = new SimpleDoubleProperty(valor);
        this.ContaDestino = new SimpleStringProperty(contaDestino);
    }
}
