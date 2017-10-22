package com.example.tugas1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tugas1.dao.KeluargaMapper;
import com.example.tugas1.dao.KelurahanMapper;
import com.example.tugas1.dao.PendudukMapper;
import com.example.tugas1.model.KeluargaModel;
import com.example.tugas1.model.KelurahanModel;
import com.example.tugas1.model.PendudukModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KelurahanServiceDatabase implements KelurahanService
{
    @Autowired
    private KelurahanMapper kelurahanMapper;


    @Override
    public KelurahanModel selectKelurahan (int id)
    {
        log.info ("select kelurahan with id {}", id);
        return kelurahanMapper.selectKelurahan (id);
    }


	@Override
	public String selectKodeKelurahanByNama(String nama_kelurahan) {
		// TODO Auto-generated method stub
		return kelurahanMapper.selectKodeKelurahanByNama(nama_kelurahan);
	}


	@Override
	public String selectNamaKelurahan(int id_kelurahan) {
		// TODO Auto-generated method stub
		log.info("select nama_kelurahan with id_kelurahan {}", id_kelurahan);
		return kelurahanMapper.selectNamaKelurahan(id_kelurahan);
	}


	@Override
	public List<KelurahanModel> selectSemuaKelurahan(int id_kecamatan) {
		// TODO Auto-generated method stub
		log.info("select * with id_kecamatan {}", id_kecamatan);
		return kelurahanMapper.selectSemuaKelurahan(id_kecamatan);
	}


	
}
