package com.quanlydiemsinhvien.qldsv.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LopHocDTO {
    private Integer idLopHoc;
    private String tenLopHoc;
    private KhoaDTO idKhoaHoc;
    private HedaotaoDTO idHeDaoTao;
    private NganhdaotaoDTO idNganh;
    private Long siSo;
}