/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.Livro;
import classes.Editora;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author gabri
 */
public class LivroDAO {
    public int findMaxId() {
        Connection conn = Conexao.open();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "select MAX(codigo) from livro";
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
    public void criar(Livro livro){
        Connection conn = Conexao.open();
        PreparedStatement pstm = null;
        String sql = "insert into livro values (?,?,?,?,?,?,?,?,?,?)";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, livro.getCodigo());
            pstm.setString(2, livro.getObra());
            pstm.setInt(3, livro.getEdicao());
            pstm.setDate(4, new java.sql.Date(livro.getAno().getTime()));
            pstm.setInt(5, livro.getNumPaginas());
            pstm.setString(6, livro.getIdioma());
            pstm.setString(7, livro.getPais());
            pstm.setString(8, livro.getIsbn());
            pstm.setDouble(9, livro.getPreco());
            pstm.setInt(10, livro.getEditora().getCodigo());
            
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.close(conn, pstm, null);
        }
    }
    //************************** LER *******************************************
    public List<Livro>ler(List<Editora> editoras){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        List<Livro> livros = new ArrayList<>();
        Connection conn = Conexao.open();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "select * from livro order by codigo asc";
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            for(int i=0; rs.next(); i++){
                livros.add(new Livro(rs.getInt("codigo"),rs.getString("obra"),rs.getInt("edicao"),rs.getDate("ano"),rs.getInt("numPaginas"),rs.getString("idioma"),rs.getString("pais"),rs.getString("isbn"),rs.getDouble("preco"),editoras.get(i) ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.close(conn, pstm, rs);
        }
        return livros;
    }
    
    //************************** ALTERAR *******************************************
    public void alterar(Livro livro){
        Connection conn = Conexao.open();
        PreparedStatement pstm = null;
        String sql = "update livro set obra = (?), edicao = (?), ano = (?), numPaginas = (?), idioma = (?),"
                + " pais = (?), isbn = (?), preco = (?), editora = (?) where codigo = (?)";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, livro.getObra());
            pstm.setInt(2, livro.getEdicao());
            pstm.setDate(3, new java.sql.Date(livro.getAno().getTime()));
            pstm.setInt(4, livro.getNumPaginas());
            pstm.setString(5, livro.getIdioma());
            pstm.setString(6, livro.getPais());
            pstm.setString(7, livro.getIsbn());
            pstm.setDouble(8, livro.getPreco());
            pstm.setInt(9, livro.getEditora().getCodigo());
            pstm.setInt(10, livro.getCodigo());
            
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
