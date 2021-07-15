/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.*;
import classes.*;
import java.text.ParseException;
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
        java.util.StringTokenizer generosTokens = new java.util.StringTokenizer(nomeGenero,",");
        for(int i=0; generosTokens.hasMoreTokens(); i++){
            Genero genero = new Genero(generoDAO.findMaxId()+1,livro,generosTokens.nextToken());
            this.getLivroTableModel().getGeneros().add(genero);
            generoDAO.criar(genero);
        }
        
        AutorDAO autorDAO = new AutorDAO();
        java.util.StringTokenizer autoresTokens = new java.util.StringTokenizer(nomeAutor,",");
        for(int i=0; autoresTokens.hasMoreTokens(); i++){
            Autor autor = new Autor(autorDAO.findMaxId()+1,livro,autoresTokens.nextToken());
            this.getLivroTableModel().getAutores().add(autor);
            autorDAO.criar(autor);
        }
        
        EstoqueDAO estoqueDAO = new EstoqueDAO();
        Estoque estoque = new Estoque(estoqueDAO.findMaxId()+1,livroQuantidade,livro);
        this.getLivroTableModel().getEstoques().add(estoque);
        estoqueDAO.criar(estoque);
        
        this.getLivroTableModel().carregaTabela();
    }
    
    public void mostrarDados() throws ParseException{
        EditoraDAO editoraDAO = new EditoraDAO();
        this.getLivroTableModel().setEditoras(editoraDAO.ler());
        
        LivroDAO livroDAO = new LivroDAO();
        this.getLivroTableModel().setLivros(livroDAO.ler(this.getLivroTableModel().getEditoras()));
        
        GeneroDAO generoDAO = new GeneroDAO();
        this.getLivroTableModel().setGeneros(generoDAO.ler(this.getLivroTableModel()));
        
        AutorDAO autorDAO = new AutorDAO();
        this.getLivroTableModel().setAutores(autorDAO.ler(this.getLivroTableModel()));
        
        EstoqueDAO estoqueDAO = new EstoqueDAO();
        this.getLivroTableModel().setEstoques(estoqueDAO.ler(this.getLivroTableModel()));
        
        this.getLivroTableModel().carregaTabela();
    }
    
    public void editarDados(int linha, String obra, int edicao, java.util.Date ano, int numPaginas, String idioma, String pais, String isbn, Double preco, String nomeEditora, String nomeGenero, String nomeAutor, int livroQuantidade){
        int codigoLinha = this.getLivroTableModel().getLivros().get(linha).getCodigo();
        
        //Editora
        this.getLivroTableModel().getEditoras().get(linha).setNome(nomeEditora);
        EditoraDAO editoraDAO = new EditoraDAO();
        editoraDAO.alterar(this.getLivroTableModel().getEditoras().get(linha) );
        
        //Livro
        this.getLivroTableModel().getLivros().get(linha).setObra(obra);
        this.getLivroTableModel().getLivros().get(linha).setEdicao(edicao);
        this.getLivroTableModel().getLivros().get(linha).setAno(ano);
        this.getLivroTableModel().getLivros().get(linha).setNumPaginas(numPaginas);
        this.getLivroTableModel().getLivros().get(linha).setIdioma(idioma);
        this.getLivroTableModel().getLivros().get(linha).setPais(pais);
        this.getLivroTableModel().getLivros().get(linha).setIsbn(isbn);
        this.getLivroTableModel().getLivros().get(linha).setPreco(preco);
        LivroDAO livroDAO = new LivroDAO();
        livroDAO.alterar(this.getLivroTableModel().getLivros().get(linha));
        
        //Genero
        java.util.StringTokenizer generosTokens = new java.util.StringTokenizer(nomeGenero,",");
        //int numGeneros = generosTokens.countTokens();
        GeneroDAO generoDAO = new GeneroDAO();
        this.getLivroTableModel().getGeneros().forEach(genero ->{
            if(genero.getLivro().getCodigo() == codigoLinha){
                genero.setNome(generosTokens.nextToken());
                generoDAO.alterar(genero);
            }
        });
        
        //Autor
        java.util.StringTokenizer autoresTokens = new java.util.StringTokenizer(nomeAutor,",");
        //int numGeneros = generosTokens.countTokens();
        AutorDAO autorDAO = new AutorDAO();
        this.getLivroTableModel().getAutores().forEach(autor ->{
            if(autor.getLivro().getCodigo() == codigoLinha){
                autor.setNome(autoresTokens.nextToken());
                autorDAO.alterar(autor);
            }
        });
        
        //Estoque
        this.getLivroTableModel().getEstoques().get(linha).setQuantidade(livroQuantidade);
        EstoqueDAO estoqueDAO = new EstoqueDAO();
        estoqueDAO.alterar(this.getLivroTableModel().getEstoques().get(linha) );
        
        this.getLivroTableModel().atualizaTabela(linha, linha);
    }
    
    public void excluirDados(int linha){
        int codigoLinha = this.getLivroTableModel().getLivros().get(linha).getCodigo();
        
        //Exclui dado de estoque
        EstoqueDAO estoqueDAO = new EstoqueDAO();
        estoqueDAO.excluir(codigoLinha);
        this.getLivroTableModel().getEstoques().remove(linha);
        
        //Exclui dado(s) de autor
        AutorDAO autorDAO = new AutorDAO();
        autorDAO.excluir(codigoLinha);
	for(int i=0; i<this.getLivroTableModel().getAutores().size(); i++){
            if(this.getLivroTableModel().getAutores().get(i).getLivro().getCodigo() == codigoLinha){
                this.getLivroTableModel().getAutores().remove(i);
            }
        }
        
        //Exclui dado(s) de genero
        GeneroDAO generoDAO = new GeneroDAO();
        generoDAO.excluir(codigoLinha);
	for(int i=0; i<this.getLivroTableModel().getGeneros().size(); i++){
            if(this.getLivroTableModel().getGeneros().get(i).getLivro().getCodigo() == codigoLinha){
                this.getLivroTableModel().getGeneros().remove(i);
            }
        }
        
        //Exclui dado de livro
        LivroDAO livroDAO = new LivroDAO();
        livroDAO.excluir(codigoLinha);
        this.getLivroTableModel().getLivros().remove(linha);
        
        //Exclui dado de editora
        EditoraDAO editoraDAO = new EditoraDAO();
        editoraDAO.excluir(codigoLinha);
        this.getLivroTableModel().getEditoras().remove(linha);
        
        //Exclui linha da view
        this.getLivroTableModel().apagarLinha(linha);
    }
    
}
