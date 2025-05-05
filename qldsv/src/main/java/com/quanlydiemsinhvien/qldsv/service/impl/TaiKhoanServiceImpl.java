/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlydiemsinhvien.qldsv.service.impl;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quanlydiemsinhvien.qldsv.request.LoginRequest;
import com.quanlydiemsinhvien.qldsv.request.TaikhoanCreateRequest;
import com.quanlydiemsinhvien.qldsv.service.KeycloakUserService;
import com.quanlydiemsinhvien.qldsv.service.TaiKhoanService;

/**
 *
 * @author Admin
 */
@Service
@Transactional
public class TaiKhoanServiceImpl implements TaiKhoanService {

    @Autowired
    private KeycloakUserService keycloakUserService;

    @Value("${keycloak.url}")
    private String keycloakLink;
    @Value("${keycloak.client-secret}")
    private String clientSecret;
    @Value("${keycloak.client-id}")
    private String clientId;

    @Override
    public List<Map<String, Object>> getTaiKhoans(Map<String, String> params) {
        List<Map<String, Object>> result = keycloakUserService.getAllUsers();
        for (int i = 0; i < result.size(); i++) {
            List<String> roles = keycloakUserService.getUserRoles(result.get(i).get("id").toString());
            result.get(i).put("chucVu", roles);
        }
        return result;
    }

    // @Override
    // public Taikhoan getUserByUsername(String username) {
    // return taikhoanRepository.findByTenTaiKhoan(username).orElse(null);
    // }

    // // Dang Nhap
    // @Override
    // public UserDetails loadUserByUsername(String tenTK) throws
    // UsernameNotFoundException {
    // Taikhoan taikhoans = this.getUserByUsername(tenTK);
    // if (taikhoans == null) {
    // throw new UsernameNotFoundException("Tài khoản không tồn tại!!!");
    // }

    // Set<GrantedAuthority> auth = new HashSet<>();
    // auth.add(new
    // SimpleGrantedAuthority(taikhoans.getChucVu().getTenloaitaikhoan()));
    // return new
    // org.springframework.security.core.userdetails.User(taikhoans.getTenTaiKhoan(),
    // taikhoans.getMatKhau(), auth);
    // }

    @Override
    public UserDetails getLoggedInUserDetails(Authentication authentication) {
        // Trả về thông tin UserDetails của người dùng đã đăng nhập thành công
        return (UserDetails) authentication.getPrincipal();
    }

    // @Override
    // public String GetIdTaiKhoan(UserDetails userDetails) {
    // Taikhoan taikhoans = this.getUserByUsername(userDetails.getUsername());

    // return taikhoans.getIdTaiKhoan();
    // }

    @Override
    public ResponseEntity<?> thayDoiMatKhau(Map<String, String> params, Principal principal) {
        String userName = keycloakUserService.getUsernameByUserId(principal.getName());
        if (!keycloakUserService.checkOldPassword(userName, params.get("matKhau"))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        if (!keycloakUserService.updateUserPassword(principal.getName(), params.get("matKhauMoi"))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public void thayDoiMatKhauAD(TaikhoanCreateRequest user, Principal principal) {
        if (!keycloakUserService.checkOldPassword(keycloakUserService.getUsernameByUserId(principal.getName()),
                user.getMatKhau())) {
            throw new RuntimeException("Thay đổi mật khẩu thất bại!");
        }
        if (keycloakUserService.updateUserPassword(principal.getName(), user.getMkMoi())) {
            return;
        }
        throw new RuntimeException("Thay đổi mật khẩu thất bại!");
    }

    @Override
    public Map<String, String> login(LoginRequest user) {
        try {
            String keycloakUrl = keycloakLink + "/protocol/openid-connect/token";

            // Cấu hình tham số
            String credentials = "client_id=" + clientId + "&client_secret=" + clientSecret
                    + "&grant_type=password&username=" + user.getTenTaiKhoan()
                    + "&password=" + user.getMatKhau();

            // Tạo RestTemplate để gửi request
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            HttpEntity<String> entity = new HttpEntity<>(credentials, headers);

            // Gửi request POST đến Keycloak
            ResponseEntity<String> response = restTemplate.exchange(keycloakUrl, HttpMethod.POST, entity, String.class);

            // Chuyển đổi JSON response thành Map
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> tokenResponse = objectMapper.readValue(response.getBody(),
                    new TypeReference<Map<String, String>>() {
                    });

            // Trả về cả access_token và refresh_token
            Map<String, String> tokens = new HashMap<>();
            tokens.put("access_token", tokenResponse.get("access_token"));
            tokens.put("refresh_token", tokenResponse.get("refresh_token"));

            return tokens;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // @Override
    // public boolean authUser(String username, String password) {
    // Taikhoan taikhoan = taikhoanRepository.findByTenTaiKhoan(username)
    // .orElseThrow(() -> new RuntimeException("Tài khoản không tồn tại!"));
    // return this.passwordEncoder.matches(password, taikhoan.getMatKhau());
    // }

    @Override
    public Map<String, Object> getUserById(String id) {
        Map<String, Object> info = keycloakUserService.getUserById(id);
        List<String> roles = keycloakUserService.getUserRoles(id);
        roles.remove("default-roles-qlydiemsinhvien");
        info.put("chucVu", roles);
        return info;
    }

    // public List<Loaitaikhoan> getLoaitaikhoanList(Map<String, String> params) {
    // try {
    // String tenSV = params.get("tenLTK");
    // return loaitaikhoanRepository.findByTenloaitaikhoanContaining(tenSV);
    // } catch (Exception ex) {
    // ex.printStackTrace();
    // return null;
    // }
    // }

    // @Override
    // public Loaitaikhoan getLoaiTaiKhoanById(int id) {
    // try {
    // return loaitaikhoanRepository.findById(id)
    // .orElseThrow(() -> new RuntimeException("Loại tài khoản không tồn tại!"));
    // } catch (Exception ex) {
    // ex.printStackTrace();
    // return null;
    // }
    // }

    // @Override
    // public boolean addOrUpdateLoaiTK(Loaitaikhoan ltk) {
    // try {
    // loaitaikhoanRepository.save(ltk);
    // return true;
    // } catch (Exception ex) {
    // ex.printStackTrace();
    // return false;
    // }
    // }

    @Override
    public long countTaiKhoan() {
        return keycloakUserService.getAllUsers().size();
    }

    @Override
    public ResponseEntity<String> logout(String authHeader) {
        try {
            // Lấy Access Token từ Header
            String accessToken = authHeader.replace("Bearer ", "");

            // Gọi API của Keycloak để logout
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("client_id", clientId);
            params.add("client_secret", clientSecret);
            params.add("refresh_token", accessToken); // Keycloak yêu cầu refresh token

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
            String logoutUrl = keycloakLink + "/protocol/openid-connect/logout";

            restTemplate.postForEntity(logoutUrl, request, String.class);
            return ResponseEntity.ok("Logged out successfully!");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Logout failed!");
        }
    }
}
