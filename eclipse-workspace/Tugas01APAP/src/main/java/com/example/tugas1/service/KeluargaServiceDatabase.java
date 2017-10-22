package com.example.tugas1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tugas1.dao.KeluargaMapper;
import com.example.tugas1.dao.PendudukMapper;
import com.example.tugas1.model.KeluargaModel;
import com.example.tugas1.model.PendudukModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KeluargaServiceDatabase implements KeluargaService
{
    @Autowired
    private KeluargaMapper keluargaMapper;


    @Override
    public KeluargaModel selectKeluarga (int id)
    {
        log.info ("select keluarga with id {}", id);
        return keluargaMapper.selectKeluarga (id);
    }
    
    @Override
    public KeluargaModel selectKeluargaByNKK (String nomor_kk)
    {
        log.info ("select keluarga with nomor_kk {}", nomor_kk);
        return keluargaMapper.selectKeluargaByNKK (nomor_kk);
    }

	@Override
	public int countNKK(String nkk1) {
		log.info ("count keluarga with nomor_kk {}", nkk1);
		return keluargaMapper.countNKK(nkk1);
	}

	@Override
	public void tambahKeluarga(KeluargaModel keluarga){
		log.info ("tambah keluarga with nomor_kk {}", keluarga.getNomor_kk());
		keluargaMapper.tambahKeluarga (keluarga);
	}

	@Override
	public void updateKeluarga(KeluargaModel keluarga) {
		log.info ("update keluarga with nomor_kk {}", keluarga.getNomor_kk());
		keluargaMapper.updateKeluarga(keluarga);
		
	}

	@Override
	public void kematianPenduduk(String id_keluarga) {
		// TODO Auto-generated method stub
		log.info ("select penduduk with id_keluarga {}", id_keluarga);
		keluargaMapper.kematianPenduduk(id_keluarga);
	}
}
