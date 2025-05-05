package com.quanlydiemsinhvien.qldsv.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quanlydiemsinhvien.qldsv.pojo.MonhocHocky;
import com.quanlydiemsinhvien.qldsv.pojo.Monhocdangky;

public interface MonhocdangkyRepository extends JpaRepository<Monhocdangky, Integer> {
    List<Monhocdangky> findByIdSinhVien(String IdSinhVien);

    Optional<Monhocdangky> findByIdSinhVienAndIdMonHoc(String IdSinhVien, MonhocHocky idMonHoc);

    List<Monhocdangky> findByIdMonHocInAndIdSinhVien(List<MonhocHocky> idMonHoc, String IdSinhVien);

    List<Monhocdangky> findByIdMonHoc(MonhocHocky idMonHoc);

    List<Monhocdangky> findByIdSinhVienAndTrangThaiAndKhoaMon(String IdSinhVien, Short trangThai,
            Short khoaMon);

    List<Monhocdangky> findByIdMonHocInAndIdSinhVienAndIdMonHoc_IdHocky_NgayDangKyLessThanEqualAndIdMonHoc_IdHocky_NgayHetHanGreaterThanEqual(
            List<MonhocHocky> idMonHoc, String IdSinhVien, Date currentDate, Date currentDate2);
}
