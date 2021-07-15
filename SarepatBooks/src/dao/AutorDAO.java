/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.Autor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author gabri
 */
public class AutorDAO {
    public int findMaxId() {
        Connection conn = Conexao.open();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "select MAX(codigo) from autor";
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
    public void criar(Autor autor){
        Connection conn = Conexao.open();
        PreparedStatement pstm = null;
        String sql = "insert into autor values (?,?,?)";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, autor.getCodigo());
            pstm.setInt(2, autor.getLivro().getCodigo());
            pstm.setString(3, autor.getNome());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.close(conn, pstm, null);
        }

    }
    //************************** LER *******************************************
    public List<Autor> ler(LivroTableModel livroTableModel){
        List<Autor> autores = new ArrayList<>();
        Connection conn = Conexao.open();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "select * from autor order by codigo asc";
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            for(int i=0; rs.next(); i++){
                autores.add(new Autor(rs.getInt("codigo"), livroTableModel.getLivroByID(rs.getInt("codigo_livro")), rs.getString("nome")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.close(conn, pstm, rs);
        }
        return autores;
    }
    
    //************************** ALTERAR *******************************************
    public void alterar(Autor autor){
      Connection conn = Conexao.open();
        PreparedStatement pstm = null;
        String sql = "update autor set nome=? where codigo=?";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, autor.getNome());
            pstm.setInt(2, autor.getCodigo());
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
        String sql = "delete from autor where codigo_livro = (?)";
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
