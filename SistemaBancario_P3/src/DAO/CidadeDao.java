package DAO;

import Conection.Conexao;
import Model.Cidade;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CidadeDao {
    private Conexao conexao;

    public CidadeDao() {
        this.conexao = new Conexao();
    }
    public boolean insereCidade(Cidade cidade){
        PreparedStatement stm = null;
        boolean result = false;
        try {
            String sql ="INSERT INTO CIDADE (nome_cidade,id_estado) VALUES(?,?)";
            stm = conexao.getConexao().prepareStatement(sql);
            stm.setString(1, cidade.getNome());
            stm.setInt(2, cidade.getEstado().getId_estado());

            stm.execute();
            conexao.getConexao().commit();
            conexao.desconecta();
            result = true;

        }catch (Exception e){
            System.out.println(e);
        }finally {
            conexao.finallyConnectionClose(stm);
        }
        return result;
    }
    public List<Cidade> listarCidades(){
        List<Cidade> listaCidade = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql="SELECT * FROM Cidade";
            stm = conexao.getConexao().prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()){
                Cidade cidade = new Cidade();
                cidade.setId_cidade(rs.getInt("id_cidade"));
                cidade.setNome(rs.getString("nome_cidade"));
                listaCidade.add(cidade);
            }

        }catch (Exception e){
            System.out.println("Erro"+e);
        }finally {
            conexao.finallyConnectionClose(stm,rs);
        }
        return listaCidade;
    }
}
