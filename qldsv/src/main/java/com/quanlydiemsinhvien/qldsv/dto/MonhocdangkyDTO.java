package com.quanlydiemsinhvien.qldsv.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonhocdangkyDTO {
    private Integer idMonHocDangKy;
    private String xepLoai;
    private Short khoaMon;
    private Short thanhToan;
    private Short trangThai;
    private MonhocHockyDTO idMonHoc;
    private SinhVienDTO idSinhVien;
}