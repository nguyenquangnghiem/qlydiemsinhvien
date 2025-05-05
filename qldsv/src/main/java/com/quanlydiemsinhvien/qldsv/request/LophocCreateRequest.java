package com.quanlydiemsinhvien.qldsv.request;

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
public class LophocCreateRequest {
    private Integer idLopHoc;
    private String tenLopHoc;
    private Integer idKhoaHoc;
    private Integer idHeDaoTao;
    private Integer idNganh;
}