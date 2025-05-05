package com.quanlydiemsinhvien.qldsv.converter;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quanlydiemsinhvien.qldsv.dto.LopHocDTO;
import com.quanlydiemsinhvien.qldsv.pojo.Hocky;
import com.quanlydiemsinhvien.qldsv.pojo.Lophoc;
import com.quanlydiemsinhvien.qldsv.repository.HeDaoTaoRepository;
import com.quanlydiemsinhvien.qldsv.repository.KhoaRepository;
import com.quanlydiemsinhvien.qldsv.repository.LopHocRepository;
import com.quanlydiemsinhvien.qldsv.repository.NghanhDaoTaoRepository;
import com.quanlydiemsinhvien.qldsv.request.LophocCreateRequest;
import com.quanlydiemsinhvien.qldsv.service.KeycloakUserService;

@Component
public class LopHocConverter {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private KhoaConverter khoaConverter;

    @Autowired
    private HeDaoTaoConverter heDaoTaoConverter;

    @Autowired
    private NganhDaoTaoConverter nganhDaoTaoConverter;

    @Autowired
    private LopHocRepository lopHocRepository;

    @Autowired
    private HeDaoTaoRepository heDaoTaoRepository;

    @Autowired
    private NghanhDaoTaoRepository nghanhDaoTaoRepository;

    @Autowired
    private KhoaRepository khoaRepository;

    @Autowired
    private KeycloakUserService keycloakUserService;

    public LopHocDTO lopHocToLopHocDTO(Lophoc lophoc) {
        LopHocDTO lopHocDTO = modelMapper.map(lophoc, LopHocDTO.class);
        lopHocDTO.setIdKhoaHoc(khoaConverter.khoaToKhoaDTO(lophoc.getIdKhoaHoc()));
        lopHocDTO.setIdHeDaoTao(heDaoTaoConverter.hedaotaoToHedaotaoDTO(lophoc.getIdHeDaoTao()));
        lopHocDTO.setIdNganh(nganhDaoTaoConverter.nganhdaotaoToNganhdaotaoDTO(lophoc.getIdNganh()));
        lopHocDTO.setSiSo(
                Long.parseLong(String.valueOf(keycloakUserService.getSinhVienByIdLop(lophoc.getIdLopHoc()).size())));
        return lopHocDTO;
    }

    public Lophoc lopHocCreateRequestToLopHoc(LophocCreateRequest request) {
        Lophoc lophoc = request.getIdLopHoc() == null ? null
                : lopHocRepository.findById(request.getIdLopHoc()).orElse(null);
        Set<Hocky> hockySet = new HashSet<>();
        if (lophoc != null) {
            hockySet = lophoc.getHockySet();
        }
        Lophoc newLophoc = modelMapper.map(request, Lophoc.class);
        newLophoc.setHockySet(hockySet);
        newLophoc.setIdHeDaoTao(heDaoTaoRepository.findById(request.getIdHeDaoTao()).orElse(null));
        newLophoc.setIdKhoaHoc(khoaRepository.findById(request.getIdKhoaHoc()).orElse(null));
        newLophoc.setIdNganh(nghanhDaoTaoRepository.findById(request.getIdNganh()).orElse(null));
        return newLophoc;
    }
}