/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.TipoContatoDao;
import interfaces.InterfaceControle;
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
        //por um try catch nesse set id
        tcm.setId((int)valor[0]);
        tcm.setDescricao((String)valor[1]);
        
        
        // Enviar as informação para o DAO
        tcd.salvarDao(tcm);
    }

    @Override
    public void excluirControle(int id) {
    }

    @Override
    public void carregarComboBox() {
    }
    
}
