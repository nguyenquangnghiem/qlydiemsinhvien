package com.quanlydiemsinhvien.qldsv.converter;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quanlydiemsinhvien.qldsv.dto.KhoadaotaoDTO;
import com.quanlydiemsinhvien.qldsv.pojo.Khoadaotao;
import com.quanlydiemsinhvien.qldsv.pojo.Nganhdaotao;
import com.quanlydiemsinhvien.qldsv.repository.KhoaDaoTaoRepository;
import com.quanlydiemsinhvien.qldsv.request.KhoaDaoTaoCreateRequest;

@Component
public class KhoaDaoTaoConverter {
    
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private KhoaDaoTaoRepository khoaDaoTaoRepository;

    public KhoadaotaoDTO khoadaotaoToKhoadaotaoDTO(Khoadaotao khoadaotao){
        return modelMapper.map(khoadaotao, KhoadaotaoDTO.class);
    }

    public Khoadaotao khoaDaoTaoCreateRequestToKhoaDaoTao(KhoaDaoTaoCreateRequest request){
        Khoadaotao khoadaotao = request.getIdKhoaDaoTao() == null? null : khoaDaoTaoRepository.findById(request.getIdKhoaDaoTao()).orElse(null);
        Set<Nganhdaotao> nganhdaotaoList = new HashSet<>();
        
        if(khoadaotao != null){
            nganhdaotaoList = khoadaotao.getNganhdaotaoSet();
        }
        Khoadaotao newKhoadaotao = modelMapper.map(request, Khoadaotao.class);
        newKhoadaotao.setNganhdaotaoSet(nganhdaotaoList);
        return newKhoadaotao;
    }
}