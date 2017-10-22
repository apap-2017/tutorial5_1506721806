package com.example.tugas1.controller;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tugas1.model.KecamatanModel;
import com.example.tugas1.model.KeluargaModel;
import com.example.tugas1.model.KelurahanModel;
import com.example.tugas1.model.KotaModel;
import com.example.tugas1.model.PendudukModel;
import com.example.tugas1.service.KecamatanService;
import com.example.tugas1.service.KeluargaService;
import com.example.tugas1.service.KelurahanService;
import com.example.tugas1.service.KotaService;
import com.example.tugas1.service.PendudukService;

@Controller
public class KeluargaController
{
	
	@Autowired
	KeluargaService keluargaDAO;
	
	@Autowired
	KelurahanService kelurahanDAO;
	
	@Autowired
	KecamatanService kecamatanDAO;
	
	@Autowired
	KotaService kotaDAO;


    @RequestMapping("/keluarga")
    public String viewKeluarga (
            @RequestParam(value = "nomor_kk", required = false) String nomor_kk, Model model)
    {
    	KeluargaModel keluarga = keluargaDAO.selectKeluargaByNKK (nomor_kk);

        if (keluarga != null) {
        	KelurahanModel kelurahan = kelurahanDAO.selectKelurahan(keluarga.getId_kelurahan());
        	KecamatanModel kecamatan = kecamatanDAO.selectKecamatan(kelurahan.getId_kecamatan());
        	KotaModel kota = kotaDAO.selectKota(kecamatan.getId_kota());
        	
            model.addAttribute ("keluarga", keluarga);
            model.addAttribute ("kelurahan", kelurahan);
            model.addAttribute ("kecamatan", kecamatan);
            model.addAttribute("kota", kota);
            return "viewKeluarga";
        } else {
            model.addAttribute ("nomor_kk", nomor_kk);
            return "not-found";
        } 
    }
    
    @RequestMapping(value = "/keluarga/tambah", method = RequestMethod.POST)
    public String tambahPenduduk (Model model,
    		@RequestParam(value = "alamat", required = false) String alamat,
            @RequestParam(value = "rt", required = false) String rt,
            @RequestParam(value = "rw", required = false) String rw,
            @RequestParam(value = "nama_kota", required = false) String nama_kota,
            @RequestParam(value = "nama_kecamatan", required = false) String nama_kecamatan,
            @RequestParam(value = "nama_kelurahan", required = false) String nama_kelurahan)
		    
    {
    	int is_tidak_berlaku = 0;
    
    	String kodeKecamatan = kecamatanDAO.selectKodeKecamatanByNama(nama_kecamatan).substring(0,6);
    	
    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    	Date date = new Date();
    	String tanggalKeluaran = dateFormat.format(date);
    	
    	String[] tglKeluaran = tanggalKeluaran.split("/");
    	String tahun = tglKeluaran[0];
    	String bulan = tglKeluaran[1];
    	String tgl = tglKeluaran[2];
    	
    	
    	String tanggalNKK = tgl+bulan+tahun.substring(2, 4);
    	
    	String nkk1 = kodeKecamatan + tanggalNKK;
    	String countNKK ="";
    	String countNKK1 = "" + (keluargaDAO.countNKK(nkk1+"%")+1);
    	
    	
    	if(countNKK1.length() == 1) {
    		countNKK = "000" + countNKK1;
    	}else if(countNKK1.length() == 2) {
    		countNKK = "00" + countNKK1;
    	}else {
    		countNKK = "0" + countNKK1;
    	}
    	//System.out.println(nama_kelurahan + " HAHA " + kelurahanDAO.selectKodeKelurahanByNama(nama_kelurahan));
    	int idKelurahan = Integer.parseInt(kelurahanDAO.selectKodeKelurahanByNama(nama_kelurahan));
    	String nkkFix = nkk1 + countNKK; 
    	
    	KeluargaModel keluarga = new KeluargaModel (0, nkkFix, alamat, rt, rw, idKelurahan, 
    			is_tidak_berlaku, null);
        keluargaDAO.tambahKeluarga (keluarga);
        model.addAttribute("nkk", nkkFix);
        model.addAttribute("flag_tambah_keluarga", true);
        return "form-add-keluarga";
        
    }
    
    @RequestMapping("/keluarga/tambah")
    public String tambah(){
    	
    	return "form-add-keluarga";
    }
    
    @RequestMapping("/keluarga/ubah/{nkk}")
    public String updateKeluarga(Model model, @PathVariable(value = "nkk") String nkk){
    	KeluargaModel keluarga = keluargaDAO.selectKeluargaByNKK(nkk);
    	if(keluarga != null){
    		model.addAttribute("keluarga", keluarga);
    		KelurahanModel kelurahan = kelurahanDAO.selectKelurahan(keluarga.getId_kelurahan());
    		KecamatanModel kecamatan = kecamatanDAO.selectKecamatan(kelurahan.getId_kecamatan());
    		KotaModel kota = kotaDAO.selectKota(kecamatan.getId_kota());
    		model.addAttribute("kota", kota);
    		model.addAttribute("kelurahan", kelurahan);
    		model.addAttribute("kecamatan", kecamatan);
    		return "form-update-keluarga";
    	}
		return"not-found"; 
    }
    
    @RequestMapping(value = "/keluarga/ubah/{nkk}" , method = RequestMethod.POST)
    public String updateHasil(Model model, KeluargaModel keluarga, @RequestParam(value = "nama_kecamatan", required = false) String nama_kecamatan,
    		@RequestParam(value = "nama_kelurahan", required = false) String nama_kelurahan, @PathVariable(value = "nkk") String nkk){
    	//PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
    	
    	String kodeKecamatan = kecamatanDAO.selectKodeKecamatanByNama(nama_kecamatan).substring(0,6);
    	
    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    	Date date = new Date();
    	String tanggalKeluaran = dateFormat.format(date);
    	
    	String[] tglKeluaran = tanggalKeluaran.split("/");
    	String tahun = tglKeluaran[0];
    	String bulan = tglKeluaran[1];
    	String tgl = tglKeluaran[2];
    	
    	
    	String tanggalNKK = tgl+bulan+tahun.substring(2, 4);
    	
    	String nkk1 = kodeKecamatan + tanggalNKK;
    	String countNKK ="";
    	String countNKK1 = "" + (keluargaDAO.countNKK(nkk1+"%")+1);
    	
    	
    	if(countNKK1.length() == 1) {
    		countNKK = "000" + countNKK1;
    	}else if(countNKK1.length() == 2) {
    		countNKK = "00" + countNKK1;
    	}else {
    		countNKK = "0" + countNKK1;
    	}
    	
    	int idKelurahan = Integer.parseInt(kelurahanDAO.selectKodeKelurahanByNama(nama_kelurahan));
    	String nkkFix = nkk1 + countNKK;
		model.addAttribute("nomor_kk", keluarga.getNomor_kk()); 
    	
    	KeluargaModel keluargaUpdate = new KeluargaModel (keluarga.getId(), nkkFix, keluarga.getAlamat(), keluarga.getRt(), keluarga.getRw(), idKelurahan, 
    			keluarga.getIs_tidak_berlaku(), null);
        keluargaDAO.updateKeluarga (keluargaUpdate);
        model.addAttribute("nkk", nkkFix);
        model.addAttribute("flag_ubah_keluarga", true);
        model.addAttribute("keluarga",keluargaUpdate);
        KelurahanModel kelurahan = kelurahanDAO.selectKelurahan(idKelurahan);
		KecamatanModel kecamatan = kecamatanDAO.selectKecamatan(kelurahan.getId_kecamatan());
		KotaModel kota = kotaDAO.selectKota(kecamatan.getId_kota());
		model.addAttribute("kota", kota);
		model.addAttribute("kelurahan", kelurahan);
		model.addAttribute("kecamatan", kecamatan);
        return "form-update-keluarga";
            	
    }
    
    
}
