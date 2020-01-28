
package view;

import controller.ContatoControl;
import javax.swing.table.DefaultTableModel;

/**
 * @author Dracula Castle
 */

public class ContatoView extends FormPadrao {

    public ContatoView(String titulo) {
        setTitle(titulo);
        inicializarComponentes();
    }

    

    @Override
    public void inicializarComponentes() {
        //inserir Componentes aqui
    }

     // Instancia de Controle
    ContatoControl ctc = new ContatoControl();
    
    @Override
    public void salvarVisao() {
        //inserir Comandos
        ctc.salvarControle(jtfId.getText(),jtfDescricao.getText());
    }

    @Override
    public void criarTabela() {
        tabela = utilTabela.criarTabela(
                jpnConsulta,
                new Object[] {60,600},
                new Object[] {"centro","esquerda"},
                new Object[] {"ID","Descrição"}
                );
        modelo = (DefaultTableModel) tabela.getModel();
    }

    @Override
    public void consultaView() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizarFormulario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluirView() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
