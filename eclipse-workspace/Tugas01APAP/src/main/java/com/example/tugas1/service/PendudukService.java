package com.example.tugas1.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.tugas1.model.PendudukModel;

public interface PendudukService
{
    PendudukModel selectPenduduk (String nik);
    void tambahPenduduk (PendudukModel penduduk);
    int countNIK(String nik);
    void updatePenduduk(PendudukModel penduduk);
    void kematianPenduduk(String nik);
    List<PendudukModel> selectPendudukByIdKelurahan(int id);
    int cekKematianKeluarga(String id_keluarga);
}
