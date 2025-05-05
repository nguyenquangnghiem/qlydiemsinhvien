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
public class NganhdaotaoDTO {
    private Integer idNganhDaoTao;
    private String tenNganhDaoTao;
    private KhoadaotaoDTO idKhoaDaoTao;
}