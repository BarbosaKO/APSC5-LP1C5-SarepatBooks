/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.*;
import classes.*;
//import java.util.ArrayList;
//import java.util.List;
/**
 *
 * @author gabri
 */
public class EstoqueService {
    
    private final LivroTableModel tableModel = new LivroTableModel();
    
    public LivroTableModel getLivroTableModel(){
        return tableModel;
    }
    
    public void adicionarNovoLivro(String obra, int edicao, java.util.Date ano, int numPaginas, String idioma, String pais, String isbn, Double preco, String nomeEditora, String nomeGenero, String nomeAutor, int livroQuantidade){
        EditoraDAO editoraDAO = new EditoraDAO();
        Editora editora = new Editora(editoraDAO.findMaxId()+1, nomeEditora);
        this.getLivroTableModel().getEditoras().add(editora);
        editoraDAO.criar(editora);
        
        LivroDAO livroDAO = new LivroDAO();
        Livro livro = new Livro(livroDAO.findMaxId()+1,obra,edicao,ano,numPaginas,idioma,pais,isbn,preco,editora);
        this.getLivroTableModel().getLivros().add(livro);
        livroDAO.criar(livro);
        
        GeneroDAO generoDAO = new GeneroDAO();
        Genero genero = new Genero(generoDAO.findMaxId()+1,livro,nomeGenero);
        this.getLivroTableModel().getGeneros().add(genero);
        generoDAO.criar(genero);
        
        AutorDAO autorDAO = new AutorDAO();
        Autor autor = new Autor(autorDAO.findMaxId()+1,livro,nomeAutor);
        this.getLivroTableModel().getAutores().add(autor);
        autorDAO.criar(autor);
        
        EstoqueDAO estoqueDAO = new EstoqueDAO();
        Estoque estoque = new Estoque(estoqueDAO.findMaxId()+1,livroQuantidade,livro);
        this.getLivroTableModel().getEstoques().add(estoque);
        estoqueDAO.criar(estoque);
        
        this.getLivroTableModel().atualizaTabela();
    }
    
    public void mostrarDados(){
        EditoraDAO editoraDAO = new EditoraDAO();
        this.getLivroTableModel().setEditoras(editoraDAO.ler());
        
        LivroDAO livroDAO = new LivroDAO();
        this.getLivroTableModel().setLivros(livroDAO.ler(this.getLivroTableModel().getEditoras()));
        
        GeneroDAO generoDAO = new GeneroDAO();
        this.getLivroTableModel().setGeneros(generoDAO.ler(this.getLivroTableModel().getLivros()));
        
        AutorDAO autorDAO = new AutorDAO();
        this.getLivroTableModel().setAutores(autorDAO.ler(this.getLivroTableModel().getLivros()));
        
        EstoqueDAO estoqueDAO = new EstoqueDAO();
        this.getLivroTableModel().setEstoques(estoqueDAO.ler(this.getLivroTableModel().getLivros()));
        
        this.getLivroTableModel().atualizaTabela();
    }
    
}
