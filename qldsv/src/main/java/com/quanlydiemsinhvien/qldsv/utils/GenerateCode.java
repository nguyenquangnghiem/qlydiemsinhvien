package com.quanlydiemsinhvien.qldsv.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.quanlydiemsinhvien.qldsv.pojo.MaSinhVienCounter;
import com.quanlydiemsinhvien.qldsv.pojo.MaGiangVienCounter;
import com.quanlydiemsinhvien.qldsv.repository.MaSinhVienCounterRepository;
import com.quanlydiemsinhvien.qldsv.repository.MaGiangVienCounterRepository;

@Component
public class GenerateCode {

    @Autowired
    private MaSinhVienCounterRepository maSinhVienCounterRepository;

    @Autowired
    private MaGiangVienCounterRepository maGiangVienCounterRepository;

    @Transactional
    public String generateMaSoSinhVien() {
        // Lock row để tránh race condition
        MaSinhVienCounter counter = maSinhVienCounterRepository.findByIdForUpdate(1)
            .orElseThrow(() -> new RuntimeException("Counter not found"));

        int newNumber = counter.getLastMaSv() + 1;
        counter.setLastMaSv(newNumber);
        maSinhVienCounterRepository.save(counter);

        return String.format("SV%05d", newNumber); // SV00001, SV00002,...
    }
    @Transactional
    public String generateMaSoGiangVien() {
        // Lock row để tránh race condition
        MaGiangVienCounter counter = maGiangVienCounterRepository.findByIdForUpdate(1)
            .orElseThrow(() -> new RuntimeException("Counter not found"));

        int newNumber = counter.getLastMaGv() + 1;
        counter.setLastMaGv(newNumber);
        maGiangVienCounterRepository.save(counter);

        return String.format("GV%05d", newNumber); // GV00001, GV00002,...
    }
}