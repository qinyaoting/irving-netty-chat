package com.example.demo.service;

import com.example.demo.mapper.TransMapper;
import com.example.demo.model.Trans;
import org.springframework.stereotype.Service;

@Service
public  class TransService {
    private static TransMapper transMapper = null;

    public TransService(TransMapper transMapper) {
        this.transMapper = transMapper;
    }

    public static int addTrans(Trans trans) {
        return transMapper.addTrans(trans);
    }
}