package com.quanlydiemsinhvien.qldsv.converter;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quanlydiemsinhvien.qldsv.dto.KhoaDTO;
import com.quanlydiemsinhvien.qldsv.pojo.Khoa;
import com.quanlydiemsinhvien.qldsv.pojo.Lophoc;
import com.quanlydiemsinhvien.qldsv.repository.KhoaRepository;
import com.quanlydiemsinhvien.qldsv.request.KhoaCreateRequest;

@Component
public class KhoaConverter {
    
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private KhoaRepository khoaRepository;

    public KhoaDTO khoaToKhoaDTO(Khoa khoa){
        return modelMapper.map(khoa, KhoaDTO.class);
    }

    public Khoa khoaCreateRequestToKhoa(KhoaCreateRequest request){
        Khoa khoa = request.getIdKhoa() == null? null : khoaRepository.findById(request.getIdKhoa()).orElse(null);
        Set<Lophoc> lophocList = new HashSet<>();
        if(khoa != null){
            lophocList = khoa.getLophocSet();
        }
        Khoa newKhoa = modelMapper.map(request, Khoa.class);
        newKhoa.setLophocSet(lophocList);
        return newKhoa;
    }
}