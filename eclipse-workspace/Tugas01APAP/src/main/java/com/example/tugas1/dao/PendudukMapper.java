package com.example.tugas1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.tugas1.model.PendudukModel;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Many;

@Mapper
public interface PendudukMapper
{	
    @Select("select id, nik, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, is_wni, id_keluarga, agama,"
    		+ "pekerjaan, status_perkawinan, status_dalam_keluarga, golongan_darah, is_wafat from penduduk where nik = #{nik}")
    @Results(value = {
    		@Result(property="id", column="id"),
    		@Result(property="nik", column="nik"),
    		@Result(property="nama", column="nama"),
    		@Result(property="tempat_lahir", column="tempat_lahir"),
    		@Result(property="tanggal_lahir", column="tanggal_lahir"),
    		@Result(property="jenis_kelamin", column="jenis_kelamin"),
    		@Result(property="is_wni", column="is_wni"),
    		@Result(property="id_keluarga", column="id_keluarga"),
    		@Result(property="agama", column="agama"),
    		@Result(property="pekerjaan", column="pekerjaan"),
    		@Result(property="status_perkawinan", column="status_perkawinan"),
    		@Result(property="golongan_darah", column="golongan_darah"),
    		@Result(property="is_wafat", column="is_wafat")
    })
  PendudukModel selectPenduduk (@Param("nik") String nik);
    
    @Insert("INSERT INTO penduduk (nik, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, golongan_darah, agama, status_perkawinan, status_dalam_keluarga, pekerjaan, "
    		+ "is_wni, is_wafat, id_keluarga) VALUES (#{nik}, #{nama}, #{tempat_lahir}, #{tanggal_lahir}, #{jenis_kelamin}, #{golongan_darah},"
    		+ "#{agama}, #{status_perkawinan}, #{status_dalam_keluarga}, #{pekerjaan}, #{is_wni}, #{is_wafat}, #{id_keluarga})")
    void tambahPenduduk (PendudukModel penduduk);
    
    @Select("select count(*) from penduduk where nik like #{nik}")
    int countNIK(@Param("nik") String nik);
    
    
    @Update("UPDATE penduduk SET nik = #{nik}, nama = #{nama}, tempat_lahir = #{tempat_lahir}, tanggal_lahir = #{tanggal_lahir},"
    		+ "jenis_kelamin = #{jenis_kelamin}, is_wni = #{is_wni}, id_keluarga = #{id_keluarga},"
    		+ "agama = #{agama}, pekerjaan = #{pekerjaan}, status_perkawinan = #{status_perkawinan},"
    		+ "golongan_darah = #{golongan_darah}, is_wafat = #{is_wafat} WHERE id = #{id}")
    void updatePenduduk(PendudukModel penduduk);

    @Update("UPDATE penduduk SET is_wafat = 1 WHERE nik = #{nik}")
    void kematianPenduduk(@Param("nik") String nik);
    
    @Select("select count(*) from penduduk where id_keluarga = #{id_keluarga} AND is_wafat = 0")
    int cekKematianKeluarga(@Param("id_keluarga") String id_keluarga);
    
    @Select("select * from penduduk, keluarga, kelurahan where "
    		+ "keluarga.id = penduduk.id_keluarga and keluarga.id_kelurahan = kelurahan.id and kelurahan.id = #{id}")
    List<PendudukModel> selectPendudukByIdKelurahan(@Param("id") int id);
}
