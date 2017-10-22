package com.example.tugas1.service;

import java.util.List;

import com.example.tugas1.model.KecamatanModel;
import com.example.tugas1.model.KotaModel;

public interface KotaService
{
    KotaModel selectKota (int id);
    List<KotaModel> selectSemuaKota ();
}
