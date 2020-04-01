package DAO;

import Conection.Conexao;
import Model.*;
import SistemaBancario.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContaDao {
    private Conexao conexao;

    public ContaDao() {
        this.conexao = new Conexao();
    }

    public boolean inserirConta(Conta conta) {
        boolean result = false;
        PreparedStatement stm = null;

        //Pega o id da pessoa inserida pelo cpf,simulando uma unica sessao.
        getIdPessoa(conta.getPessoa());
        try {
            String sql = "INSERT INTO Conta (numero_conta,senha, id_TipoConta, id_pessoa, id_Agencia) " +
                    "VALUES(?,?,?,?,?);";
            stm = conexao.getConexao().prepareStatement(sql);
            stm.setInt(1,conta.getNumeroConta());
            stm.setString(2,conta.getSenha());
            stm.setInt(3,conta.getTipoConta().getId_tipoConta());
            stm.setInt(4,conta.getPessoa().getId_pessoa());
            stm.setInt(5,conta.getAgencia().getId_Agencia());

            stm.execute();
            conexao.getConexao().commit();
            result = true;
        } catch (SQLException e) {
            Main.changeScreen("mensageBox", "Error insertFuncionario " + e);
        } finally {
            conexao.finallyConnectionClose(stm);
        }
        return result;
    }
    public void getIdPessoa(Pessoa pessoa) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT id_Pessoa FROM PESSOA WHERE cpf_cnpj=?";
            stm = conexao.getConexao().prepareStatement(sql);
            stm.setString(1, pessoa.getCpf_cnpj());
            rs = stm.executeQuery();
            rs.next();

            pessoa.setId_pessoa(rs.getInt("id_Pessoa"));

        } catch (Exception e) {
            System.out.println("Error getIdPessoa " + e);

        } finally {
            conexao.finallyConnectionClose(stm, rs);
        }
    }
    public List<TableContas> consultaAndViewTable(String valorPesquisa) {
        List<TableContas> listaContas = new ArrayList<>();
        String sql;
        PreparedStatement stm = null;
        ResultSet rs = null;
        // ex sql : "SELECT id_funcionario,nome_Pessoa,cargo FROM FUNCIONARIO INNER JOIN PESSOA ON Pessoa.id_Pessoa = Funcionario.id_pessoa INNER JOIN CARGO ON Cargo.id_cargo = Funcionario.id_cargo;";
        if ("".equals(valorPesquisa) || valorPesquisa == null) {
            sql = "SELECT numero_conta,nome_Pessoa,contaAtive, id_TipoConta FROM CONTA " +
                    "INNER JOIN PESSOA ON Pessoa.id_Pessoa = Conta.id_pessoa ";
        } else {
            sql = "SELECT numero_conta, nome_Pessoa, contaAtive, id_TipoConta FROM CONTA " +
                    "INNER JOIN PESSOA ON Pessoa.id_Pessoa = Conta.id_pessoa " +
                    "WHERE PESSOA.nome_Pessoa LIKE '%" + valorPesquisa + "%';";
        }

        try {
            stm = conexao.getConexao().prepareStatement(sql);
            rs = stm.executeQuery();
            //verifica se tem algum resultado
            while (rs.next()) {
                TableContas t = new TableContas(String.valueOf(rs.getInt("numero_conta")), rs.getString("nome_Pessoa"), String.valueOf(rs.getBoolean("contaAtive")),
                        String.valueOf(rs.getInt("id_TipoConta")) );
                listaContas.add(t);
            }
        } catch (Exception e) {
            System.out.println("Result set problem: " + e);
        } finally {
            conexao.finallyConnectionClose(stm, rs);
        }
        return listaContas;
    }

    public boolean getIdPessoaWhereNumConta(Conta conta) {
        boolean result = false;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT id_pessoa FROM CONTA WHERE numero_conta = ?;";
            stm = conexao.getConexao().prepareStatement(sql);
            stm.setInt(1, conta.getNumeroConta());
            rs = stm.executeQuery();
            conta.getPessoa().setId_pessoa(rs.getInt("id_pessoa"));
            result = true;
        } catch (Exception e) {
            System.out.println("Erro getIdPessoaWhereNumConta: " + e);
        } finally {
            conexao.finallyConnectionClose(stm, rs);
        }
        return result;
    }
    public void carregarModel(Conta conta) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM CONTA WHERE numero_conta = ? AND id_TipoConta = ?;";
            stm = conexao.getConexao().prepareStatement(sql);
            stm.setInt(1, conta.getNumeroConta());
            stm.setInt(2,conta.getTipoConta().getId_tipoConta());
            rs = stm.executeQuery();

            conta.setSenha(rs.getString("senha"));
            conta.getAgencia().setId_Agencia(rs.getInt("id_Agencia"));
            conta.getTipoConta().setId_tipoConta(rs.getInt("id_TipoConta"));
            conta.setContaAtiva(rs.getBoolean("contaAtive"));
            conta.setSaldo(rs.getDouble("saldo"));

        } catch (SQLException e) {
            System.out.println("Erro em carregar Campos Conta: " + e);
        } finally {
            conexao.finallyConnectionClose(stm, rs);
        }
    }
    public boolean atualizarConta(Conta conta) {
        boolean result = false;
        PreparedStatement stm = null;
        try {
            String sql = "UPDATE CONTA SET senha = ?, id_TipoConta = ? WHERE numero_conta = ?;";
            stm = conexao.getConexao().prepareStatement(sql);
            stm.setString(1,conta.getSenha());
            stm.setInt(2,conta.getTipoConta().getId_tipoConta());
            //id where
            stm.setInt(3, conta.getNumeroConta());
            stm.execute();
            conexao.getConexao().commit();
            result = true;

        } catch (Exception e) {
            System.out.println("Error Altera conta " + e);
        } finally {
            conexao.finallyConnectionClose(stm);
        }
        return result;
    }
    public boolean updateAtiveConta(Conta conta){
        boolean result = false;
        PreparedStatement stm = null;
        try {
            String sql = "UPDATE CONTA SET contaAtive = ? WHERE numero_conta = ?;";
            stm = conexao.getConexao().prepareStatement(sql);
            stm.setBoolean(1,conta.getContaAtiva());
            //id where
            stm.setInt(2, conta.getNumeroConta());
            stm.execute();
            conexao.getConexao().commit();
            result = true;

        } catch (Exception e) {
            System.out.println("Error Altera Status conta " + e);
        } finally {
            conexao.finallyConnectionClose(stm);
        }
        return result;
    }
    public boolean atualizarSaldoConta(Conta conta){
        boolean result = false;
        PreparedStatement stm = null;
        try {
            String sql = "UPDATE CONTA SET saldo = ? WHERE numero_conta = ? AND id_TipoConta = ?;";
            stm = conexao.getConexao().prepareStatement(sql);
            stm.setDouble(1,conta.getSaldo());

            //id where and type
            stm.setInt(2, conta.getNumeroConta());
            stm.setInt(3,conta.getTipoConta().getId_tipoConta());
            stm.execute();
            conexao.getConexao().commit();
            result = true;

        } catch (Exception e) {
            System.out.println("Error Altera Status conta " + e);
        } finally {
            conexao.finallyConnectionClose(stm);
        }
        return result;
    }

    public Boolean validarContaEcarregarModel(Conta conta) {
        boolean result = false;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT id_conta, saldo, nome_Pessoa, cpf_cnpj, contaAtive FROM CONTA" +
                " INNER JOIN PESSOA ON Pessoa.id_Pessoa = CONTA.id_pessoa" +
                " WHERE CONTA.numero_conta =? AND CONTA.senha =? AND CONTA.id_Agencia = ? AND CONTA.id_TipoConta = ?;";
        try {
            stm = conexao.getConexao().prepareStatement(sql);
            stm.setInt(1, conta.getNumeroConta());
            stm.setString(2, conta.getSenha());
            stm.setInt(3,conta.getAgencia().getId_Agencia());
            stm.setInt(4,conta.getTipoConta().getId_tipoConta());
            rs = stm.executeQuery();
            rs.next();
            if (rs.getRow() != 0) {
                conta.setId_conta(rs.getInt("id_conta"));
                conta.setSaldo(rs.getDouble("saldo"));
                conta.setContaAtiva(rs.getBoolean("contaAtive"));
                conta.getPessoa().setNome(rs.getString("nome_Pessoa"));
                conta.getPessoa().setCpf_cnpj(rs.getString("cpf_cnpj"));
                conta.setSenha("");
                result = true;
            }
        } catch (Exception e) {
            System.out.println("Algum problema na validação: " + e);
        } finally {
            conexao.finallyConnectionClose(stm, rs);
        }

        return result;
    }
    public boolean verifyExistConta(String numConta,String id_tipoConta) {
        boolean result = true;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT numero_conta FROM CONTA WHERE CONTA.numero_conta =? AND CONTA.id_TipoConta = ?;";
        try {
            stm = conexao.getConexao().prepareStatement(sql);
            stm.setInt(1, Integer.parseInt(numConta));
            stm.setInt(2, Integer.parseInt(id_tipoConta));
            rs = stm.executeQuery();
            rs.next();
            if (rs.getRow() == 0) {
                result = false;
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Conta não existe: " + e);
        } finally {
            conexao.finallyConnectionClose(stm, rs);
        }

        return result;
    }
}
