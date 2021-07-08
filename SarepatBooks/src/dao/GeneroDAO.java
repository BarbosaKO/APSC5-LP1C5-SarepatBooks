/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.Genero;
import classes.Livro;
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
        java.util.StringTokenizer generos = new java.util.StringTokenizer(genero.getNome(),",");
        
        for(int count=genero.getCodigo(); generos.hasMoreTokens(); count++){
            Connection conn = Conexao.open();
            PreparedStatement pstm = null;
            String sql = "insert into genero values (?,?,?)";
            try {
                pstm = conn.prepareStatement(sql);
                pstm.setInt(1, count);
                pstm.setInt(2, genero.getLivro().getCodigo());
                pstm.setString(3, generos.nextToken().trim());

                pstm.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                Conexao.close(conn, pstm, null);
            }
        }
        
    }
    //************************** LER *******************************************
    public List<Genero> ler(List<Livro> livros){
        List<Genero> generos = new ArrayList<>();
        Connection conn = Conexao.open();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "select * from genero order by codigo asc";
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            for(int i=0; rs.next(); i++){
                generos.add(new Genero(rs.getInt("codigo"), livros.get(rs.getInt("codigo_livro")), rs.getString("nome")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.close(conn, pstm, rs);
        }
        return generos;
        
    }
    
    //************************** ALTERAR *******************************************
    public void alterar(){
        
    }
    
    //************************** EXCLUIR *******************************************
    public void excluir(){
        
    }
}
