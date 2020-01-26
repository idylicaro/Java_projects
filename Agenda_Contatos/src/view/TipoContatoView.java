/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.TipoContatoControl;

/**
 *
 * @author Dracula Castle
 */
public class TipoContatoView extends FormPadrao{

    public TipoContatoView(String titulo) {
        setTitle(titulo);
        inicializarComponentes();
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
    }
    
    
}
