
package view;

import controller.ContatoControl;

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
    
}
