package DAO;

import Conection.Conexao;
import Model.Agencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AgenciaDao {
    private Conexao conexao;
    public AgenciaDao(){
        this.conexao = new Conexao();
    }
    public List<Agencia> listarAgencias(){
        List<Agencia> listaAgencia = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql="SELECT * FROM AGENCIA;";
            stm = conexao.getConexao().prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()){
                Agencia agencia = new Agencia();
                agencia.setId_Agencia(rs.getInt("id_agencia"));
                agencia.setRua_endereco(rs.getString("rua_endereco"));
                agencia.setCep_endereco(rs.getString("cep_endereco"));
                agencia.setNumero_endereco(rs.getInt("numero_endereco"));
                listaAgencia.add(agencia);
            }

        }catch (Exception e){
            System.out.println("Erro Listar Agencia: "+e);
        }finally {
            conexao.finallyConnectionClose(stm,rs);
        }
        return listaAgencia;
    }
}
