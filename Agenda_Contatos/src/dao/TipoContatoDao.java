/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import interfaces.InterfaceDao;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import model.TipoContatoModel;

/**
 *
 * @author Dracula Castle
 */
public class TipoContatoDao implements InterfaceDao{
    
    String sql;
    PreparedStatement stm;

    @Override
    public void salvarDao(Object... valor) {
        
        TipoContatoModel tcm = (TipoContatoModel) valor[0];
        //Descobrir se é uma inclusão ou uma alteração
        if(tcm.getId() == 0){
        //INCLUSAO:
        sql = "INSERT INTO tiposdecontato (descricao_tipoContato) VALUES (?)";
        }else{
        //ALTERAÇÃO
        sql = "UPDATE tiposdecontato SET descricao_tipoContato=? WHERE id_tipoContato=?";
        }
        
        try {
            //Preparando e manipulando os dados
            stm = ConexaoBanco.abreConexao().prepareStatement(sql);
            
            stm.setString(1,tcm.getDescricao());
            
            if(tcm.getId()>0){
             stm.setInt(2,tcm.getId());   
            }
            
            stm.execute();
            
            stm.close();
            
            JOptionPane.showMessageDialog(null,"Salvo!");
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null,"Error: "+erro);
        }
        
    }

    @Override
    public void excluirDao(int id) {
    }

    @Override
    public void consultarDao(Object... valor) throws SQLException {
    }

    @Override
    public void carregarComboBoxDao(JComboBox itens) throws SQLException {
    }
    
}
