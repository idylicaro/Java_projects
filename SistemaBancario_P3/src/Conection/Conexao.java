package Conection;

import java.sql.*;

public class Conexao {
    private static final String driver = "org.sqlite.JDBC";
    private static final String url = "jdbc:sqlite:DataBase/BankBD.db";
    private Connection conexao = null;

    public Conexao(){
        try {
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConexao(){
        if(conexao == null){
            try {
                conexao = DriverManager.getConnection(url);
                conexao.setAutoCommit(false);
            } catch (SQLException e) {
                System.out.println("Algum problema na conexao com o banco de dados "+e);
            }
        }
        return conexao;
    }

    public void desconecta(){
        try {
            conexao.close();
        } catch (SQLException e) {
            System.out.println("Algum problema ao fechar conexao com o banco de dados "+e);
        }
        conexao = null;
    }
    public void finallyConnectionClose(PreparedStatement stm, ResultSet rs) {
        desconecta();
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        if (stm != null) {
            try {
                stm.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void finallyConnectionClose(PreparedStatement stm) {
        finallyConnectionClose(stm, null);
    }
}
