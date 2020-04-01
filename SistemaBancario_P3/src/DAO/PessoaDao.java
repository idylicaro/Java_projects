package DAO;

import Conection.Conexao;
import Model.Pessoa;
import SistemaBancario.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PessoaDao {
    private Conexao conexao;
    public PessoaDao(){
        this.conexao = null;
        this.conexao = new Conexao();
    }
    public void finallyConexaoClose(PreparedStatement stm, ResultSet rs) {
        conexao.desconecta();
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
    public void finallyConexaoClose(PreparedStatement stm) {
        conexao.desconecta();
        if (stm != null) {
            try {
                stm.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    public boolean inserirPessoa(Pessoa pessoa){
        boolean result = false;
        PreparedStatement stm = null;
        try{
            String sql = "INSERT INTO PESSOA (nome_Pessoa, telefone, nascimento, cpf_cnpj, fisica_ou_juridica, rg, rua_endereco, numero_endereco, cep_endereco,id_cidade) "
                       + "VALUES(?,?,?,?,?,?,?,?,?,?);";
            stm = conexao.getConexao().prepareStatement(sql);
            stm.setString(1,pessoa.getNome());
            stm.setString(2,pessoa.getTelefone());
            stm.setString(3,pessoa.getNascimento());
            stm.setString(4,pessoa.getCpf_cnpj());
            stm.setString(5, String.valueOf(pessoa.getFisica_ou_juridica()));
            stm.setString(6,pessoa.getRg());
            stm.setString(7,pessoa.getRua_endereco());
            stm.setInt(8,pessoa.getNumero_endereco());
            stm.setString(9,pessoa.getCep_endereco());
            stm.setInt(10,pessoa.getCidade().getId_cidade());

            stm.execute();
            conexao.getConexao().commit();
            result = true;
        }catch (SQLException e){
            Main.changeScreen("mensageBox","Erro ao adicionar Pessoa" + e);
            System.out.println("ERROR Isert Pessoa "+e);

        }finally {
            conexao.finallyConnectionClose(stm);
        }
        return result;
    }
    public boolean atualizarPessoa(Pessoa pessoa){
        boolean result = false;
        PreparedStatement stm = null;
        try {
            String sql = "UPDATE PESSOA SET nome_Pessoa = ?, telefone = ?," +
                    " nascimento = ?, cpf_cnpj = ?," +
                    " rg = ?, rua_endereco = ?," +
                    " numero_endereco =?, cep_endereco =?, id_cidade = ?, fisica_ou_juridica = ?" +
                    " WHERE id_Pessoa = ?;";
            stm = conexao.getConexao().prepareStatement(sql);
            stm.setString(1,pessoa.getNome());
            stm.setString(2,pessoa.getTelefone());
            stm.setString(3,pessoa.getNascimento());
            stm.setString(4,pessoa.getCpf_cnpj());
            stm.setString(5,pessoa.getRg());
            stm.setString(6,pessoa.getRua_endereco());
            stm.setInt(7,pessoa.getNumero_endereco());
            stm.setString(8,pessoa.getCep_endereco());
            stm.setInt(9,pessoa.getCidade().getId_cidade());
            stm.setString(10, String.valueOf(pessoa.getFisica_ou_juridica()));
            //id where
            stm.setInt(11,pessoa.getId_pessoa());

            stm.execute();
            conexao.getConexao().commit();

            result = true;

        }catch (Exception e){
            System.out.println("ERROR Altera Pessoa "+e);
        }finally {
            conexao.finallyConnectionClose(stm);
            //finallyConexaoClose(stm);
        }
        return result;
    }
    public void carregarModel(Pessoa pessoa){
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM PESSOA WHERE id_Pessoa = ?;";
            stm = conexao.getConexao().prepareStatement(sql);
            stm.setInt(1,pessoa.getId_pessoa());
            rs = stm.executeQuery();
            pessoa.setNome(rs.getString("nome_Pessoa"));
            pessoa.setTelefone(rs.getString("telefone"));
            pessoa.setNascimento(rs.getString("nascimento"));
            pessoa.setCpf_cnpj(rs.getString("cpf_cnpj"));
            pessoa.setFisica_ou_juridica(rs.getString("fisica_ou_juridica").charAt(0));
            pessoa.setRg(rs.getString("rg"));
            pessoa.setCep_endereco(rs.getString("cep_endereco"));
            pessoa.setRua_endereco(rs.getString("rua_endereco"));
            pessoa.setNumero_endereco(rs.getInt("numero_endereco"));
            pessoa.getCidade().setId_cidade(rs.getInt("id_cidade"));

        }catch (Exception e){
            System.out.println("Erro em carregar Campos Pessoa: "+e);
        }finally {
            conexao.finallyConnectionClose(stm,rs);
        }
    }
    public boolean verifyUniqueConditionTelefone(String telefone){
        boolean result = false;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT id_Pessoa FROM PESSOA WHERE PESSOA.telefone =?;";
        try {
            stm = conexao.getConexao().prepareStatement(sql);
            stm.setString(1,telefone);
            rs = stm.executeQuery();
            rs.next();
            if (rs.getRow() == 0){
                result = true;
            }
        }catch (Exception e){
            System.out.println("Algum problema no login: "+e);
        }finally {
            conexao.finallyConnectionClose(stm,rs);
        }

        return result;
    }
    public boolean verifyUniqueConditionCpfCnpj(String cpf_cnpj){
        boolean result = false;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT id_Pessoa FROM PESSOA WHERE PESSOA.cpf_cnpj =?;";
        try {
            stm = conexao.getConexao().prepareStatement(sql);
            stm.setString(1,cpf_cnpj);
            rs = stm.executeQuery();
            rs.next();
            if (rs.getRow() == 0){
                result = true;
            }
        }catch (Exception e){
            System.out.println("Algum problema no login: "+e);
        }finally {
            conexao.finallyConnectionClose(stm,rs);
        }

        return result;
    }
    public boolean verifyUniqueConditionRg(String rg){
        boolean result = false;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT id_Pessoa FROM PESSOA WHERE PESSOA.rg =?;";
        try {
            stm = conexao.getConexao().prepareStatement(sql);
            stm.setString(1,rg);
            rs = stm.executeQuery();
            rs.next();
            if (rs.getRow() == 0){
                result = true;
            }
        }catch (Exception e){
            System.out.println("Algum problema no login: "+e);
        }finally {
            conexao.finallyConnectionClose(stm,rs);
        }
        return result;
    }

}
