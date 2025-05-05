// package com.quanlydiemsinhvien.qldsv.converter;

// import java.util.List;
// import java.util.Map;

// import org.modelmapper.ModelMapper;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;

// import com.quanlydiemsinhvien.qldsv.dto.CauhoidiendangDTO;
// import com.quanlydiemsinhvien.qldsv.pojo.Cauhoidiendang;
// import com.quanlydiemsinhvien.qldsv.service.KeycloakUserService;

// @Component
// public class CauhoidiendangConverter {
    
//     @Autowired
//     private ModelMapper modelMapper;

//     @Autowired
//     private KeycloakUserService keycloakUserService;

//     public CauhoidiendangDTO cauhoidiendangToCauhoidiendangDTO(Cauhoidiendang cauhoidiendang){
//         CauhoidiendangDTO cauhoidiendangDTO = modelMapper.map(cauhoidiendang, CauhoidiendangDTO.class);
//         Map<String, Object> user = keycloakUserService.getUserById(cauhoidiendang.getIdTaiKhoan());
//         List<String> roles = keycloakUserService.getUserRoles(cauhoidiendang.getIdTaiKhoan());
//         roles.remove("default-roles-qlydiemsinhvien");
//         if(user != null) {
//             user.put("chucVu", roles);
//         }
//         cauhoidiendangDTO.setTaiKhoan(user);
//         return cauhoidiendangDTO;
//     }
// }