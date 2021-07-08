/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.Editora;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabri
 */
public class EditoraDAO {
    
    public int findMaxId() {
        Connection conn = Conexao.open();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "select MAX(codigo) from editora";
        int id = 0;
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.close(conn, pstm, rs);
        }
        return id;
    }
    
    //************************** CRIAR *******************************************
    public void criar(Editora editora){
        Connection conn = Conexao.open();
        PreparedStatement pstm = null;
        String sql = "insert into editora values (?,?)";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, editora.getCodigo());
            pstm.setString(2, editora.getNome());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.close(conn, pstm, null);
        }
    }
    //************************** LER *******************************************
    public List<Editora>ler(){
        List<Editora> editoras = new ArrayList<>();
        Connection conn = Conexao.open();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "select * from editora order by codigo asc";
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while(rs.next()){
                //Editora editora = new Editora(rs.getInt("codigo"),rs.getString("nome"));
                editoras.add(new Editora(rs.getInt("codigo"),rs.getString("nome")));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.close(conn, pstm, rs);
        }
        return editoras;
    }
    
    //************************** ALTERAR *******************************************
    public void alterar(Editora editora){
        Connection conn = Conexao.open();
        PreparedStatement pstm = null;
        String sql = "update editora set nome = (?) where codigo = (?)";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, editora.getNome());
            pstm.setInt(2, editora.getCodigo());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.close(conn, pstm, null);
        }
    }
    
    //************************** EXCLUIR *******************************************
    public void excluir(){
        
    }
}
