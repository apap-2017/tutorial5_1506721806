package com.example.tugas1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tugas1.dao.KecamatanMapper;
import com.example.tugas1.dao.KeluargaMapper;
import com.example.tugas1.dao.PendudukMapper;
import com.example.tugas1.model.KecamatanModel;
import com.example.tugas1.model.KeluargaModel;
import com.example.tugas1.model.PendudukModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KecamatanServiceDatabase implements KecamatanService
{
    @Autowired
    private KecamatanMapper kecamatanMapper;


    @Override
    public KecamatanModel selectKecamatan (int id)
    {
        log.info ("select kecamatan with id {}", id);
        return kecamatanMapper.selectKecamatan (id);
    }

	@Override
	public String selectKodeKecamatan(int id_keluarga) {
		log.info("select kode_kecamatan with id_keluarga {}", id_keluarga);
		return kecamatanMapper.selectKodeKecamatan(id_keluarga);
	}

	@Override
	public String selectKodeKecamatanByNama(String nama_kecamatan) {
		log.info("select kode_kecamatan with nama_kecamatan {}", nama_kecamatan);
		return kecamatanMapper.selectKodeKecamatanByNama(nama_kecamatan);
	}

	@Override
	public String selectNamaKecamatan(int id) {
		// TODO Auto-generated method stub
		log.info("select nama_kecamatan with id_kecamatan {}", id);
		return kecamatanMapper.selectNamaKecamatan(id);
	}

	@Override
	public List<KecamatanModel> selectSemuaKecamatan(int id_kota) {
		// TODO Auto-generated method stub
		log.info("select * with id_kota {}", id_kota);
		return kecamatanMapper.selectSemuaKecamatan(id_kota);
		
	}
}
