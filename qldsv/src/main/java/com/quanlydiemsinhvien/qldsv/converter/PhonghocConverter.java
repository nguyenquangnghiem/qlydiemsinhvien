package com.quanlydiemsinhvien.qldsv.converter;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quanlydiemsinhvien.qldsv.dto.PhonghocDTO;
import com.quanlydiemsinhvien.qldsv.pojo.MonhocHocky;
import com.quanlydiemsinhvien.qldsv.pojo.Phonghoc;
import com.quanlydiemsinhvien.qldsv.repository.PhonghocRepository;
import com.quanlydiemsinhvien.qldsv.request.PhongHocCreateRequest;

@Component
public class PhonghocConverter {
    
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PhonghocRepository phonghocRepository;

    public PhonghocDTO phonghocToPhonghocDTO(Phonghoc phonghoc){
        return modelMapper.map(phonghoc, PhonghocDTO.class);
    }

    public Phonghoc phongHocCreateRequestToPhongHoc(PhongHocCreateRequest request){
        Phonghoc phonghoc = request.getIdPhongHoc() == null? null : phonghocRepository.findById(request.getIdPhongHoc()).orElse(null);
        Set<MonhocHocky> monhocHockyList = new HashSet<>();
        if(phonghoc != null){
            monhocHockyList = phonghoc.getMonhocHockySet();
        }
        Phonghoc newPhonghoc = modelMapper.map(request, Phonghoc.class);
        newPhonghoc.setMonhocHockySet(monhocHockyList);
        return newPhonghoc;
    }
}