package com.quanlydiemsinhvien.qldsv.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quanlydiemsinhvien.qldsv.dto.LoaihockyDTO;
import com.quanlydiemsinhvien.qldsv.pojo.Loaihocky;

@Component
public class LoaiHocKyConverter {
    
    @Autowired
    private ModelMapper modelMapper;

    public LoaihockyDTO loaihockyToLoaihockyDTO(Loaihocky loaihocky){
        return modelMapper.map(loaihocky, LoaihockyDTO.class);
    }
}