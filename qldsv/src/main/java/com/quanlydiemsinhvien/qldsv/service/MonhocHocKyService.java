package com.quanlydiemsinhvien.qldsv.service;

import java.util.List;

import com.quanlydiemsinhvien.qldsv.request.MonHocHocKyCreateRequest;

public interface MonhocHocKyService {
    public boolean deleteById(Integer id);
    public void dangKyMonHocAdmin(Integer idHK, List<MonHocHocKyCreateRequest> requests);
}
