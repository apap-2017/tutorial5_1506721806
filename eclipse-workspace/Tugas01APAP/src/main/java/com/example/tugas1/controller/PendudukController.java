package com.example.tugas1.controller;


import java.text.DateFormat;
import java.text.ParseException;
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
public class PendudukController
{
	
	@Autowired
	PendudukService pendudukDAO;
	
	@Autowired
	KeluargaService keluargaDAO;
	
	@Autowired
	KecamatanService kecamatanDAO;
	
	@Autowired
	KelurahanService kelurahanDAO;
	
	@Autowired
	KotaService kotaDAO;

    @RequestMapping("/")
    public String index ()
    {
        return "index";
    }

    @RequestMapping("/penduduk")
    public String viewPenduduk (
            @RequestParam(value = "nik", required = false) String nik, Model model) throws ParseException
    {
    	PendudukModel penduduk = pendudukDAO.selectPenduduk (nik);

        if (penduduk != null) {
        	KeluargaModel keluarga = keluargaDAO.selectKeluarga(penduduk.getId_keluarga());
        	KelurahanModel kelurahan = kelurahanDAO.selectKelurahan(keluarga.getId_kelurahan());
        	KecamatanModel kecamatan = kecamatanDAO.selectKecamatan(kelurahan.getId_kecamatan());
        	KotaModel kota = kotaDAO.selectKota(kecamatan.getId_kota());
        	
            model.addAttribute ("penduduk", penduduk);
            model.addAttribute ("keluarga", keluarga);
            model.addAttribute ("kelurahan", kelurahan);
            model.addAttribute ("kecamatan", kecamatan);
            model.addAttribute("kota", kota);
            
            DateFormat startDate = new SimpleDateFormat("yyyy-mm-dd");
            
            DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
            Date tanggal_lahir1 = startDate.parse(penduduk.getTanggal_lahir());
            String tanggal_lahir = dateFormat.format(tanggal_lahir1);
            model.addAttribute("tanggal_lahir", tanggal_lahir);
            
            return "viewPenduduk";
        } else {
            model.addAttribute ("nik", nik);
            return "not-found";
        }
        
    }
    
    @RequestMapping(value = "/penduduk/tambah", method = RequestMethod.POST)
    public String tambahPenduduk (Model model,
    		@RequestParam(value = "nama", required = false) String nama,
            @RequestParam(value = "tempat_lahir", required = false) String tempat_lahir,
            @RequestParam(value = "tanggal_lahir", required = false) String tanggal_lahir,
            @RequestParam(value = "jenis_kelamin", required = false) int jenis_kelamin,
		    @RequestParam(value = "golongan_darah", required = false) String golongan_darah,
		    @RequestParam(value = "agama", required = false) String agama,
		    @RequestParam(value = "status_perkawinan", required = false) String status_perkawinan,
		    @RequestParam(value = "status_dalam_keluarga", required = false) String status_dalam_keluarga,
		    @RequestParam(value = "pekerjaan", required = false) String pekerjaan,
		    @RequestParam(value = "is_wni", required = false) int is_wni,
		    @RequestParam(value = "is_wafat", required = false) int is_wafat,
		    @RequestParam(value = "id_keluarga", required = false) int id_keluarga)

    {
    	String kodeKecamatan = kecamatanDAO.selectKodeKecamatan(id_keluarga).substring(0,6);
    	String[] tanggalLahir = tanggal_lahir.split("-");
    	
    	String tgl = tanggalLahir[2];
    	int tanggal = 0;
    	if(jenis_kelamin == 1) {
    		tanggal = Integer.parseInt(tgl) + 40;
    	}else if(jenis_kelamin ==0) {
    		tanggal = Integer.parseInt(tgl);
    	}
    	
    	String tanggalFix = "" + tanggal;
    	String bulan = tanggalLahir[1];
    	String tahun = tanggalLahir[0];
    	String tanggalNIK = tanggalFix+bulan+tahun.substring(2, 4);
    	
    	String nik1 = kodeKecamatan + tanggalNIK;
    	String countNIK ="";
    	String countNIK1 = "" + (pendudukDAO.countNIK("%"+nik1+"%" )+1);
    	
  
    	if(countNIK1.length() == 1) {
    		countNIK = "000" + countNIK1;
    	}else if(countNIK1.length() == 2) {
    		countNIK = "00" + countNIK1;
    	}else {
    		countNIK = "0" + countNIK1;
    	}
    	
    	
    	String nikFix = nik1 + countNIK; 
    	
    	PendudukModel penduduk = new PendudukModel ("", nikFix, nama, tempat_lahir, tanggal_lahir, jenis_kelamin,
        		is_wni, id_keluarga, agama, pekerjaan, status_perkawinan, status_dalam_keluarga,
        		golongan_darah, is_wafat);
        pendudukDAO.tambahPenduduk (penduduk);
        model.addAttribute("nik", nikFix);
        model.addAttribute("flag_tambah", true);
        return "form-add";
        
    }
    
    @RequestMapping("/penduduk/tambah")
    public String tambah(){
    	
    	return "form-add";
    }
    
    @RequestMapping("/penduduk/ubah/{nik}")
    public String updatePenduduk(Model model, @PathVariable(value = "nik") String nik){
    	PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
    	if(penduduk != null){
    		model.addAttribute("penduduk", penduduk);
    		return "form-update";
    	}
		return"not-found"; 
    }
    
    @RequestMapping(value = "/penduduk/ubah/{nik}" , method = RequestMethod.POST)
    public String updateHasil(Model model, PendudukModel penduduk){
    	//PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
    	
    	String kodeKecamatan = kecamatanDAO.selectKodeKecamatan(penduduk.getId_keluarga()).substring(0,6);
    	String[] tanggalLahir = penduduk.getTanggal_lahir().split("-");
    	
    	String tgl = tanggalLahir[2];
    	int tanggal = 0;
    	if(penduduk.getJenis_kelamin() == 1) {
    		tanggal = Integer.parseInt(tgl) + 40;
    	}else if(penduduk.getJenis_kelamin() == 0) {
    		tanggal = Integer.parseInt(tgl);
    	}
    	
    	String tanggalFix = "" + tanggal;
    	String bulan = tanggalLahir[1];
    	String tahun = tanggalLahir[0];
    	String tanggalNIK = tanggalFix+bulan+tahun.substring(2, 4);
    	
    	String nik1 = kodeKecamatan + tanggalNIK;
    	String countNIK ="";
    	String countNIK1 = "" + (pendudukDAO.countNIK("%"+nik1+"%")+1);
    	
    	
    	if(countNIK1.length() == 1) {
    		countNIK = "000" + countNIK1;
    	}else if(countNIK1.length() == 2) {
    		countNIK = "00" + countNIK1;
    	}else {
    		countNIK = "0" + countNIK1;
    	}
    	
    	
    	String nikFix = nik1 + countNIK; 
    	
    	PendudukModel pendudukUpdated = new PendudukModel (penduduk.getId(), nikFix, penduduk.getNama(), penduduk.getTempat_lahir(), penduduk.getTanggal_lahir(), penduduk.getJenis_kelamin(),
        		penduduk.getIs_wni(), penduduk.getId_keluarga(), penduduk.getAgama(), penduduk.getPekerjaan(), penduduk.getStatus_perkawinan(), penduduk.getStatus_dalam_keluarga(),
        		penduduk.getGolongan_darah(), penduduk.getIs_wafat());
        pendudukDAO.updatePenduduk (pendudukUpdated);
        model.addAttribute("nik", nikFix);
        
        penduduk = pendudukDAO.selectPenduduk(nikFix);
        model.addAttribute("flag_ubah", true);
        model.addAttribute("penduduk", penduduk);
        return "form-update";
        
        
    }
    
    
    @RequestMapping(value = "/penduduk", method = RequestMethod.POST)
    public String kematianPenduduk(@RequestParam(value = "nik", required = false) String nik, 
    		@RequestParam(value = "id_keluarga", required = false) String id_keluarga, Model model) {
  	
    	pendudukDAO.kematianPenduduk(nik);
    	
    	int pendudukHidup = pendudukDAO.cekKematianKeluarga(id_keluarga);
    	if(pendudukHidup < 1 ) {
    		keluargaDAO.kematianPenduduk(id_keluarga);
    	}
    	PendudukModel penduduk = pendudukDAO.selectPenduduk (nik);
    	KeluargaModel keluarga = keluargaDAO.selectKeluarga(penduduk.getId_keluarga());
    	KelurahanModel kelurahan = kelurahanDAO.selectKelurahan(keluarga.getId_kelurahan());
    	KecamatanModel kecamatan = kecamatanDAO.selectKecamatan(kelurahan.getId_kecamatan());
    	KotaModel kota = kotaDAO.selectKota(kecamatan.getId_kota());
    	model.addAttribute ("penduduk", penduduk);
        model.addAttribute ("keluarga", keluarga);
        model.addAttribute ("kelurahan", kelurahan);
        model.addAttribute ("kecamatan", kecamatan);
        model.addAttribute("kota", kota);
    	model.addAttribute("flag_mati", true);
    	model.addAttribute("nik", nik);
		return "viewPenduduk";
    	
    }
    
    @RequestMapping(value = "/penduduk/cari", method = RequestMethod.GET)
    public String cariPenduduk(@RequestParam(value = "kt", required = false) String kt, 
    		@RequestParam(value = "kc", required = false) String kc, 
    		@RequestParam(value = "kl", required = false) String kl,  Model model) {
    	if(kl != null) {
    		model.addAttribute("iskecamatan", false);
    		model.addAttribute("kl", kl);
    		List<PendudukModel> pendudukKelurahan = pendudukDAO.selectPendudukByIdKelurahan(Integer.parseInt(kl));
    		model.addAttribute("pendudukKelurahan", pendudukKelurahan);
    		return "cariPendudukResult";
    	}else if(kc !=null) {
    		model.addAttribute("kc",kc);
    		model.addAttribute("iskota", false);
    		model.addAttribute("iskecamatan", true);

        	List<KelurahanModel> semuaKelurahan = kelurahanDAO.selectSemuaKelurahan(Integer.parseInt(kc));
        	String namaKecamatan = kecamatanDAO.selectNamaKecamatan(Integer.parseInt(kc));
        	
        	model.addAttribute("nama_kecamatan", namaKecamatan);
        	String namaKota = kotaDAO.selectKota(Integer.parseInt(kt)).getNama_kota();
        	model.addAttribute("nama_kota", namaKota);
            model.addAttribute ("semuaKelurahan", semuaKelurahan);
    	}
    	else if(kt !=null) {

        	List<KecamatanModel> semuaKecamatan = kecamatanDAO.selectSemuaKecamatan(Integer.parseInt(kt));
        	String namaKota = kotaDAO.selectKota(Integer.parseInt(kt)).getNama_kota();
        	model.addAttribute("nama_kota", namaKota);
        	model.addAttribute ("semuaKecamatan", semuaKecamatan);
    		model.addAttribute("kt",kt);
    		model.addAttribute("iskota", true);
    		
    	}
    	
    	List<KotaModel> semuaKota = kotaDAO.selectSemuaKota();
        model.addAttribute("semuaKota", semuaKota);
    	return"cariPenduduk";
    }
    
    
    
    
    
}
