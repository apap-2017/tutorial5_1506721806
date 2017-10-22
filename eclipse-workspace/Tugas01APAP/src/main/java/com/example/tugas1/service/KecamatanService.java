package com.example.tugas1.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.tugas1.model.KecamatanModel;

public interface KecamatanService
{
    KecamatanModel selectKecamatan (int id);
    
    String selectKodeKecamatan(int id_keluarga);
    
    String selectKodeKecamatanByNama (String nama_kecamatan);
    
    String selectNamaKecamatan(int id);
    
    List<KecamatanModel> selectSemuaKecamatan (@Param("id_kota") int id_kota);
}
