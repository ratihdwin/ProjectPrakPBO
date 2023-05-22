/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAOInterface.IPinjam;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import koneksi.Koneksi;
import model.Pinjam;
/**
 *
 * @author ACER
 */
public class DAOPinjam implements IPinjam{

    Koneksi koneksi = new Koneksi();
    @Override
    public void simpan(Pinjam a) {
        if ((a.getId_anggota().equals(""))||(a.getJudul_buku().equals(""))
                ||(a.getTanggal_pinjam().equals(""))) {
            JOptionPane.showMessageDialog(null,"Isi semua data");
        }else{
            try {
                String sql = "INSERT INTO peminjaman VALUES('"+a.getId_anggota()+"','"
                        + a.getJudul_buku()+"','"
                        + a.getTanggal_pinjam()+"','"
                        + a.getTanggal_kembali()+"','"
                        + a.getDenda()+"');";
                koneksi.statement = koneksi.koneksi.createStatement();
                koneksi.statement.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Berhasil Upload Data");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"TERJADI MASSALAH"+e.getMessage());
            }
        }
    }

    @Override
    public List<Pinjam> getAll() {
        List<Pinjam>  listPinjam = null;
        try {
            String sql = "SELECT * FROM peminjaman";
            listPinjam = new ArrayList<Pinjam>();
            koneksi.statement = koneksi.koneksi.createStatement();
            ResultSet resultSet = koneksi.statement.executeQuery(sql);
            while(resultSet.next()){
                Pinjam pinjam = new Pinjam();
                pinjam.setId_anggota(resultSet.getString("id_anggota"));
                pinjam.setJudul_buku(resultSet.getString("judul_buku"));
                pinjam.setTanggal_pinjam(resultSet.getString("tanggal_pinjam"));
                pinjam.setTanggal_kembali(resultSet.getString("tanggal_kembali"));
                pinjam.setDenda(resultSet.getString("denda"));
                listPinjam.add(pinjam);
            }
        } catch (SQLException e) {
        }
        return listPinjam;
    }

    @Override
    public void edit(Pinjam a) {
       
            try {
                String sql = "UPDATE peminjaman SET id_anggota='"+a.getId_anggota()+"', "
                        + "judul_buku = '"+a.getJudul_buku()+"', "
                        + "tanggal_pinjam = '"+a.getTanggal_pinjam()+"', "
                        + "tanggal_kembali = '"+a.getTanggal_kembali()+"', "
                        + "denda = '"+a.getDenda()+"' WHERE id_anggota= '"+a.getId_anggota()+"';";
                koneksi.statement = koneksi.koneksi.createStatement();
                koneksi.statement.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Berhasil Ubah Data");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"TERJADI MASSALAH"+e.getMessage());
            }
        
    }

    @Override
    public void hapus(Pinjam a) {
        if (a.getId_anggota().equals("")) {
            JOptionPane.showMessageDialog(null,"KLIK DATA DI TABEL YANG ADA AKAN DI HAPUS");
        }else{
            try {
                String sql = "DELETE FROM peminjaman WHERE id_anggota= '"+a.getId_anggota()+"';";
                koneksi.statement = koneksi.koneksi.createStatement();
                koneksi.statement.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Berhasil Hapus Data");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"TERJADI MASSALAH"+e.getMessage());
            }
        }
    }



    @Override
    public List<Pinjam> getAllId(String a) {
         List<Pinjam>  listPinjam = null;
         int hasil = 0;
            try {
                String sql = "SELECT * FROM peminjaman WHERE id_anggota='"+a+"';";
                listPinjam = new ArrayList<Pinjam>();
                koneksi.statement = koneksi.koneksi.createStatement();
                ResultSet resultSet = koneksi.statement.executeQuery(sql);
                while (resultSet.next()) {
                    Pinjam pinjam = new Pinjam();
                    pinjam.setId_anggota(resultSet.getString("id_anggota"));
                    pinjam.setJudul_buku(resultSet.getString("judul_buku"));
                    pinjam.setTanggal_pinjam(resultSet.getString("tanggal_pinjam"));
                    pinjam.setTanggal_kembali(resultSet.getString("tanggal_kembali"));
                    pinjam.setDenda(resultSet.getString("denda"));
                    listPinjam.add(pinjam);
                    hasil = 1;
                }
                if (hasil != 1) {
                    JOptionPane.showMessageDialog(null, "Data Tidak Ada");
                }
            } catch (SQLException e) {
                 JOptionPane.showMessageDialog(null, "Data Tidak Ada");
            }
            return listPinjam;
    }
    
}
