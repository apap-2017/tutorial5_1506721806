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
import com.example.tugas1.model.KelurahanModel;
import com.example.tugas1.model.PendudukModel;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Many;

@Mapper
public interface KelurahanMapper{

    @Select("select id, id_kecamatan, kode_kelurahan, nama_kelurahan, kode_pos from kelurahan where "
    		+ "kelurahan.id = #{id}")
    @Results(value = {
    		@Result(property="id", column="id"),
    		@Result(property="id_kecamatan", column="ids_kecamatan"),
    		@Result(property="kode_kecamatan", column="kode_kecamatan"),
    		@Result(property="nama_kelurahan", column="nama_kelurahan"),
    		@Result(property="kode_pos", column="kode_pos")
    })
  KelurahanModel selectKelurahan (@Param("id") int id);
  
    @Select("select id from kelurahan where kelurahan.nama_kelurahan = #{nama_kelurahan}")
	String selectKodeKelurahanByNama (@Param("nama_kelurahan") String nama_kelurahan);
  
    @Select("select nama_kelurahan from kelurahan, keluarga where id_kelurahan = #{id}")
	String selectNamaKelurahan(@Param("id_kelurahan") int id_kelurahan);

    @Select("select * from kelurahan where kelurahan.id_kecamatan = #{id_kecamatan}")
    List<KelurahanModel> selectSemuaKelurahan (@Param("id_kecamatan") int id_kecamatan);
}
