package com.example.tugas1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.tugas1.model.KeluargaModel;
import com.example.tugas1.model.PendudukModel;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Many;

@Mapper
public interface KeluargaMapper{

    @Select("select id, nomor_kk, alamat, rt, rw, id_kelurahan from keluarga where "
    		+ "keluarga.id = #{id}")
    @Results(value = {
    		@Result(property="id", column="id"),
    		@Result(property="nomor_kk", column="nomor_kk"),
    		@Result(property="alamat", column="alamat"),
    		@Result(property="rt", column="rt"),
    		@Result(property="rw", column="rw"),
    		@Result(property="id_kelurahan", column="id_kelurahan")	
    })
  KeluargaModel selectKeluarga (@Param("id") int id);
    
    @Select("select id, nomor_kk, alamat, rt, rw, id_kelurahan from keluarga where "
    		+ "keluarga.nomor_kk = #{nomor_kk}")
    @Results(value = {
    		@Result(property="id", column="id"),
    		@Result(property="nomor_kk", column="nomor_kk"),
    		@Result(property="alamat", column="alamat"),
    		@Result(property="rt", column="rt"),
    		@Result(property="rw", column="rw"),
    		@Result(property="id_kelurahan", column="id_kelurahan"),
    		@Result(property="listKeluarga", column="id", 
    		javaType = List.class, many=@Many(select="selectAnggota"))
    })
  KeluargaModel selectKeluargaByNKK (@Param("nomor_kk") String nomor_kk);
    
    @Select("select id, nik, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, is_wni, id_keluarga, agama,"
    		+ "pekerjaan, status_perkawinan, status_dalam_keluarga, golongan_darah, is_wafat from penduduk where id_keluarga = #{keluarga.id}")
    List<PendudukModel> selectAnggota (@Param("id") String id);

   
    @Insert("INSERT INTO keluarga (nomor_kk, alamat, rt, rw, id_kelurahan) VALUES (#{nomor_kk}, #{alamat}, #{rt}, #{rw}, #{id_kelurahan})")
    void tambahKeluarga (KeluargaModel keluarga);
    
    @Select("select count(*) from keluarga where nomor_kk like #{nkk}")
    int countNKK(String nkk);
    
    @Update("UPDATE keluarga SET nomor_kk = #{nomor_kk}, alamat = #{alamat}, rt = #{rt}, rw = #{rw}, "
    		+ "id_kelurahan = #{id_kelurahan} where id = #{id}")
    void updateKeluarga(KeluargaModel keluarga);
    
    @Update("UPDATE keluarga SET is_tidak_berlaku = 1 WHERE id = #{id_keluarga}")
    void kematianPenduduk(@Param("id_keluarga") String id_keluarga);
}
