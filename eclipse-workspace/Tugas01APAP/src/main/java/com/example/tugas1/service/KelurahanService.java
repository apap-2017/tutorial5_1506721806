package com.example.tugas1.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.tugas1.model.KeluargaModel;
import com.example.tugas1.model.KelurahanModel;
import com.example.tugas1.model.PendudukModel;

public interface KelurahanService
{
    KelurahanModel selectKelurahan (int id);
    String selectKodeKelurahanByNama (String nama_kelurahan);
    String selectNamaKelurahan(int id_kelurahan);
    
    List<KelurahanModel> selectSemuaKelurahan (@Param("id_kecamatan") int id_kecamatan);
}
