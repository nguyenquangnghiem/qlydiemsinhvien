package com.quanlydiemsinhvien.qldsv.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MonhocHockyDTO {
    private Integer idMonHocHocKy;
    private int soLuong;
    private int soLuongConLai;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private GiangVienDTO idGiangVien;
    private HocKyDTO idHocky;
    private MonhocDTO idMonHoc;
    private PhonghocDTO phongHoc;
}