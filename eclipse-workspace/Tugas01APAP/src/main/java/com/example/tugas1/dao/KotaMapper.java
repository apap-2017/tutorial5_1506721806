package com.example.tugas1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.tugas1.model.KecamatanModel;
import com.example.tugas1.model.KeluargaModel;
import com.example.tugas1.model.KotaModel;
import com.example.tugas1.model.PendudukModel;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Many;

@Mapper
public interface KotaMapper{
	
@Select("select id, kode_kota, nama_kota from kota where "
    		+ "kota.id = #{id}")
    @Results(value = {
    		@Result(property="id", column="id"),
    		@Result(property="kode_kota", column="id_kota"),
    		@Result(property="nama_kota", column="nama_kota"),	
    })
  KotaModel selectKota(@Param("id") int id);
    

@Select("select * from kota")
List<KotaModel> selectSemuaKota ();
}
