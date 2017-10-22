package com.example.tugas1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tugas1.dao.KeluargaMapper;
import com.example.tugas1.dao.KotaMapper;
import com.example.tugas1.dao.PendudukMapper;
import com.example.tugas1.model.KeluargaModel;
import com.example.tugas1.model.KotaModel;
import com.example.tugas1.model.PendudukModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KotaServiceDatabase implements KotaService
{
    @Autowired
    private KotaMapper kotaMapper;


    @Override
    public KotaModel selectKota (int id)
    {
        log.info ("select kota with id {}", id);
        return kotaMapper.selectKota (id);
    }


	@Override
	public List<KotaModel> selectSemuaKota() {
		// TODO Auto-generated method stub
		return kotaMapper.selectSemuaKota();
	}
}
