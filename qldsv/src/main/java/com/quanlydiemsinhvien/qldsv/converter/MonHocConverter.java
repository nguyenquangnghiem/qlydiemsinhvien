package com.quanlydiemsinhvien.qldsv.converter;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quanlydiemsinhvien.qldsv.dto.MonhocDTO;
import com.quanlydiemsinhvien.qldsv.pojo.Monhoc;
import com.quanlydiemsinhvien.qldsv.pojo.MonhocHocky;
import com.quanlydiemsinhvien.qldsv.repository.MonHocRepository;
import com.quanlydiemsinhvien.qldsv.request.MonhocCreateRequest;

@Component
public class MonHocConverter {
    
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MonHocRepository monHocRepository;

    public MonhocDTO monhocToMonhocDTO(Monhoc monhoc){
        return modelMapper.map(monhoc, MonhocDTO.class);
    }

    public Monhoc monHocCreateRequestToMonHoc(MonhocCreateRequest request){
        Monhoc monhoc = request.getIdMonHoc() == null? null : monHocRepository.findById(request.getIdMonHoc()).orElse(null);
        Set<MonhocHocky> monhocHockyList = new HashSet<>();
        if(monhoc != null){
            monhocHockyList = monhoc.getMonhocHockySet();
        }
        Monhoc newMonHoc = modelMapper.map(request, Monhoc.class);
        newMonHoc.setMonhocHockySet(monhocHockyList);
        return newMonHoc;
    }
}