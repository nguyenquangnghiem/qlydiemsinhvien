/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.quanlydiemsinhvien.qldsv.repository;


import com.quanlydiemsinhvien.qldsv.pojo.Hedaotao;
import com.quanlydiemsinhvien.qldsv.pojo.Hocky;
import com.quanlydiemsinhvien.qldsv.pojo.Khoa;
import com.quanlydiemsinhvien.qldsv.pojo.Khoadaotao;
import com.quanlydiemsinhvien.qldsv.pojo.Lophoc;

import com.quanlydiemsinhvien.qldsv.pojo.Nganhdaotao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

/**
 *
 * @author FPTSHOP
 */


public interface NghanhDaoTaoRepository extends JpaRepository<Nganhdaotao, Integer> {
}
