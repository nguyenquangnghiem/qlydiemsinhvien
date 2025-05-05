package com.quanlydiemsinhvien.qldsv.service.impl;

import com.quanlydiemsinhvien.qldsv.repository.PhonghocRepository;
import com.quanlydiemsinhvien.qldsv.service.PhonghocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PhonghocServiceImpl implements PhonghocService {

    @Autowired
    private PhonghocRepository phonghocRepository;

    @Override
    public boolean deleteById(Integer id) {
        try{
            phonghocRepository.deleteById(id);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
