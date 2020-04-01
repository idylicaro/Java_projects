package DAO;

import Conection.Conexao;
import Model.Conta;
import Model.ContaOperacoes;
import SistemaBancario.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OperacaoBancariaDao {
    private Conexao conexao;

    public OperacaoBancariaDao() {
        this.conexao = new Conexao();
    }

    public boolean inserirOperaçãolInLog(Conta remetente, Conta destinatario, String datatime, int tipo, double valor) {
        boolean result = false;
        PreparedStatement stm = null;

        if(tipo == 1){
            //transferencia
            String sql = "INSERT INTO CONTAOPERACOES (data_Operacao, valor , id_Operacao, numero_Conta_Remetente, " +
                    "numero_Conta_Destinatario, id_tipoConta) " +
                    "VALUES(?,?,?,?,?,?);";
            try{
                stm = conexao.getConexao().prepareStatement(sql);
                stm.setString(1, datatime);
                stm.setDouble(2, valor);
                stm.setInt(3, tipo);
                stm.setInt(4, remetente.getNumeroConta());
                stm.setInt(5, destinatario.getNumeroConta());
                stm.setInt(6,remetente.getTipoConta().getId_tipoConta());
                stm.execute();
                conexao.getConexao().commit();
                result = true;

            }catch (SQLException e) {
            Main.changeScreen("mensageBox", "Error logOperações " + e);
        } finally {
            conexao.finallyConnectionClose(stm);
        }

        }else if (tipo == 2 || tipo == 3){
            //saque
            String sql = "INSERT INTO CONTAOPERACOES (data_Operacao, valor , id_Operacao, numero_Conta_Remetente, id_tipoConta) " +
                    "VALUES(?,?,?,?,?);";
            try{
                stm = conexao.getConexao().prepareStatement(sql);
                stm.setString(1, datatime);
                stm.setDouble(2, valor);
                stm.setInt(3, tipo);
                stm.setInt(4, remetente.getNumeroConta());
                stm.setInt(5,remetente.getTipoConta().getId_tipoConta());
                stm.execute();
                conexao.getConexao().commit();
                result = true;

            }catch (SQLException e) {
                Main.changeScreen("mensageBox", "Error logOperações " + e);
            } finally {
                conexao.finallyConnectionClose(stm);
            }
        }

        return result;
    }
    public List<ContaOperacoes> carregaExtratoConta(Conta conta){
        List<ContaOperacoes> listContaOperacoes = new ArrayList<>();
        String sql;
        PreparedStatement stm = null;
        ResultSet rs = null;

            sql = "SELECT id_ContaOperacoes, data_Operacao, valor, nome_tipo_Operacao, numero_Conta_Remetente, " +
                    "numero_Conta_Destinatario, id_tipoConta " +
                    "FROM CONTAOPERACOES " +
                    "INNER JOIN OPERACAO ON OPERACAO.id_Operacao = CONTAOPERACOES.id_Operacao " +
                    "WHERE CONTAOPERACOES.numero_Conta_Remetente = ? AND CONTAOPERACOES.id_tipoConta = ?;";
        try {
            stm = conexao.getConexao().prepareStatement(sql);
            stm.setInt(1,conta.getNumeroConta());
            stm.setInt(2,conta.getTipoConta().getId_tipoConta());
            rs = stm.executeQuery();
            //verifica se tem algum resultado
            while (rs.next()) {
                ContaOperacoes contaOperacoes = new ContaOperacoes();
                contaOperacoes.getOperacao().setNome_tipo_Operacao(rs.getString("nome_tipo_Operacao"));
                contaOperacoes.setData_Operacao(rs.getString("data_Operacao"));
                contaOperacoes.setValor(rs.getDouble("valor"));
                contaOperacoes.setId_contaOperacoes(rs.getInt("id_ContaOperacoes"));
                contaOperacoes.getContaRemetente().setNumeroConta(rs.getInt("numero_Conta_Remetente"));
                contaOperacoes.getContaDestinatario().setNumeroConta(rs.getInt("numero_Conta_Destinatario"));
                listContaOperacoes.add(contaOperacoes);
            }
        } catch (Exception e) {
            System.out.println("Result set problem: " + e);
        } finally {
            conexao.finallyConnectionClose(stm, rs);
        }


        return listContaOperacoes;
    }
}
