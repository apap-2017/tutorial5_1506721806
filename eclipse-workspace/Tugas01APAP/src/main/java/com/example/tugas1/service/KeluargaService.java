package com.example.tugas1.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.tugas1.model.KeluargaModel;
import com.example.tugas1.model.PendudukModel;

public interface KeluargaService
{
    KeluargaModel selectKeluarga (int id);
    KeluargaModel selectKeluargaByNKK (String nomor_kk);
    void tambahKeluarga (KeluargaModel keluarga);
	int countNKK(String nkk1);
	void updateKeluarga(KeluargaModel keluarga);
	void kematianPenduduk(String id_keluarga);
}
