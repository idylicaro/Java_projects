package DAO;

import Conection.Conexao;
import Model.Cargo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CargoDao {
    private Conexao conexao;

    public CargoDao() {
        this.conexao = new Conexao();
    }
    public List<Cargo> listarCargo(){
        List<Cargo> listaCargo = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql="SELECT * FROM CARGO";
            stm = conexao.getConexao().prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()){
                Cargo cargo = new Cargo();
                cargo.setId_cargo(rs.getInt("id_cargo"));
                cargo.setCargo(rs.getString("cargo"));
                listaCargo.add(cargo);
            }
        }catch (Exception e){
            System.out.println("Erro"+e);
        }finally {
            conexao.finallyConnectionClose(stm,rs);
        }
        return listaCargo;
    }
}

