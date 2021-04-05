package com.stipe.mstore.service;

import com.stipe.mstore.model.Size;
import com.stipe.mstore.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeServiceImpl implements SizeService {

    @Autowired
    private SizeRepository sizeRepository;

    @Override
    public Size save(Size size) {
        return sizeRepository.saveSize(size);
    }

    @Override
    public List<Size> getSizes() {
        return sizeRepository.getAllSizes();
    }
}