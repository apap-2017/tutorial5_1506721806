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
public interface KecamatanMapper{

@Select("select id, id_kota, kode_kecamatan, nama_kecamatan from kecamatan where "
    		+ "kecamatan.id = #{id}")
    @Results(value = {
    		@Result(property="id", column="id"),
    		@Result(property="id_kota", column="id_kota"),
    		@Result(property="kode_kecamatan", column="kode_kecamatan"),
    		@Result(property="nama_kecamatan", column="nama_kecamatan"),	
    })
  KecamatanModel selectKecamatan (@Param("id") int id);

@Select("select kode_kecamatan from kecamatan, keluarga, kelurahan where keluarga.id = #{id_keluarga} AND "
		+ "kelurahan.id = keluarga.id_kelurahan AND kelurahan.id_kecamatan = kecamatan.id")
	String selectKodeKecamatan (@Param("id_keluarga") int id_keluarga);

@Select("select kode_kecamatan from kecamatan where kecamatan.nama_kecamatan = #{nama_kecamatan}")
	String selectKodeKecamatanByNama (@Param("nama_kecamatan") String nama_kecamatan);

@Select("select nama_kecamatan from kecamatan where id = #{id}")
	String selectNamaKecamatan(@Param("id") int id);

@Select("select * from kecamatan where id_kota = #{id_kota}")
List<KecamatanModel> selectSemuaKecamatan (@Param("id_kota") int id_kota);
}



