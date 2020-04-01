package DAO;


import Conection.Conexao;
import Model.Funcionario;
import Model.Pessoa;
import SistemaBancario.Main;
import Model.TableFuncionario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDao {
    private Conexao conexao;

    public FuncionarioDao() {
        this.conexao = new Conexao();
    }

    public boolean inserirFuncionario(Funcionario funcionario) {
        boolean result = false;
        PreparedStatement stm = null;

        //Pega o id da pessoa inserida pelo cpf,simulando uma unica sessao.
        getIdPessoa(funcionario.getPessoa());
        try {
            String sql = "INSERT INTO FUNCIONARIO (login, senha, salario, id_cargo,id_pessoa) "
                    + "VALUES(?,?,?,?,?)";
            stm = conexao.getConexao().prepareStatement(sql);
            stm.setString(1, funcionario.getUsuario());
            stm.setString(2, funcionario.getSenha());
            stm.setFloat(3, funcionario.getSalario());
            stm.setInt(4, funcionario.getCargo().getId_cargo());
            stm.setInt(5, funcionario.getPessoa().getId_pessoa());

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

    public boolean atualizarFuncionario(Funcionario funcionario) {
        boolean result = false;
        PreparedStatement stm = null;
        try {
            String sql = "UPDATE FUNCIONARIO SET login = ?, senha = ?, salario = ?, id_cargo = ? WHERE id_funcionario = ?;";
            stm = conexao.getConexao().prepareStatement(sql);
            stm.setString(1, funcionario.getUsuario());
            stm.setString(2, funcionario.getSenha());
            stm.setFloat(3, funcionario.getSalario());
            stm.setInt(4, funcionario.getCargo().getId_cargo());
            //id where
            stm.setInt(5, funcionario.getId_funcionario());
            stm.execute();
            conexao.getConexao().commit();
            result = true;

        } catch (Exception e) {
            System.out.println("ERROR Altera Funcionario " + e);
        } finally {
            conexao.finallyConnectionClose(stm);
        }
        return result;
    }

    public boolean excluirFuncionario(int id_funcionario) {
        boolean result = false;
        PreparedStatement stm = null;
        try {
            String sql = "DELETE FROM funcionario WHERE id_funcionario = ?;";
            stm = conexao.getConexao().prepareStatement(sql);
            //id where
            stm.setInt(1, id_funcionario);
            stm.execute();
            conexao.getConexao().commit();
            result = true;

        } catch (Exception e) {
            System.out.println("ERROR Excluir Funcionario " + e);
        } finally {
            conexao.finallyConnectionClose(stm);
        }
        return result;
    }

    public void carregarModel(Funcionario funcionario) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM FUNCIONARIO WHERE id_funcionario = ?;";
            stm = conexao.getConexao().prepareStatement(sql);
            stm.setInt(1, funcionario.getId_funcionario());
            rs = stm.executeQuery();
            funcionario.setUsuario(rs.getString("login"));
            funcionario.setSenha(rs.getString("senha"));
            funcionario.setSalario(rs.getFloat("salario"));
            funcionario.getCargo().setId_cargo(rs.getInt("id_cargo"));

        } catch (SQLException e) {
            System.out.println("Erro em carregar Campos Funcionario: " + e);
        } finally {
            conexao.finallyConnectionClose(stm, rs);
        }
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

    public List<TableFuncionario> consultaAndViewTable(String valorPesquisa) {
        List<TableFuncionario> listaFunc = new ArrayList<>();
        String sql;
        PreparedStatement stm = null;
        ResultSet rs = null;
        // ex sql : "SELECT id_funcionario,nome_Pessoa,cargo FROM FUNCIONARIO INNER JOIN PESSOA ON Pessoa.id_Pessoa = Funcionario.id_pessoa INNER JOIN CARGO ON Cargo.id_cargo = Funcionario.id_cargo;";
        if ("".equals(valorPesquisa) || valorPesquisa == null) {
            sql = "SELECT id_funcionario,nome_Pessoa,cargo FROM FUNCIONARIO " +
                    "INNER JOIN PESSOA ON Pessoa.id_Pessoa = Funcionario.id_pessoa " +
                    "INNER JOIN CARGO ON Cargo.id_cargo = Funcionario.id_cargo;";
        } else {
            sql = "SELECT id_funcionario,nome_Pessoa,cargo FROM FUNCIONARIO " +
                    "INNER JOIN PESSOA ON Pessoa.id_Pessoa = Funcionario.id_pessoa " +
                    "INNER JOIN CARGO ON Cargo.id_cargo = Funcionario.id_cargo " +
                    "WHERE PESSOA.nome_Pessoa LIKE '%" + valorPesquisa + "%';";
        }

        try {
            stm = conexao.getConexao().prepareStatement(sql);
            rs = stm.executeQuery();
            //verifica se tem algum resultado
            while (rs.next()) {
                TableFuncionario t = new TableFuncionario(String.valueOf(rs.getInt("id_funcionario")), rs.getString("nome_Pessoa"), rs.getString("cargo"));
                listaFunc.add(t);
            }
        } catch (Exception e) {
            System.out.println("Result set problem: " + e);
        } finally {
            conexao.finallyConnectionClose(stm, rs);
        }
        return listaFunc;
    }
    public Boolean getFuncionarioLogin(Funcionario funcionario) {
        boolean result = false;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT id_funcionario,login,id_cargo FROM FUNCIONARIO WHERE FUNCIONARIO.login =? AND FUNCIONARIO.senha =?;";
        try {
            stm = conexao.getConexao().prepareStatement(sql);
            stm.setString(1, funcionario.getUsuario());
            stm.setString(2, funcionario.getSenha());
            rs = stm.executeQuery();
            rs.next();
            if (rs.getRow() != 0) {
                funcionario.setId_funcionario(rs.getInt("id_funcionario"));
                funcionario.setUsuario(rs.getString("login"));
                funcionario.getCargo().setId_cargo(rs.getInt("id_cargo"));
                funcionario.setSenha("");
                result = true;
            }

        } catch (Exception e) {
            System.out.println("Algum problema no login: " + e);
        } finally {
            conexao.finallyConnectionClose(stm, rs);
        }

        return result;
    }

    public boolean verifyUniqueConditionUsuario(String usuario) {
        boolean result = false;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT id_funcionario FROM FUNCIONARIO WHERE FUNCIONARIO.login =?;";
        try {
            stm = conexao.getConexao().prepareStatement(sql);
            stm.setString(1, usuario);
            rs = stm.executeQuery();
            rs.next();
            if (rs.getRow() == 0) {
                result = true;
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Algum problema no login: " + e);
        } finally {
            conexao.finallyConnectionClose(stm, rs);
        }

        return result;
    }

    public boolean getIdPessoaWhereIdFuncionario(Funcionario funcionario) {
        boolean result = false;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT id_pessoa FROM FUNCIONARIO WHERE id_funcionario = ?;";
            stm = conexao.getConexao().prepareStatement(sql);
            stm.setInt(1, funcionario.getId_funcionario());
            rs = stm.executeQuery();
            funcionario.getPessoa().setId_pessoa(rs.getInt("id_pessoa"));
            result = true;
        } catch (Exception e) {
            System.out.println("Erro getIdPessoaWhereIdFuncionario: " + e);
        } finally {
            conexao.finallyConnectionClose(stm, rs);
        }
        return result;
    }
}
