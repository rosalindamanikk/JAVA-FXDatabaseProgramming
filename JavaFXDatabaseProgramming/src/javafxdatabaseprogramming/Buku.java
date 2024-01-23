/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxdatabaseprogramming;

/**
 *
 * @author Diles
 */
public class Buku {
    private String isbn;
    private String judul;
    private String pengarang;
    private String tahunTerbit;
    private String penerbit;
    
    /*Buku(String isbn, String judul, String pengarang, String tahunTerbit, String penerbit){
        this.isbn = isbn;
        this.judul = judul;
        this.pengarang = pengarang;
        this.tahunTerbit = tahunTerbit;
        this.penerbit = penerbit;
    }*/
    
    public String getISBN() {
        return isbn;
    }
    
    public void setISBN(String isbn) {
        this.isbn = isbn;
    }
    
    public String getJudul() {
        return judul;
    }
    public void setJudul(String judul) {
        this.judul = judul;
    }
    
    public String getPengarang() {
        return pengarang;
    }
    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }
    
    public String getTahunTerbit() {
        return tahunTerbit;
    }
    public void setTahunTerbit(String tahunTerbit) {
        this.tahunTerbit = tahunTerbit;
    }
    
    public String getPenerbit() {
        return penerbit;
    }
    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }
}
