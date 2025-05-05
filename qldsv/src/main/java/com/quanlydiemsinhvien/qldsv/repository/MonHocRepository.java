/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.quanlydiemsinhvien.qldsv.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.quanlydiemsinhvien.qldsv.pojo.Monhoc;

/**
 *
 * @author FPTSHOP
 */
public interface MonHocRepository extends JpaRepository<Monhoc, Integer> {
    Page<Monhoc> findByTenMonHocContaining(String tenMonHoc, Pageable pageable);
}
