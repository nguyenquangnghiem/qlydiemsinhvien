package com.quanlydiemsinhvien.qldsv.service;

import java.util.List;
import java.util.Map;

import com.quanlydiemsinhvien.qldsv.dto.UserDTO;
import com.quanlydiemsinhvien.qldsv.request.UserCreateRequest;

public interface KeycloakUserService {
    public void assignRoleToUser(String userId, String roleName) throws Exception;

    public String createUserInKeycloak(UserDTO user);

    public boolean updateUser(String userId, UserCreateRequest request);

    public boolean deleteUser(String userId);

    public String getKeycloakUserId(String username);

    public boolean updateUserPassword(String userId, String newPassword);

    public boolean checkOldPassword(String username, String oldPassword);

    public String getUsernameByUserId(String userId);

    public List<String> getUserRoles(String userId);

    public String getUserIdByUsername(String username);

    public List<Map<String, Object>> getAllUsers();

    public Map<String, Object> getUserById(String userId);

    public Map<String, Object> getUsersByRoles(String role);

    public List<Map<String, Object>> getUserByFullNameAndRole(String fullName, String role);

    public List<Map<String, Object>> getSinhVienByIdLop(Integer idLop);
}
