/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.Autor;
import classes.Livro;
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
        java.util.StringTokenizer autores = new java.util.StringTokenizer(autor.getNome(),",");
        
        for(int count=autor.getCodigo(); autores.hasMoreTokens(); count++){
            Connection conn = Conexao.open();
            PreparedStatement pstm = null;
            String sql = "insert into autor values (?,?,?)";
            try {
                pstm = conn.prepareStatement(sql);
                pstm.setInt(1, count);
                pstm.setInt(2, autor.getLivro().getCodigo());
                pstm.setString(3, autores.nextToken().trim());

                pstm.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                Conexao.close(conn, pstm, null);
            }
        }

    }
    //************************** LER *******************************************
    public List<Autor> ler(List<Livro> livros){
        List<Autor> autores = new ArrayList<>();
        Connection conn = Conexao.open();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "select * from autor order by codigo asc";
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            for(int i=0; rs.next(); i++){
                autores.add(new Autor(rs.getInt("codigo"), livros.get(rs.getInt("codigo_livro")), rs.getString("nome")));
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
        java.util.StringTokenizer autores = new java.util.StringTokenizer(autor.getNome(),",");
        
        for(int count=autor.getCodigo(); autores.hasMoreTokens(); count++){
            Connection conn = Conexao.open();
            PreparedStatement pstm = null;
            String sql = "update autor set livro = (?), nome = (?) where codigo = (?)";
            try {
                pstm = conn.prepareStatement(sql);
                pstm.setInt(1, count);
                pstm.setInt(2, autor.getLivro().getCodigo());
                pstm.setString(3, autores.nextToken().trim());

                pstm.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                Conexao.close(conn, pstm, null);
            }
        }
    }
    
    //************************** EXCLUIR *******************************************
    public void excluir(){
        
    }
}
