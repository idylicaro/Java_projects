/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.BairroControl;
import javax.swing.JComboBox;
import javax.swing.JLabel;

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
    
}
