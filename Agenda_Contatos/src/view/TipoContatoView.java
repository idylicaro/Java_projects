/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.TipoContatoControl;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dracula Castle
 */
public class TipoContatoView extends FormPadrao{

    public TipoContatoView(String titulo) {
        setTitle(titulo);
        inicializarComponentes();
        consultaView();
    }

    @Override
    public void inicializarComponentes() {
        //inserir Componentes aqui
    }

    // Instancia de Controle
    TipoContatoControl tcc = new TipoContatoControl();
    
    @Override
    public void salvarVisao() {
        //inserir Comandos
        tcc.salvarControle(jtfId.getText(),jtfDescricao.getText());
        //consultaView();
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
        // isso aqui e para zera a quantidade de linhas , para na atualizaçao vir com o novo item ...
        modelo.setNumRows(0);
        tcc.consultarControle(jtfConsulta.getText(),modelo);

    }

    @Override
    public void atualizarFormulario() {
        jtfId.setText( (String) tabela.getValueAt(tabela.getSelectedRow(), 0).toString() );
        jtfDescricao.setText( (String) tabela.getValueAt(tabela.getSelectedRow(), 1).toString() );
    }

    @Override
    public void excluirView() {
        tcc.excluirControle(Integer.parseInt(jtfId.getText() ));
    }
    
    
}
