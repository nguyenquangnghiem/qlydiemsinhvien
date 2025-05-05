package com.quanlydiemsinhvien.qldsv.repository;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.quanlydiemsinhvien.qldsv.pojo.MaGiangVienCounter;

public interface MaGiangVienCounterRepository extends JpaRepository<MaGiangVienCounter, Integer> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT c FROM MaGiangVienCounter c WHERE c.id = :id")
    Optional<MaGiangVienCounter> findByIdForUpdate(@Param("id") int id);
}