package DAO;

import Conection.Conexao;
import Model.TipoConta;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TipoContaDao {
    private Conexao conexao;
    public TipoContaDao(){
        this.conexao = new Conexao();
    }

    public List<TipoConta> listarTiposConta(){
        List<TipoConta> listaTiposConta = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql="SELECT * FROM TipoConta";
            stm = conexao.getConexao().prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()){
                TipoConta tipoConta = new TipoConta();
                tipoConta.setId_tipoConta(rs.getInt("id_TipoConta"));
                tipoConta.setNomeTipo(rs.getString("tipo"));
                listaTiposConta.add(tipoConta);
            }

        }catch (Exception e){
            System.out.println("Erro"+e);
        }finally {
            conexao.finallyConnectionClose(stm,rs);
        }
        return listaTiposConta;
    }
}
