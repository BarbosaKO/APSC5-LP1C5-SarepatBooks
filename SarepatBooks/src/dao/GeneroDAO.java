/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.Genero;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author gabri
 */
public class GeneroDAO {
    public int findMaxId() {
        Connection conn = Conexao.open();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "select MAX(codigo) from genero";
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
    public void criar(Genero genero){
        Connection conn = Conexao.open();
        PreparedStatement pstm = null;
        String sql = "insert into genero values (?,?,?)";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, genero.getCodigo());
            pstm.setInt(2, genero.getLivro().getCodigo());
            pstm.setString(3, genero.getNome());

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.close(conn, pstm, null);
        }
        
    }
    //************************** LER *******************************************
    public List<Genero> ler(LivroTableModel livroTableModel){
        List<Genero> generos = new ArrayList<>();
        Connection conn = Conexao.open();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "select * from genero order by codigo asc";
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            for(int i=0; rs.next(); i++){
                generos.add(new Genero(rs.getInt("codigo"), livroTableModel.getLivroByID(rs.getInt("codigo_livro")), rs.getString("nome")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.close(conn, pstm, rs);
        }
        return generos;
        
    }
    
    //************************** ALTERAR *******************************************
    public void alterar(Genero genero){
        Connection conn = Conexao.open();
        PreparedStatement pstm = null;
        String sql = "update genero set nome=? where codigo=?";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, genero.getNome());
            pstm.setInt(2, genero.getCodigo());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.close(conn, pstm, null);
        }
    }
    
    //************************** EXCLUIR *******************************************
    public void excluir(int codigo){
        Connection conn = Conexao.open();
        PreparedStatement pstm = null;
        String sql = "delete from genero where codigo_livro = (?)";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, codigo);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.close(conn, pstm, null);
        }
    }
}
