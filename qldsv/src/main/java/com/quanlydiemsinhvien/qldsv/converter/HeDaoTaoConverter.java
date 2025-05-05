package com.quanlydiemsinhvien.qldsv.converter;
import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quanlydiemsinhvien.qldsv.dto.HedaotaoDTO;
import com.quanlydiemsinhvien.qldsv.pojo.Hedaotao;
import com.quanlydiemsinhvien.qldsv.pojo.Lophoc;
import com.quanlydiemsinhvien.qldsv.repository.HeDaoTaoRepository;
import com.quanlydiemsinhvien.qldsv.request.HeDaoTaoCreateRequest;

@Component
public class HeDaoTaoConverter {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private HeDaoTaoRepository heDaoTaoRepository;

    public HedaotaoDTO hedaotaoToHedaotaoDTO(Hedaotao hedaotao){
        return modelMapper.map(hedaotao, HedaotaoDTO.class);
    }

    public Hedaotao heDaoTaoCreateRequestToHeDaoTao(HeDaoTaoCreateRequest request){
        Hedaotao hedaotao = request.getIdHeDaoTao() == null? null : heDaoTaoRepository.findById(request.getIdHeDaoTao()).orElse(null);
        Set<Lophoc> lophocList = new HashSet<>();
        if(hedaotao != null){
            lophocList = hedaotao.getLophocSet();
        }
        Hedaotao newHedaotao = modelMapper.map(request, Hedaotao.class);
        newHedaotao.setLophocSet(lophocList);
        return newHedaotao;
    }
}