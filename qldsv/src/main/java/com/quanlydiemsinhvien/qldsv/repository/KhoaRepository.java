package com.quanlydiemsinhvien.qldsv.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.quanlydiemsinhvien.qldsv.pojo.Khoa;

public interface KhoaRepository extends JpaRepository<Khoa, Integer> {
    Page<Khoa> findByTenKhoaContaining(String tenKhoa, Pageable pageable);
}
