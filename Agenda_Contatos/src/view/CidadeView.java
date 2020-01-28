/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.CidadeControl;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dracula Castle
 */
public class CidadeView extends FormPadrao{
    
    JLabel jlUf;
    JLabel jlCep;
    
    JComboBox jcbUf;
    JTextField jtfCep;
    
    public CidadeView(String titulo) {
        setTitle(titulo);
        inicializarComponentes();
    }

    

    @Override
    public void inicializarComponentes() {
        //inserir Componentes aqui
        jlUf = new JLabel("UF");
        jlUf.setBounds(9, 60, 50, 25);
        jpnFormulario.add(jlUf);
        
        jcbUf = new JComboBox();
        jcbUf.setBounds(9, 80, 70, 25);
        jpnFormulario.add(jcbUf);
        
        jlCep = new JLabel("Cep");
        jlCep.setBounds(90, 60, 100, 25);
        jpnFormulario.add(jlCep);
        
        jtfCep = new JTextField();
        jtfCep.setBounds(90, 80, 150, 25);
        jpnFormulario.add(jtfCep);
    }

     // Instancia de Controle
    CidadeControl cc = new CidadeControl();
    
    @Override
    public void salvarVisao() {
        //inserir Comandos
        cc.salvarControle(jtfId.getText(),jtfDescricao.getText());
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
