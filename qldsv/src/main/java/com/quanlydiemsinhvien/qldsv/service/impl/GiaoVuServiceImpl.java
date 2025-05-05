/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlydiemsinhvien.qldsv.service.impl;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quanlydiemsinhvien.qldsv.converter.GiaoVuConverter;
import com.quanlydiemsinhvien.qldsv.dto.GiaoVuDTO;
import com.quanlydiemsinhvien.qldsv.service.GiaoVuService;
import com.quanlydiemsinhvien.qldsv.service.KeycloakUserService;

/**
 *
 * @author FPTSHOP
 */
@Service
@Transactional
public class GiaoVuServiceImpl implements GiaoVuService {

    @Autowired
    private GiaoVuConverter giaoVuConverter;

    @Autowired
    private KeycloakUserService keycloakUserService;

    @Override
    public GiaoVuDTO getGiaoVu(Principal auth) {
        String id = auth.getName();
        return giaoVuConverter.giaoVuToGiaoVuDTO(keycloakUserService.getUserById(id));
    }

}
