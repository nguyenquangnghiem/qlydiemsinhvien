package com.quanlydiemsinhvien.qldsv.repository;

import com.quanlydiemsinhvien.qldsv.pojo.Phonghoc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhonghocRepository extends JpaRepository<Phonghoc, Integer> {
    List<Phonghoc> findByTenPhongHocContaining(String tenPhong);
}
