/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import javax.swing.JComboBox;
import java.sql.SQLException;

/**
 *
 * @author Dracula Castle
 */
public interface InterfaceDao {
    public void salvarDao(Object... valor);
    public void excluirDao(int id);
    public void consultarDao(Object... valor) throws SQLException;
    public void carregarComboBoxDao(JComboBox itens) throws SQLException;
}
