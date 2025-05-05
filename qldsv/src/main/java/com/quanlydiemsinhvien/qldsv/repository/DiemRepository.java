/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.quanlydiemsinhvien.qldsv.repository;

import com.quanlydiemsinhvien.qldsv.dto.DiemTrungBinhDTO;
import com.quanlydiemsinhvien.qldsv.pojo.Diem;
import com.quanlydiemsinhvien.qldsv.pojo.DiemMonHoc;
import com.quanlydiemsinhvien.qldsv.pojo.Loaidiem;
import com.quanlydiemsinhvien.qldsv.pojo.MonhocHocky;
import com.quanlydiemsinhvien.qldsv.pojo.Monhocdangky;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
public interface DiemRepository extends JpaRepository<Diem, Integer> {
    List<Diem> findByIdMonHocInAndTenDiem(List<Monhocdangky> idMonHocList, Loaidiem tenDiem);

    void deleteByTenDiemIn (List<Loaidiem> tenDiemList);

    List<Diem> findByIdMonHoc(Monhocdangky idMonHocDangKy);

    public Optional<Diem> findByIdMonHocAndTenDiem(Monhocdangky idMonHoc, Loaidiem idLoaiDiem);
}
