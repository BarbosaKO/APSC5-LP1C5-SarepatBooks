/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.Estoque;
import classes.Livro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Gabriel Barbosa
 */
public class EstoqueDAO {
    public int findMaxId() {
        Connection conn = Conexao.open();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "select MAX(codigo) from estoque";
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
    public void criar(Estoque estoque){
        Connection conn = Conexao.open();
        PreparedStatement pstm = null;
        String sql = "insert into estoque values (?,?,?)";
        try {
            pstm = conn.prepareStatement(sql);
            
            pstm.setInt(1, estoque.getCodigo());
            pstm.setInt(2, estoque.getQuantidade());
            pstm.setInt(3, estoque.getLivro().getCodigo());
            
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.close(conn, pstm, null);
        }
    }
    //************************** LER *******************************************
    public List<Estoque> ler(List<Livro> livros){
        List<Estoque> estoques = new ArrayList<>();
        Connection conn = Conexao.open();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "select * from estoque order by codigo asc";
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            for(int i=0; rs.next(); i++){
                estoques.add(new Estoque(rs.getInt("codigo"), rs.getInt("quantidade"), livros.get(rs.getInt("codigo_livro")) ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.close(conn, pstm, rs);
        }
        return estoques;
    }
    
    //************************** ALTERAR *******************************************
    public void alterar(){
        
    }
    
    //************************** EXCLUIR *******************************************
    public void excluir(){
        
    }
}
