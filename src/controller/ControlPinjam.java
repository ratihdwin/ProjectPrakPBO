/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.DAOPinjam;
import DAOInterface.IPinjam;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import model.Pinjam;
import model.TabelModelPinjam;
import view.Peminjaman;

/**
 *
 * @author ACER
 */
public class ControlPinjam {
    Peminjaman jframePeminjaman;
    IPinjam iPinjam;
    List<Pinjam> lstPinjam;
    
    public ControlPinjam(Peminjaman jframePeminjaman){
        this.jframePeminjaman = jframePeminjaman;
        iPinjam = (IPinjam) new DAOPinjam();
    }
    
     public void isiTabel(){
        
        lstPinjam = iPinjam.getAll();
        TabelModelPinjam tabelPinjam = new TabelModelPinjam(lstPinjam);
        jframePeminjaman.getTabelData().setModel(tabelPinjam);
        
    }
     
    public void simpanTable(){
        Pinjam insert = new Pinjam();
        insert.setId_anggota(jframePeminjaman.getID().getText());
        insert.setJudul_buku(jframePeminjaman.getJudul().getText());
        //insert.setTanggal_pinjam(jframePeminjaman.getTanggalPinjam().getText());
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tanggal = String.valueOf(fm.format(jframePeminjaman.getTanggalPinjam().getDate()));
        insert.setTanggal_pinjam(tanggal);
//        String tanggal2 = String.valueOf(fm.format(jframePeminjaman.getTanggalKembali().getDate()));
//        insert.setTanggal_kembali(tanggal2);
//        String tanggal2 = jframePeminjaman.getTanggalKembali().getDate().toString();
//        insert.setTanggal_kembali(tanggal2);
        insert.setTanggal_kembali(jframePeminjaman.getTanggalKembali().getText());
        insert.setDenda(jframePeminjaman.getDenda().getText());
        iPinjam.simpan(insert);
    }
    
    public void editTable(){
        Pinjam update = new Pinjam();
        update.setId_anggota(jframePeminjaman.getID().getText());
        update.setJudul_buku(jframePeminjaman.getJudul().getText());
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tanggal = String.valueOf(fm.format(jframePeminjaman.getTanggalPinjam().getDate()));
        update.setTanggal_pinjam(tanggal);
//        String tanggal2 = String.valueOf(fm.format(jframePeminjaman.getTanggalKembali().getDate()));
//        update.setTanggal_kembali(tanggal2);
        //update.setTanggal_pinjam(jframePeminjaman.getTanggalPinjam().getText());
        update.setTanggal_kembali(jframePeminjaman.getTanggalKembali().getText());
        update.setDenda(jframePeminjaman.getDenda().getText());
        iPinjam.edit(update);
    }
    
    public void isiField(int row) throws ParseException{
        jframePeminjaman.getID().setText(lstPinjam.get(row).getId_anggota());
        jframePeminjaman.getJudul().setText(lstPinjam.get(row).getJudul_buku());
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tanggal = lstPinjam.get(row).getTanggal_pinjam();
        jframePeminjaman.getTanggalPinjam().setDate(fm.parse(tanggal));
//        String tanggal2 = lstPinjam.get(row).getTanggal_kembali();
//        jframePeminjaman.getTanggalKembali().setDate(fm.parse(tanggal2));   
        //jframePeminjaman.getTanggalPinjam().setText(lstPinjam.get(row).getTanggal_pinjam());
        jframePeminjaman.getTanggalKembali().setText(lstPinjam.get(row).getTanggal_kembali());
        jframePeminjaman.getDenda().setText(lstPinjam.get(row).getDenda());       
    }
    
    public void hapusTable(){
        Pinjam hapus = new Pinjam();
        hapus.setId_anggota(jframePeminjaman.getID().getText());
        hapus.setJudul_buku(jframePeminjaman.getJudul().getText());
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tanggal = String.valueOf(fm.format(jframePeminjaman.getTanggalPinjam().getDate()));
        hapus.setTanggal_pinjam(tanggal);
//        String tanggal2 = String.valueOf(fm.format(jframePeminjaman.getTanggalKembali().getDate()));
//        hapus.setTanggal_kembali(tanggal2);
        //hapus.setTanggal_pinjam(jframePeminjaman.getTanggalPinjam().getText());
        hapus.setTanggal_kembali(jframePeminjaman.getTanggalKembali().getText());
        hapus.setDenda(jframePeminjaman.getDenda().getText());
        iPinjam.hapus(hapus);
    }
    
    public void cariTable(){  
        
        lstPinjam = iPinjam.getAllId(jframePeminjaman.getID().getText());
        TabelModelPinjam tabelPinjam = new TabelModelPinjam(lstPinjam);
        jframePeminjaman.getTabelData().setModel(tabelPinjam);
    }
    
    public void resetIsiKolom(){
        jframePeminjaman.getID().setText("");
        jframePeminjaman.getJudul().setText("");
        jframePeminjaman.getTanggalPinjam().setDate(null);
        jframePeminjaman.getTanggalKembali().setText("");
        jframePeminjaman.getDenda().setText("");
    }
}
