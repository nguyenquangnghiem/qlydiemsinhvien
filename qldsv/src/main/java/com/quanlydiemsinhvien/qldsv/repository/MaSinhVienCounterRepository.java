package com.quanlydiemsinhvien.qldsv.repository;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.quanlydiemsinhvien.qldsv.pojo.MaSinhVienCounter;

public interface MaSinhVienCounterRepository extends JpaRepository<MaSinhVienCounter, Integer> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT c FROM MaSinhVienCounter c WHERE c.id = :id")
    Optional<MaSinhVienCounter> findByIdForUpdate(@Param("id") int id);
}