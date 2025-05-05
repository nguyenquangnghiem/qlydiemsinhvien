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
public class HeDaoTaoCreateRequest {
    private Integer idHeDaoTao;
    private String tenHeDaoTao;
}