package com.quanlydiemsinhvien.qldsv.repository;

import com.quanlydiemsinhvien.qldsv.pojo.Loaidiem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoaidiemRepository extends JpaRepository<Loaidiem, Integer> {
    public Optional<Loaidiem> findByTenDiem(String tenDiem);
}
