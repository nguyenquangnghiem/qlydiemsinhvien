package com.quanlydiemsinhvien.qldsv.converter;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.quanlydiemsinhvien.qldsv.dto.GiaoVuDTO;

@Component
public class GiaoVuConverter {

    public GiaoVuDTO giaoVuToGiaoVuDTO(Map<String, Object> giaovu) {
        Map<String, Object> attributes = (Map<String, Object>) giaovu.get("attributes");
        return GiaoVuDTO.builder()
                .gioiTinh((String) ((ArrayList) attributes.get("gioi_tinh")).get(0))
                .idGiaoVu((String) giaovu.get("id"))
                .soDienThoai((String) ((ArrayList) attributes.get("so_dien_thoai")).get(0))
                .tenGiaoVu((String) ((ArrayList) attributes.get("ho_ten")).get(0)).build();
    }
}