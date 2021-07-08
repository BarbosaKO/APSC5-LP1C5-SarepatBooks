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
    
    public void atualizaTabela(){
        this.fireTableDataChanged();
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
