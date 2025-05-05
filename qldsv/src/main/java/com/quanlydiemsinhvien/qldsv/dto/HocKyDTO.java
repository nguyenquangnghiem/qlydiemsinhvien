package com.quanlydiemsinhvien.qldsv.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HocKyDTO {
    private Integer idHocKy;
    private LoaihockyDTO tenHocKy;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private Date ngayDangKy;
    private Date ngayHetHan;
    private LopHocDTO idLop;
}