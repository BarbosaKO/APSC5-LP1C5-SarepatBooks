/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import classes.*;

/**
 *
 * @author gabri
 */
public class LivroTableModel extends AbstractTableModel{
    
    private List<Editora>editoras = new ArrayList<>();
    private List<Livro>livros = new ArrayList<>();
    private List<Genero>generos = new ArrayList<>();
    private List<Autor>autores = new ArrayList<>();
    private List<Estoque>estoques = new ArrayList<>();
    private final String[] colunas = {"ID","Obra","Editora","ISBN"};

    public List<Editora> getEditoras() {
        return editoras;
    }

    public void setEditoras(List<Editora> editoras) {
        this.editoras = editoras;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    public List<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public List<Estoque> getEstoques() {
        return estoques;
    }

    public void setEstoques(List<Estoque> estoques) {
        this.estoques = estoques;
    }
    
    public Livro getLivroByID(int id){
        for(int i=0; i<this.livros.size(); i++){
            if(this.livros.get(i).getCodigo() == id){
                return this.livros.get(i);
            }
        }
        System.out.println("ID nao encontrado!");
        return null;
    }
    
    public String getAllGenero(int id){
        String generoTxt = "";
        int aux = 0;
        for(int i=0; i<this.generos.size(); i++){
            if(this.generos.get(i).getLivro().getCodigo() == id){
                if(aux == 0){
                    generoTxt += this.generos.get(i).getNome();
                    aux++;
                }else{
                    generoTxt += ","+this.generos.get(i).getNome();
                }
            }
        }
        if(generoTxt.compareTo("") == 0){
            System.out.println("Generos nao encontrados!");
            return null;
        }else{
            return generoTxt;
        }
    }
    
    public String getAllAutor(int id){
        String autorTxt = "";
        int aux = 0;
        for(int i=0; i<this.autores.size(); i++){
            if(this.autores.get(i).getLivro().getCodigo() == id){
                if(aux == 0){
                    autorTxt += this.autores.get(i).getNome();
                    aux++;
                }else{
                    autorTxt += ","+this.autores.get(i).getNome();
                }
            }
        }
        if(autorTxt.compareTo("") == 0){
            System.out.println("Autores nao encontrados!");
            return null;
        }else{
            return autorTxt;
        }
    }
    
    public void carregaTabela(){
        this.fireTableDataChanged();
    }
    
    public void atualizaTabela(int a, int b){
        this.fireTableRowsUpdated(a, b);
    }
    
    public void apagarLinha(int linha){
        this.fireTableRowsDeleted(linha, linha);
    }
    
    @Override
    public String getColumnName(int column){
        return colunas[column];
    }
    
    @Override
    public int getRowCount() {
        
        return livros.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch(coluna){
            case 0:
                return livros.get(linha).getCodigo();
            case 1:
                return livros.get(linha).getObra();
            case 2:
                return editoras.get(linha).getNome();
            case 3:
                return livros.get(linha).getIsbn();
        }
        return null;
    }
    
}
