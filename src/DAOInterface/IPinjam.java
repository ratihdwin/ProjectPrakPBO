/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOInterface;

import java.util.List;
import model.Pinjam;

/**
 *
 * @author ACER
 */
public interface IPinjam {
    public void simpan(Pinjam a);//menyimpan data
    public void edit(Pinjam a);//edit data
    public void hapus(Pinjam a);//hapus data
    public List<Pinjam> getAllId(String a);//cari data
    public List<Pinjam> getAll(); //membaca data
}
