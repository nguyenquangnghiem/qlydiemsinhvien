package com.quanlydiemsinhvien.qldsv.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quanlydiemsinhvien.qldsv.pojo.Monhoc;
import com.quanlydiemsinhvien.qldsv.pojo.MonhocHocky;

public interface MonhocHockyRepository extends JpaRepository<MonhocHocky, Integer> {
    public List<MonhocHocky> findByIdMonHocHocKyIn(List<Integer> idMonHocHocKy);

    public List<MonhocHocky> findByIdGiangVien(String idGiangVien);

    MonhocHocky findByIdMonHoc(Monhoc idMonHoc);

    List<MonhocHocky> findByIdHocky_IdHocKy(Integer idHocKy);

    MonhocHocky findByIdMonHocAndIdHocky_IdHocKy(Monhoc idMonHoc, Integer idHocKy);

    MonhocHocky findByIdMonHoc_IdMonHocAndIdHocky_IdHocKy(Integer idMonHoc, Integer idHocKy);

    List<MonhocHocky> findByIdGiangVienAndIdHocky_NgayBatDauLessThanEqualAndIdHocky_NgayKetThucGreaterThanEqual(
            String idGiangvien, Date currentDate, Date currentDate2);

    List<MonhocHocky> findByIdGiangVienAndIdHocky_NgayBatDauGreaterThan(String idGiangvien, Date currentDate);

    List<MonhocHocky> findByIdGiangVienAndIdHocky_NgayKetThucLessThan(String idGiangvien, Date currentDate);

    List<MonhocHocky> findByIdHocky_IdLop_IdLopHocAndIdMonHoc_TenMonHocAndIdHocky_NgayDangKyLessThanEqualAndIdHocky_NgayHetHanGreaterThanEqual(
            Integer idLopHoc, String tenMonHoc, Date currentDate, Date currentDate2);

    List<MonhocHocky> findByIdHocky_IdLop_IdLopHocAndIdHocky_NgayDangKyLessThanEqualAndIdHocky_NgayHetHanGreaterThanEqual(
            Integer idLopHoc, Date currentDate1, Date currentDate2);
}
