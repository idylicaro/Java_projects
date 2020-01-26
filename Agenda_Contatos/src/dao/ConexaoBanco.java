/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.DriverManager;

/**
 *
 * @author Dracula Castle
 */
public class ConexaoBanco {
    //seria metodo tradicional , porem devido a um erro de zona de fuso horario vamos mudar.
    //private static final String driveClass = "com.mysql.jdbc.Driver";
    //private static final String url = "jdbc:mysql://localhost:3306/ageConBd";
    private static final String driveClass = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/ageConBd?useTimezone=true&serverTimezone=UTC";
    private static final String usuario = "root";
    private static final String senha = "icaro1";
    
    public static Connection abreConexao(){
        Connection con = null;
        
        try {
            Class.forName(driveClass);
            con = DriverManager.getConnection(url,usuario,senha);
            System.out.println("Conexao Concluida!");
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com banco de dados: "+erro);
        }
        
        return con;
    }
}
