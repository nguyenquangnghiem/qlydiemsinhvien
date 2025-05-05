package com.quanlydiemsinhvien.qldsv.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quanlydiemsinhvien.qldsv.dto.NganhdaotaoDTO;
import com.quanlydiemsinhvien.qldsv.pojo.Nganhdaotao;

@Component
public class NganhDaoTaoConverter {
    
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private KhoaDaoTaoConverter khoaDaoTaoConverter;

    public NganhdaotaoDTO nganhdaotaoToNganhdaotaoDTO(Nganhdaotao nganhdaotao){
        NganhdaotaoDTO nganhdaotaoDTO = modelMapper.map(nganhdaotao, NganhdaotaoDTO.class);
        nganhdaotaoDTO.setIdKhoaDaoTao(khoaDaoTaoConverter.khoadaotaoToKhoadaotaoDTO(nganhdaotao.getIdKhoaDaoTao()));
        return nganhdaotaoDTO;
    }
}