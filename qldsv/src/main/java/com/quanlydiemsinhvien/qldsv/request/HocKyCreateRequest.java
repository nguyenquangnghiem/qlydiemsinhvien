package com.quanlydiemsinhvien.qldsv.request;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HocKyCreateRequest {
    private Integer idHocKy;
    private Integer tenHocKy;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private Date ngayDangKy;
    private Date ngayHetHan;
    private Integer idLop;
}