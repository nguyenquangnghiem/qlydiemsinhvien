/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.quanlydiemsinhvien.qldsv.service;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import com.quanlydiemsinhvien.qldsv.request.LoginRequest;
import com.quanlydiemsinhvien.qldsv.request.TaikhoanCreateRequest;

/**
 *
 * @author Admin
 */
public interface TaiKhoanService {
    // Taikhoan updateImg(Map<String, String> params, MultipartFile avatar);
    // boolean addAcount(TaikhoanDTO t);
    List<Map<String, Object>> getTaiKhoans(Map<String, String> params);

    // Taikhoan getUserByUsername(String username);
    UserDetails getLoggedInUserDetails(Authentication authentication);

    // String GetIdTaiKhoan(UserDetails userDetails);
    public ResponseEntity<?> thayDoiMatKhau(Map<String, String> params, Principal principal);

    void thayDoiMatKhauAD(TaikhoanCreateRequest user, Principal principal);

    public Map<String, String> login(LoginRequest user);

    // boolean authUser(String username, String password);
    Map<String, Object> getUserById(String id);

    // List<Loaitaikhoan> getLoaitaikhoanList(Map<String, String> params);
    // Loaitaikhoan getLoaiTaiKhoanById(int id);
    // boolean addOrUpdateLoaiTK(Loaitaikhoan ltk);
    long countTaiKhoan();

    ResponseEntity<String> logout(String authHeader);
}
