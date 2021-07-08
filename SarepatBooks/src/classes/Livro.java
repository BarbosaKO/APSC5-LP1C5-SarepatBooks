/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author Gabriel Barbosa
 */
public class Livro {

    private int codigo;
    private String obra;
    private int edicao;
    private java.util.Date ano;
    private int numPaginas;
    private String idioma;
    private String pais;
    private String isbn;
    private Double preco;
    private Editora editora;
    
    public  Livro(int codigo, String obra, int edicao, java.util.Date ano, int numPaginas, String idioma, String pais, String isbn, Double preco, Editora editora){
        this.codigo = codigo;
        this.obra = obra;
        this.edicao = edicao;
        this.ano = ano;
        this.numPaginas = numPaginas;
        this.idioma = idioma;
        this.pais = pais;
        this.isbn = isbn;
        this.preco = preco;
        this.editora = editora;
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the obra
     */
    public String getObra() {
        return obra;
    }

    /**
     * @param obra the obra to set
     */
    public void setObra(String obra) {
        this.obra = obra;
    }

    /**
     * @return the edicao
     */
    public int getEdicao() {
        return edicao;
    }

    /**
     * @param edicao the edicao to set
     */
    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }

    /**
     * @return the ano
     */
    public java.util.Date getAno() {
        return ano;
    }

    /**
     * @param ano the ano to set
     */
    public void setAno(java.util.Date ano) {
        this.ano = ano;
    }

    /**
     * @return the numPaginas
     */
    public int getNumPaginas() {
        return numPaginas;
    }

    /**
     * @param numPaginas the numPaginas to set
     */
    public void setNumPaginas(int numPaginas) {
        this.numPaginas = numPaginas;
    }

    /**
     * @return the idioma
     */
    public String getIdioma() {
        return idioma;
    }

    /**
     * @param idioma the idioma to set
     */
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    /**
     * @return the pais
     */
    public String getPais() {
        return pais;
    }

    /**
     * @param pais the pais to set
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @param isbn the isbn to set
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * @return the preco
     */
    public Double getPreco() {
        return preco;
    }

    /**
     * @param preco the preco to set
     */
    public void setPreco(Double preco) {
        this.preco = preco;
    }
    /**
     * @return the editora
     */
    public Editora getEditora() {
        return editora;
    }

    /**
     * @param editora the editora to set
     */
    public void setEditora(Editora editora) {
        this.editora = editora;
    }
    
    @Override
    public String toString(){
        return getCodigo()+" | "+getObra()+" | "+getEdicao()+" | "+getAno()+" | "+getNumPaginas()+" | "+getIdioma()+" | "+getPais()+" | "+getIsbn()+" | "+getPreco()+" | "+getEditora().getCodigo();
    }
}
