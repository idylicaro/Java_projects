/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.TipoContatoDao;
import interfaces.InterfaceControle;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.TipoContatoModel;

/**
 *
 * @author Dracula Castle
 */
public class TipoContatoControl implements InterfaceControle{
    // Instancia
    TipoContatoModel tcm = new TipoContatoModel();
    TipoContatoDao tcd = new TipoContatoDao();
    
    @Override
    public void salvarControle(Object... valor) {
        if("".equals(valor[0])){
            tcm.setId(0);
        }else{
            tcm.setId(Integer.parseInt(valor[0].toString() ));
        }
        
        tcm.setDescricao(valor[1].toString());
        
        System.out.println("tcm "+tcm.getDescricao());
        
        // Enviar as informação para o DAO
        tcd.salvarDao(tcm);
    }

    @Override
    public void excluirControle(int id) {
        tcd.excluirDao(id);
    }

    @Override
    public void carregarComboBox() {
    }

    @Override
    public void consultarControle(Object... valor) {
        try {
            tcd.consultarDao(valor);
        } catch (SQLException ex) {
            Logger.getLogger(TipoContatoControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
