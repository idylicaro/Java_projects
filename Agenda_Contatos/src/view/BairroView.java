/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.BairroControl;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dracula Castle
 */
    

public class BairroView extends FormPadrao{
    
    JLabel jlBairro;
    JComboBox jcbBairro;
    
    public BairroView(String titulo) {
        setTitle(titulo);
        inicializarComponentes();
        
    }

    @Override
    public void inicializarComponentes() {
        jlBairro = new JLabel("Bairro");
        jlBairro.setBounds(9, 60, 50, 25);
        jpnFormulario.add(jlBairro);
        
        jcbBairro = new JComboBox();
        jcbBairro.setBounds(9, 80, 250, 25);
        jpnFormulario.add(jcbBairro);
    }

     // Instancia de Controle
    BairroControl bc = new BairroControl();
    
    @Override
    public void salvarVisao() {
        //inserir Comandos
        bc.salvarControle(jtfId.getText(),jtfDescricao.getText(),jcbBairro.getSelectedItem());
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
