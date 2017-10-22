package com.example.tugas1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tugas1.dao.PendudukMapper;
import com.example.tugas1.model.PendudukModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PendudukServiceDatabase implements PendudukService
{
    @Autowired
    private PendudukMapper pendudukMapper;


    @Override
    public PendudukModel selectPenduduk (String nik)
    {
        log.info ("select penduduk with nik {}", nik);
        return pendudukMapper.selectPenduduk (nik);
    }
    
    @Override
    public void tambahPenduduk (PendudukModel penduduk)
    {
        pendudukMapper.tambahPenduduk (penduduk);
    }

	@Override
	public int countNIK(String nik) {
		// TODO Auto-generated method stub
		log.info ("select penduduk with nik {}", nik);
		return pendudukMapper.countNIK(nik);
	}

	@Override
	public void updatePenduduk(PendudukModel penduduk) {
		// TODO Auto-generated method stub
		log.info ("select penduduk with nik {}", penduduk.getNik());
		pendudukMapper.updatePenduduk(penduduk);;
	}

	@Override
	public void kematianPenduduk(String nik) {
		// TODO Auto-generated method stub
		log.info ("select penduduk with nik {}", nik);
		pendudukMapper.kematianPenduduk(nik);;
		
	}

	@Override
	public int cekKematianKeluarga(String id_keluarga) {
		// TODO Auto-generated method stub
		log.info ("select penduduk with id_keluarga {}", id_keluarga);
		return pendudukMapper.cekKematianKeluarga(id_keluarga);
	}

	@Override
	public List<PendudukModel> selectPendudukByIdKelurahan(int id) {
		
		return pendudukMapper.selectPendudukByIdKelurahan(id);
	}
}
