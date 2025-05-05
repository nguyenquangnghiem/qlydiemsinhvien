package com.quanlydiemsinhvien.qldsv.request;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MonHocHocKyCreateRequest {
    private Integer idMonHocHocKy;
    private String giangVien;
    private Integer idMonHoc;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private Integer phongHoc;
    private Integer soLuong;
}