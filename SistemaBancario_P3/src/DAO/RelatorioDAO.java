package DAO;

import Conection.Conexao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RelatorioDAO {
    private Conexao conexao;

    public RelatorioDAO(){
        this.conexao = new Conexao();
    }
    public double getSaldoAgencia(int agencia){
        double saldo = 0;
        ResultSet rs = null;
        PreparedStatement stm = null;

        try {
            String sql = "select sum(saldo) from Conta where Conta.id_Agencia = ?;";
            stm = conexao.getConexao().prepareStatement(sql);
            stm.setInt(1,agencia);

            rs = stm.executeQuery();
            saldo = rs.getDouble("sum(saldo)");

        }catch (Exception e){
            System.out.println("Problema no getSaldoAgencia:"+e);
        }finally {
            conexao.finallyConnectionClose(stm,rs);
        }
        return saldo;
    }
    public int getNumeroContaAgencia(int agencia){
        int numContas = 0;
        ResultSet rs = null;
        PreparedStatement stm = null;

        try {
            String sql = "SELECT COUNT(*) FROM Conta where Conta.id_Agencia = ?;";
            stm = conexao.getConexao().prepareStatement(sql);
            stm.setInt(1,agencia);

            rs = stm.executeQuery();
            numContas = rs.getInt("COUNT(*)");

        }catch (Exception e){
            System.out.println("Problema no getNumeroContaAgencia:"+e);
        }finally {
            conexao.finallyConnectionClose(stm,rs);
        }
        return numContas;
    }
}
